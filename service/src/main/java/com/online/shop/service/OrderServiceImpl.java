package com.online.shop.service;

import com.online.shop.dao.OrderRepository;
import com.online.shop.entity.Customer;
import com.online.shop.entity.Order;
import com.online.shop.enums.OrderStatus;
import com.online.shop.exception.NoEntitiesFoundException;
import com.online.shop.exception.NoSuchEntityException;
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
     * @return {@link Order} - заказ по указанному {@code id}, или выбрасывается исключение
     * {@link NoSuchEntityException}, если такого заказа нет
     */
    @Override
    public Order findOrderById(UUID id) {
        return orderRepository.findOrderById(id)
                .orElseThrow(() -> new NoSuchEntityException("Заказ с ID: " + id + " не найден"));
    }

    /**
     * Выборка всех заказов
     *
     * @return {@link List} - список всех заказов {@link Order}, или выбрасывается исключение
     * {@link NoEntitiesFoundException}, если заказов ещё нет
     */
    @Override
    public List<Order> findAllOrders() {
        List<Order> orders = orderRepository.findAllOrders();
        if (orders.isEmpty()) {
            throw new NoEntitiesFoundException("Ни один заказ не найден в БД");
        }
        return orders;
    }

    /**
     * Выборка заказов по статусу
     *
     * @param status статус заказа {@link String}
     * @return {@link List} - список заказов {@link Order} по указанному статусу {@code status}, или выбрасывается
     * исключение {@link NoEntitiesFoundException}, если таких заказов нет
     */
    @Override
    public List<Order> findAllOrdersByStatus(OrderStatus status) {
        List<Order> orders = orderRepository.findAllOrdersByStatus(status);
        if (orders.isEmpty()) {
            throw new NoEntitiesFoundException("Ни один заказ не найден по статусу " + status);
        }
        return orders;
    }

    /**
     * Добавление/обновление заказа в БД
     *
     * @param order сущность Заказ {@link Order}
     */
    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    /**
     * Удаление заказа по id
     * <p> Может выбросить исключение {@link NoSuchEntityException}, если заказ {@link Customer} не был удалён
     *
     * @param id идентификатор заказа {@link UUID}
     */
    @Override
    public void deleteOrderById(UUID id) {
        if (orderRepository.deleteOrderById(id) == 0) {
            throw new NoSuchEntityException("Заказ с ID: " + id + " не найден и/или не может быть удалён");
        }
    }
}
