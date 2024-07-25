package com.online.shop.service;

import com.online.shop.dao.OrderRepository;
import com.online.shop.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Выборка заказа по id
     *
     * @param id идентификатор заказа {@link UUID}
     * @return {@link Order} - заказ по указанному {@code id}, или {@code null}, если такого заказа нет
     */
    @Override
    public Order findOrderById(UUID id) {
        return orderRepository.findOrderById(id);
    }

    /**
     * Выборка всех заказов
     *
     * @return {@link List} - список всех заказов, или {@code null}, если заказов еще нет
     */
    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAllOrders();
    }

    /**
     * Выборка заказов по статусу
     *
     * @param status статус заказа {@link String}
     * @return {@link List} - список заказов по указанному статусу, или {@code null}, если таких заказов нет
     */
    @Override
    public List<Order> findAllOrdersByStatus(String status) {
        return orderRepository.findAllOrdersByStatus(status);
    }

    /**
     * Удаление заказа по id
     *
     * @param id идентификатор заказа {@link UUID}
     */
    @Override
    public void deleteOrderById(UUID id) {
        orderRepository.deleteOrderById(id);
    }
}
