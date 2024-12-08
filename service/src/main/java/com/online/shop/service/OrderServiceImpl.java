package com.online.shop.service;

import com.online.shop.dao.OrderRepository;
import com.online.shop.entity.User;
import com.online.shop.entity.Goods;
import com.online.shop.entity.Order;
import com.online.shop.enums.OrderStatus;
import com.online.shop.exception_handling.CommonRuntimeException;
import com.online.shop.exception_handling.ErrorCode;
import com.online.shop.statemachine.OrderStateMachineService;
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

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderStateMachineService orderStateMachineService;

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
                        ErrorCode.NOT_FOUND,
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
            throw new CommonRuntimeException(ErrorCode.NOT_FOUND, "Ни один заказ не найден в БД");
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
                    ErrorCode.NOT_FOUND, String.format("Ни один заказ не найден по статусу '%s'", status)
            );
        }
        return orders;
    }

    /**
     * Создание заказа в БД
     * <p> Заказ формируется из корзины пользователя, который оформляет заказ
     * <p> При этом корзина пользователя очищается от товаров, а количество приобретенных товаров уменьшается на складе
     *
     * @param order сущность Заказ {@link Order}
     */
    @Override
    @Transactional
    public void create(Order order) {
        if (order == null) {
            throw new CommonRuntimeException(ErrorCode.BAD_REQUEST, "Order: предан пустой объект для сохранения");
        }

        User user = order.getUser();
        if (user == null) {
            throw new CommonRuntimeException(ErrorCode.BAD_REQUEST, "Order: пользователь не указан");
        }

        List<Goods> goodsInCart = new ArrayList<>(userService.getActualCart(user));
        if (goodsInCart.isEmpty()) {
            throw new CommonRuntimeException(ErrorCode.NOT_FOUND, "Ваша корзина пуста");
        }

        double orderAmount = goodsService.getCartTotalPrice(goodsInCart);
        if (user.getBalance() < orderAmount) {
            throw new CommonRuntimeException(
                    ErrorCode.BAD_REQUEST,
                    String.format("Недостаточно средств на счёте, не хватает %.2f рублей",
                            orderAmount - user.getBalance())
            );
        }

        order.setGoodsInOrder(goodsInCart);
        order.setAmount(orderAmount);
        orderStateMachineService.create(order);
        goodsService.deductGoodsCount(goodsInCart);
        userService.updateAfterOrder(user, orderAmount);
    }

    /**
     * Обновление заказа в БД
     *
     * @param order сущность Заказ {@link Order}
     */
    @Override
    public void update(Order order) {
        if (order == null) {
            throw new CommonRuntimeException(ErrorCode.BAD_REQUEST, "Order: предан пустой объект для сохранения");
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
                    ErrorCode.INTERNAL_SERVER_ERROR,
                    String.format("Заказ с ID %s не найден или не может быть удалён", id)
            );
        }
    }

}
