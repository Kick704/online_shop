package com.online.shop.service;

import com.online.shop.dto.OrderDTO;
import com.online.shop.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class OrderDTOServiceImpl implements OrderDTOService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    /**
     * Выборка заказа по id
     *
     * @param id идентификатор заказа {@link UUID}
     * @return {@link OrderDTO} - заказ по указанному {@code id}, или {@code null}, если такого заказа нет
     */
    @Transactional(readOnly = true)
    @Override
    public OrderDTO findOrderById(UUID id) {
        return orderMapper.toOrderDTO(orderService.findOrderById(id));
    }

    /**
     * Выборка всех заказов
     *
     * @return {@link List} - список всех заказов, или {@code null}, если заказов еще нет
     */
    @Transactional(readOnly = true)
    @Override
    public List<OrderDTO> findAllOrders() {
        return orderMapper.toOrderDTOList(orderService.findAllOrders());
    }

    /**
     * Выборка заказов по статусу
     *
     * @param status статус заказа {@link String}
     * @return {@link List} - список заказов по указанному статусу, или {@code null}, если таких заказов нет
     */
    @Transactional(readOnly = true)
    @Override
    public List<OrderDTO> findAllOrdersByStatus(String status) {
        return orderMapper.toOrderDTOList(orderService.findAllOrdersByStatus(status));
    }

    /**
     * Удаление заказа по id
     *
     * @param id идентификатор заказа {@link UUID}
     */
    @Transactional
    @Override
    public void deleteOrderById(UUID id) {
        orderService.deleteOrderById(id);
    }

}
