package com.online.shop.service;

import com.online.shop.dto.OrderDTO;
import com.online.shop.entity.Order;
import com.online.shop.enums.OrderStatus;

import java.util.List;
import java.util.UUID;

public interface OrderFacadeService {

    /**
     * Выборка заказа по id
     *
     * @param id идентификатор заказа {@link UUID}
     * @return {@link OrderDTO} - заказ по указанному {@code id}
     */
    OrderDTO findOrderById(UUID id);

    /**
     * Выборка всех заказов
     *
     * @return {@link List} - список всех заказов {@link OrderDTO}
     */
    List<OrderDTO> findAllOrders();

    /**
     * Выборка заказов по статусу
     *
     * @param status статус заказа {@link OrderStatus}
     * @return {@link List} - список заказов {@link Order} по указанному статусу {@code status}
     */
    List<OrderDTO> findAllOrdersByStatus(OrderStatus status);

    /**
     * Добавление заказа товара в БД
     *
     * @param order сущность Заказ {@link Order}
     * @return DTO Заказ {@link OrderDTO}
     */
    OrderDTO addNewOrder(Order order);

    /**
     * Обновление заказа в БД
     *
     * @param id          идентификатор заказа {@link UUID}
     * @param orderDTO DTO Заказ {@link OrderDTO} с изменёнными полями
     * @return обновлённый DTO Заказ {@link OrderDTO}
     */
    OrderDTO updateOrder(UUID id, OrderDTO orderDTO);

    /**
     * Удаление заказа по id
     *
     * @param id идентификатор заказа {@link UUID}
     */
    void deleteOrderById(UUID id);

}
