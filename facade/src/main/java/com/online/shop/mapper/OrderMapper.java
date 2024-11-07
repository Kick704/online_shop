package com.online.shop.mapper;

import com.online.shop.dto.request.creation.OrderCreationDTO;
import com.online.shop.dto.request.update.OrderUpdateDTO;
import com.online.shop.dto.response.OrderResponseDTO;
import com.online.shop.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * Маппер для сущности {@link Order}
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, GoodsMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper extends BaseMapper<Order,
        OrderCreationDTO,
        OrderUpdateDTO,
        OrderResponseDTO> {

    /**
     * Маппинг из сущности в DTO
     *
     * @param entity сущность
     * @return ResponseDTO
     */
    @Override
    OrderResponseDTO toDTO(Order entity);

    /**
     * Маппинг из DTO в сущность для его создания
     *
     * @param dto RequestDTO
     * @return сущность
     */
    @Override
    Order toEntity(OrderCreationDTO dto);

    /**
     * Маппинг из списка сущностей в список DTO
     *
     * @param orders список сущностей
     * @return список ResponseDTO
     */
    @Override
    List<OrderResponseDTO> toDTOList(List<Order> orders);

    /**
     * Обновление сущности на основе DTO, игнорируя null поля
     *
     * @param dto    RequestDTO, проинициализированные поля которого будут обновлены в сущности
     * @param entity обновляемая сущность
     */
    @Override
    void updateEntityFromDto(OrderUpdateDTO dto, @MappingTarget Order entity);

}
