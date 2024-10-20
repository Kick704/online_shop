package com.online.shop.service;

import com.online.shop.entity.Order;
import com.online.shop.enums.OrderStatus;

import java.util.List;

/**
 * Сервис для управления сущностью {@link Order}
 */
public interface OrderService extends BaseService<Order> {

    /**
     * Выборка заказов по статусу
     *
     * @param status статус заказа {@link OrderStatus}
     * @return {@link List} - список заказов {@link Order} по указанному статусу {@code status}
     */
    List<Order> findAllByStatus(OrderStatus status);

    /**
     * Создание заказа в БД
     *
     * @param order сущность Заказ {@link Order}
     */
    void create(Order order);

    /**
     * Обновление заказа в БД
     *
     * @param order сущность Заказ {@link Order}
     */
    void update(Order order);

}
