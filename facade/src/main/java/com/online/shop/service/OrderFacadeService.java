package com.online.shop.service;

import com.online.shop.dto.request.creation.OrderCreationDTO;
import com.online.shop.dto.request.update.OrderUpdateDTO;
import com.online.shop.dto.response.OrderResponseDTO;
import com.online.shop.entity.Order;
import com.online.shop.enums.OrderStatus;

import java.util.List;

/**
 * Фасад-сервис для управления DTO на основе сущности {@link Order}
 */
public interface OrderFacadeService extends BaseFacadeService<OrderCreationDTO, OrderUpdateDTO, OrderResponseDTO> {

    /**
     * Выборка заказов по статусу
     *
     * @param status статус заказа {@link OrderStatus}
     * @return {@link List} - список заказов {@link OrderResponseDTO} по указанному статусу {@code status}
     */
    List<OrderResponseDTO> findAllByStatus(OrderStatus status);

}
