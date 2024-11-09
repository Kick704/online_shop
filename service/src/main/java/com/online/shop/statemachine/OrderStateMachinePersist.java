package com.online.shop.statemachine;

import com.online.shop.dao.OrderRepository;
import com.online.shop.entity.Order;
import com.online.shop.enums.OrderEvent;
import com.online.shop.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Component;

/**
 * Реализация интерфейса {@link StateMachinePersist} для сохранения и восстановления состояния заказа.
 * Обеспечивает интеграцию машины состояний с базой данных, сохраняя статус заказа
 * при изменениях и загружая его при необходимости.
 */
@Component
public class OrderStateMachinePersist implements StateMachinePersist<OrderStatus, OrderEvent, Order> {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Сохраняет текущее состояние заказа в БД
     *
     * @param stateMachineContext контекст машины состояний, хранящий текущее состояние заказа
     * @param order заказ {@link Order}
     * @throws Exception если возникает ошибка при сохранении состояния
     */
    @Override
    public void write(StateMachineContext<OrderStatus, OrderEvent> stateMachineContext, Order order) throws Exception {
        OrderStatus currentStatus = stateMachineContext.getState();
        order.setStatus(currentStatus);
        orderRepository.save(order);
    }

    /**
     * Загружает текущее состояние конкретного заказа
     *
     * @param order заказ {@link Order}
     * @return контекст машины состояний, хранящий текущее состояние заказа
     * @throws Exception если возникает ошибка при чтении состояния
     */
    @Override
    public StateMachineContext<OrderStatus, OrderEvent> read(Order order) throws Exception {
        return new DefaultStateMachineContext<>(order.getStatus(), null, null, null);
    }

}
