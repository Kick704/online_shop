package com.online.shop.service;

import com.online.shop.dao.OrderRepository;
import com.online.shop.entity.User;
import com.online.shop.entity.Goods;
import com.online.shop.entity.Order;
import com.online.shop.enums.OrderStatus;
import com.online.shop.exception_handling.CommonRuntimeException;
import com.online.shop.exception_handling.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Сервис для управления сущностью {@link Order}
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    /**
     * Выборка заказа по id
     *
     * @param id идентификатор заказа {@link UUID}
     * @return {@link Order} - заказ по указанному {@code id}
     */
    @Override
    public Order findById(UUID id) {
        return orderRepository.findOrderById(id)
                .orElseThrow(() -> new CommonRuntimeException(
                        ErrorCode.ENTITY_NOT_FOUND,
                        String.format("Заказ с ID %s не найден", id))
                );
    }

    /**
     * Выборка всех заказов
     *
     * @return {@link List} - список всех заказов {@link Order}
     */
    @Override
    public List<Order> findAll() {
        List<Order> orders = orderRepository.findAllOrders();
        if (orders.isEmpty()) {
            throw new CommonRuntimeException(ErrorCode.ENTITY_NOT_FOUND, "Ни один заказ не найден в БД");
        }
        return orders;
    }

    /**
     * Выборка заказов по статусу
     *
     * @param status статус заказа {@link String}
     * @return {@link List} - список заказов {@link Order} по указанному статусу {@code status}
     */
    @Override
    public List<Order> findAllByStatus(OrderStatus status) {
        List<Order> orders = orderRepository.findAllOrdersByStatus(status);
        if (orders.isEmpty()) {
            throw new CommonRuntimeException(
                    ErrorCode.ENTITY_NOT_FOUND, String.format("Ни один заказ не найден по статусу '%s'", status)
            );
        }
        return orders;
    }

    /**
     * Создание заказа в БД
     * <p> Заказ формируется из корзины пользователя, который оформляет заказ
     * <p> При этом корзина пользователя очищается от товаров
     *
     * @param order сущность Заказ {@link Order}
     */
    @Override
    @Transactional
    public void create(Order order) {
        if (order == null) {
            throw new CommonRuntimeException(
                    ErrorCode.OBJECT_REFERENCE_IS_NULL,
                    "Order: предан пустой объект для сохранения"
            );
        }
        User user = order.getUser();
        List<Goods> goodsInCart = new ArrayList<>(user.getGoodsInCart());
        order.setGoodsInOrder(goodsInCart);
        orderRepository.save(order);
        user.getGoodsInCart().clear();
        userService.update(user);
    }

    /**
     * Обновление заказа в БД
     *
     * @param order сущность Заказ {@link Order}
     */
    @Override
    public void update(Order order) {
        if (order == null) {
            throw new CommonRuntimeException(
                    ErrorCode.OBJECT_REFERENCE_IS_NULL,
                    "Order: предан пустой объект для сохранения"
            );
        }
        orderRepository.save(order);
    }

    /**
     * Удаление заказа по id
     *
     * @param id идентификатор заказа {@link UUID}
     */
    @Override
    public void deleteById(UUID id) {
        if (orderRepository.deleteOrderById(id) == 0) {
            throw new CommonRuntimeException(
                    ErrorCode.ENTITY_NOT_FOUND,
                    String.format("Заказ с ID %s не найден или не может быть удалён", id)
            );
        }
    }
}
