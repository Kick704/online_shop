package com.online.shop.statemachine;

import com.online.shop.entity.Order;
import com.online.shop.enums.OrderStatus;
import com.online.shop.enums.OrderEvent;
import com.online.shop.exception_handling.CommonRuntimeException;
import com.online.shop.exception_handling.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Сервис для управления состояниями заказов с использованием фабрики машин состояний
 */
@Service
public class OrderStateMachineService {

    @Autowired
    private StateMachineFactory<OrderStatus, OrderEvent> stateMachineFactory;

    @Autowired
    private StateMachinePersister<OrderStatus, OrderEvent, Order> stateMachinePersister;

    /**
     * Создает и сохраняет новую машину состояний для заказа
     *
     * @param order заказ {@link Order}
     */
    public void create(Order order) {
        StateMachine<OrderStatus, OrderEvent> stateMachine = stateMachineFactory.getStateMachine();
        try {
            stateMachinePersister.persist(stateMachine, order);
        } catch (Exception e) {
            throw new CommonRuntimeException(
                    ErrorCode.INTERNAL_SERVER_ERROR,
                    String.format("Ошибка создания заказа: %s", e)
            );
        }
    }

    /**
     * Выполняет переход к следующему состоянию заказа, отправляя событие в машину состояний.
     * Поддерживает отправку причины отмены для конечного статуса {@link OrderStatus#CANCELLED}
     *
     * @param order заказ {@link Order}
     * @param event событие {@link OrderEvent}, инициирующее переход к следующему состоянию
     * @param cancelCause причина отмены заказа для соответствующего конечного статуса
     */
    public void next(Order order, OrderEvent event, String cancelCause) {
        StateMachine<OrderStatus, OrderEvent> stateMachine = stateMachineFactory.getStateMachine();
        try {
            stateMachinePersister.restore(stateMachine, order);
            Message<OrderEvent> eventMessage = buildEventMessage(order, event, cancelCause);
            stateMachine.sendEvent(Mono.just(eventMessage));
            stateMachinePersister.persist(stateMachine, order);
        } catch (Exception e) {
            throw new CommonRuntimeException(
                    ErrorCode.INTERNAL_SERVER_ERROR,
                    String.format("Ошибка изменения статуса заказа: %s", e)
            );
        }
    }

    /**
     * Выполняет переход к следующему состоянию заказа, отправляя событие в машину состояний.
     * Используется для инициации переходов в состояния, отличных от {@link OrderStatus#CANCELLED},
     * то есть не нуждающихся в указании причины перехода
     *
     * @param order заказ {@link Order}
     * @param event событие {@link OrderEvent}, инициирующее переход к следующему состоянию
     */
    public void next(Order order, OrderEvent event) {
        next(order, event, null);
    }

    /**
     * Формирует сообщение с событием для отправки в машину состояний и инициации перехода.
     * Устанавливает заголовки в сообщение для конечных состояний, содержащие необходимую для слушателя информацию
     *
     * @param order заказ {@link Order}
     * @param event событие {@link OrderEvent}, инициирующее переход к следующему состоянию
     * @param cancelCause причина отмены заказа для соответствующего конечного статуса
     * @return сообщение с событием и заголовками для отправки в машину состояний
     */
    private Message<OrderEvent> buildEventMessage(Order order, OrderEvent event, String cancelCause) {
        MessageBuilder<OrderEvent> eventMessage = MessageBuilder.withPayload(event);
        if (event == OrderEvent.CONFIRM_DELIVERY || event == OrderEvent.CANCEL) {
            eventMessage.setHeader("order", order);
            if (event == OrderEvent.CANCEL) {
                eventMessage.setHeader("cancelCause", cancelCause);
            }
        }
        return eventMessage.build();
    }

}
