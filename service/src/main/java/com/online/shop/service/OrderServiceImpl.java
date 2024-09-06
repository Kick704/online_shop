package com.online.shop.service;

import com.online.shop.dao.OrderRepository;
import com.online.shop.entity.Customer;
import com.online.shop.entity.Goods;
import com.online.shop.entity.Order;
import com.online.shop.enums.OrderStatus;
import com.online.shop.exception.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Реализация интерфейса для управления сущностью {@link Order} на сервисном слое
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerService customerService;

    /**
     * Выборка заказа по id
     *
     * @param id идентификатор заказа {@link UUID}
     * @return {@link Order} - заказ по указанному {@code id}, или выбрасывается исключение
     * {@link NotFoundEntityException}, если такого заказа нет
     */
    @Override
    public Order findById(UUID id) {
        return orderRepository.findOrderById(id)
                .orElseThrow(() -> new NotFoundEntityException(String.format("Заказ с ID: %s не найден", id)));
    }

    /**
     * Выборка всех заказов
     *
     * @return {@link List} - список всех заказов {@link Order}, или выбрасывается исключение
     * {@link NotFoundEntityException}, если заказов ещё нет
     */
    @Override
    public List<Order> findAll() {
        List<Order> orders = orderRepository.findAllOrders();
        if (orders.isEmpty()) {
            throw new NotFoundEntityException("Ни один заказ не найден в БД");
        }
        return orders;
    }

    /**
     * Выборка заказов по статусу
     *
     * @param status статус заказа {@link String}
     * @return {@link List} - список заказов {@link Order} по указанному статусу {@code status}, или выбрасывается
     * исключение {@link NotFoundEntityException}, если таких заказов нет
     */
    @Override
    public List<Order> findAllOrdersByStatus(OrderStatus status) {
        List<Order> orders = orderRepository.findAllOrdersByStatus(status);
        if (orders.isEmpty()) {
            throw new NotFoundEntityException(String.format("Ни один заказ не найден по статусу %s", status));
        }
        return orders;
    }

    /**
     * Создание заказа в БД
     * <p> Заказ формируется из корзины покупателя, который оформляет заказ
     * <p> При этом корзина покупателя очищается от товаров
     *
     * @param order сущность Заказ {@link Order}
     */
    @Override
    @Transactional
    public void createOrder(Order order) {
        Customer customer = order.getCustomer();
        List<Goods> goodsInCart = new ArrayList<>(customer.getGoodsInCart());
        order.setGoodsInOrder(goodsInCart);
        orderRepository.save(order);
        customer.getGoodsInCart().clear();
        customerService.save(customer);
    }

    /**
     * Обновление заказа в БД
     * <p> Может выбросить исключение {@link NullPointerException}, если сущность ссылается на null
     *
     * @param order сущность Заказ {@link Order}
     */
    @Override
    public void updateOrder(Order order) {
        Objects.requireNonNull(order, "Сущность Order не проинициализирована перед сохранением");
        orderRepository.save(order);
    }

    /**
     * Удаление заказа по id
     * <p> Может выбросить исключение {@link NotFoundEntityException}, если заказ {@link Customer} не был удалён
     *
     * @param id идентификатор заказа {@link UUID}
     */
    @Override
    public void deleteById(UUID id) {
        if (orderRepository.deleteOrderById(id) == 0) {
            throw new NotFoundEntityException(String.format("Заказ с ID: %s не найден или не может быть удалён", id));
        }
    }
}
