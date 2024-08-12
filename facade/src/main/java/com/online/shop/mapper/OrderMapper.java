package com.online.shop.mapper;

import com.online.shop.dto.OrderCreationDTO;
import com.online.shop.dto.OrderDTO;
import com.online.shop.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * Маппер для {@link Order} и {@link OrderDTO}
 */
@Mapper(componentModel = "spring", uses = CustomerMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper extends BaseMapper<Order, OrderDTO> {

    /**
     * Маппинг из сущности в DTO
     *
     * @param entity сущность
     * @return DTO
     */
    @Override
    OrderDTO toDTO(Order entity);

    /**
     * Маппинг из DTO в сущность
     *
     * @param dto DTO
     * @return сущность
     */
    @Override
    Order toEntity(OrderDTO dto);

    /**
     * Маппинг из DTO в сущность при создании заказа
     * @param dto DTO с информацией о создаваемом заказе
     * @return сущность
     */
    Order toEntity(OrderCreationDTO dto);

    /**
     * Маппинг из списка сущностей в список DTO
     *
     * @param orders список сущностей
     * @return список DTO
     */
    @Override
    List<OrderDTO> toDTOList(List<Order> orders);

    /**
     * Маппинг из списка DTO в список сущностей
     *
     * @param orderDTOS список DTO
     * @return список сущностей
     */
    @Override
    List<Order> toEntityList(List<OrderDTO> orderDTOS);

    /**
     * Обновление сущности на основе DTO, игнорируя null поля и поле id
     *
     * @param dto    DTO, проинициализированные поля которого, кроме id, будут обновлены в сущности
     * @param entity обновляемая сущность
     */
    @Override
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(OrderDTO dto, @MappingTarget Order entity);
}
