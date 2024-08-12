package com.online.shop.service;

import com.online.shop.entity.Order;
import com.online.shop.enums.OrderStatus;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    /**
     * Выборка заказа по id
     *
     * @param id идентификатор заказа {@link UUID}
     * @return {@link Order} - заказ по указанному {@code id}
     */
    Order findOrderById(UUID id);

    /**
     * Выборка всех заказов
     *
     * @return {@link List} - список всех заказов {@link Order}
     */
    List<Order> findAllOrders();

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

    /**
     * Удаление заказа по id
     *
     * @param id идентификатор заказа {@link UUID}
     */
    void deleteOrderById(UUID id);

}
