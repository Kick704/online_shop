package com.online.shop.service;

import com.online.shop.dto.OrderDTO;
import com.online.shop.entity.Customer;
import com.online.shop.entity.Order;
import com.online.shop.enums.OrderStatus;
import com.online.shop.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class OrderFacadeServiceImpl implements OrderFacadeService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderMapper orderMapper;

    /**
     * Выборка заказа по id
     *
     * @param id идентификатор заказа {@link UUID}
     * @return {@link OrderDTO} - заказ по указанному {@code id}
     */
    @Override
    @Transactional(readOnly = true)
    public OrderDTO findOrderById(UUID id) {
        return orderMapper.toDTO(orderService.findOrderById(id));
    }

    /**
     * Выборка всех заказов
     *
     * @return {@link List} - список всех заказов {@link OrderDTO}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrderDTO> findAllOrders() {
        return orderMapper.toDTOList(orderService.findAllOrders());
    }

    /**
     * Выборка заказов по статусу
     *
     * @param status статус заказа {@link OrderStatus}
     * @return {@link List} - список заказов {@link OrderDTO} по указанному статусу {@code status}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrderDTO> findAllOrdersByStatus(OrderStatus status) {
        return orderMapper.toDTOList(orderService.findAllOrdersByStatus(status));
    }

    /**
     * Создание заказа в БД с удалением товаров из корзины покупателя
     *
     * @param order сущность Заказ {@link Order}
     * @return DTO Заказ {@link OrderDTO}
     */
    @Override
    public OrderDTO addNewOrder(Order order) {
        Customer customer = customerService.findCustomerById(order.getCustomer().getId());
        Order newOrder = Order.Builder
                .newBuilder()
                .customer(order.getCustomer())
                .amount(order.getAmount())
                .deliveryAddress(order.getDeliveryAddress())
                .receiptCode(order.getReceiptCode())
                .build();
        newOrder.setGoodsInOrder(customer.getGoodsInCart());
        orderService.saveOrder(newOrder);
        customer.getGoodsInCart().clear();
        customerService.saveCustomer(customer);
        return orderMapper.toDTO(newOrder);
    }

    /**
     * Обновление заказа в БД
     *
     * @param id       идентификатор заказа {@link UUID}
     * @param orderDTO DTO Заказ {@link OrderDTO} с изменёнными полями
     * @return обновлённый DTO Заказ {@link OrderDTO}
     */
    @Override
    public OrderDTO updateOrder(UUID id, OrderDTO orderDTO) {
        Order order = orderService.findOrderById(id);
        orderMapper.updateEntityFromDto(orderDTO, order);
        orderService.saveOrder(order);
        return orderMapper.toDTO(order);
    }

    /**
     * Удаление заказа по id
     *
     * @param id идентификатор заказа {@link UUID}
     */
    @Override
    @Transactional
    public void deleteOrderById(UUID id) {
        orderService.deleteOrderById(id);
    }

}
