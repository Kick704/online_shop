package com.online.shop.statemachine;

import com.online.shop.dao.CompletedOrderEventRepository;
import com.online.shop.entity.CompletedOrderEvent;
import com.online.shop.entity.Order;
import com.online.shop.enums.OrderEvent;
import com.online.shop.enums.OrderStatus;
import com.online.shop.exception_handling.CommonRuntimeException;
import com.online.shop.exception_handling.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Слушатель событий машины состояний заказов
 */
@Component
public class OrderStateMachineEventListener extends StateMachineListenerAdapter<OrderStatus, OrderEvent> {

    @Autowired
    private CompletedOrderEventRepository completedOrderEventRepository;

    /**
     * Обрабатывает контекст машины состояний, отлавливая конечные состояния {@link OrderStatus#DELIVERED}
     * и {@link OrderStatus#CANCELLED}. Регистрирует их в базе данных с необходимой извлеченной информацией из контекста
     *
     * @param stateContext контекст состояния, в котором находится заказ
     */
    @Override
    public void stateContext(StateContext<OrderStatus, OrderEvent> stateContext) {
        OrderStatus targetStatus = stateContext.getStateMachine().getState().getId();
        if (targetStatus == OrderStatus.DELIVERED || targetStatus == OrderStatus.CANCELLED) {
            Order order = Optional.ofNullable((Order) stateContext.getMessageHeader("order"))
                    .orElseThrow(() -> new CommonRuntimeException(
                            ErrorCode.INTERNAL_SERVER_ERROR,
                            "Ошибка регистрации завершенного заказа")
                    );
            String cancelCause = (String) stateContext.getMessageHeader("cancelCause");
            completedOrderEventRepository.save(buildEvent(order, cancelCause));
        }
    }

    /**
     * Формирует событие на основе {@link CompletedOrderEvent} для дальнейшего сохранения в БД
     *
     * @param order заказ {@link Order}
     * @param cancelCause причина отмены заказа при переходе в состояние {@link OrderStatus#CANCELLED}
     * @return событие
     */
    private CompletedOrderEvent buildEvent(Order order, String cancelCause) {
        return CompletedOrderEvent.Builder.newBuilder()
                .order(order)
                .description(cancelCause == null ?
                        "Успешно доставлен" :
                        String.format("Отменён по причине: %s", cancelCause))
                .build();
    }

}
