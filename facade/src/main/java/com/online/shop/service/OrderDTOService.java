package com.online.shop.service;

import com.online.shop.dto.OrderDTO;

import java.util.List;
import java.util.UUID;

public interface OrderDTOService {

    /**
     * Выборка заказа по id
     * @param id идентификатор заказа {@link UUID}
     * @return {@link OrderDTO} - заказ по указанному {@code id}, или {@code null}, если такого заказа нет
     */
    OrderDTO findOrderById(UUID id);

    /**
     * Выборка всех заказов
     * @return {@link List} - список всех заказов, или {@code null}, если заказов еще нет
     */
    List<OrderDTO> findAllOrders();

    /**
     * Выборка заказов по статусу
     * @param status статус заказа {@link String}
     * @return {@link List} - список заказов по указанному статусу, или {@code null}, если таких заказов нет
     */
    List<OrderDTO> findAllOrdersByStatus(String status);

    /**
     * Удаление заказа по id
     * @param id идентификатор заказа {@link UUID}
     */
    void deleteOrderById(UUID id);

}
