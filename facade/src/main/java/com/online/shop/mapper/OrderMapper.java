package com.online.shop.mapper;

import com.online.shop.dto.OrderDTO;
import com.online.shop.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Маппер для {@link Order} и {@link OrderDTO}
 */
@Mapper(componentModel = "spring", uses = CustomerMapper.class)
public interface OrderMapper {

    OrderDTO toOrderDTO(Order order);
    Order toOrder(OrderDTO orderDTO);
    List<OrderDTO> toOrderDTOList(List<Order> orders);
    List<Order> toOrderList(List<OrderDTO> orderDTOS);

}
