package com.online.shop.service;

import com.online.shop.dto.request.creation.OrderCreationDTO;
import com.online.shop.dto.request.update.OrderUpdateDTO;
import com.online.shop.dto.response.InformationDTO;
import com.online.shop.dto.response.OrderResponseDTO;
import com.online.shop.entity.User;
import com.online.shop.entity.Order;
import com.online.shop.enums.OrderStatus;
import com.online.shop.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Фасад-сервис для управления DTO на основе сущности {@link Order}
 */
@Service
public class OrderFacadeServiceImpl implements OrderFacadeService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderMapper orderMapper;

    /**
     * Выборка заказа по id
     *
     * @param id идентификатор заказа {@link UUID}
     * @return {@link OrderResponseDTO} - заказ по указанному {@code id}
     */
    @Override
    @Transactional(readOnly = true)
    public OrderResponseDTO findById(UUID id) {
        return orderMapper.toDTO(orderService.findById(id));
    }

    /**
     * Выборка всех заказов
     *
     * @return {@link List} - список всех заказов {@link OrderResponseDTO}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrderResponseDTO> findAll() {
        return orderMapper.toDTOList(orderService.findAll());
    }

    /**
     * Выборка заказов по статусу
     *
     * @param status статус заказа {@link OrderStatus}
     * @return {@link List} - список заказов {@link OrderResponseDTO} по указанному статусу {@code status}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrderResponseDTO> findAllByStatus(OrderStatus status) {
        return orderMapper.toDTOList(orderService.findAllByStatus(status));
    }

    /**
     * Создание заказа
     *
     * @param orderCreationDTO DTO новый Заказ {@link OrderCreationDTO}
     * @return DTO Заказ {@link OrderResponseDTO}
     */
    @Override
    @Transactional
    public OrderResponseDTO addNew(OrderCreationDTO orderCreationDTO) {
        Order newOrder = orderMapper.toEntity(orderCreationDTO);
        User user = userService.findById(orderCreationDTO.getUserId());
        newOrder.setUser(user);
        orderService.create(newOrder);
        return orderMapper.toDTO(newOrder);
    }

    /**
     * Обновление существующего заказа
     *
     * @param id идентификатор заказа {@link UUID}
     * @param orderUpdateDTO DTO Заказ {@link OrderUpdateDTO} с изменёнными полями
     * @return обновлённый DTO Заказ {@link OrderResponseDTO}
     */
    @Override
    @Transactional
    public OrderResponseDTO update(UUID id, OrderUpdateDTO orderUpdateDTO) {
        Order order = orderService.findById(id);
        orderMapper.updateEntityFromDto(orderUpdateDTO, order);
        orderService.update(order);
        return orderMapper.toDTO(order);
    }

    /**
     * Удаление заказа по id
     *
     * @param id идентификатор заказа {@link UUID}
     * @return {@link InformationDTO} с сообщением о результате
     */
    @Override
    @Transactional
    public InformationDTO deleteById(UUID id) {
        orderService.deleteById(id);
        return new InformationDTO(String.format("Заказ с ID %s удалён", id));
    }

}