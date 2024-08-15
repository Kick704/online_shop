package com.online.shop.service;

import com.online.shop.entity.Order;
import com.online.shop.enums.OrderStatus;

import java.util.List;

/**
 * Интерфейс для управления сущностью {@link Order} на сервисном слое
 */
public interface OrderService extends BaseService<Order> {

    /**
     * Выборка заказов по статусу
     *
     * @param status статус заказа {@link OrderStatus}
     * @return {@link List} - список заказов {@link Order} по указанному статусу {@code status}
     */
    List<Order> findAllOrdersByStatus(OrderStatus status);

    /**
     * Создание заказа в БД
     * @param order сущность Заказ {@link Order}
     */
    void createOrder(Order order);

    /**
     * Обновление заказа в БД
     *
     * @param order сущность Заказ {@link Order}
     */
    void updateOrder(Order order);

}
