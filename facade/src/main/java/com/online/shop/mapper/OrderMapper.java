package com.online.shop.mapper;

import com.online.shop.dto.OrderDTO;
import com.online.shop.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CustomerMapper.class)
public interface OrderMapper {

    OrderDTO toOrderDTO(Order order);
    Order toOrder(OrderDTO orderDTO);

}
