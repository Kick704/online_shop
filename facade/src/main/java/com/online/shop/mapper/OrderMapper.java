package com.online.shop.mapper;

import com.online.shop.dto.request.AbstractRequestDTO;
import com.online.shop.dto.response.OrderResponseDTO;
import com.online.shop.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * Маппер для сущности {@link Order} и обратно
 */
@Mapper(componentModel = "spring", uses = {CustomerMapper.class, GoodsMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper extends BaseMapper<Order, OrderResponseDTO> {

    /**
     * Маппинг из сущности в DTO
     *
     * @param entity сущность
     * @return ResponseDTO
     */
    @Override
    OrderResponseDTO toDTO(Order entity);

    /**
     * Маппинг из DTO в сущность
     *
     * @param dto RequestDTO
     * @return сущность
     */
    @Override
    Order toEntity(AbstractRequestDTO dto);

    /**
     * Маппинг из списка сущностей в список DTO
     *
     * @param orders список сущностей
     * @return список ResponseDTO
     */
    @Override
    List<OrderResponseDTO> toDTOList(List<Order> orders);

    /**
     * Маппинг из списка DTO в список сущностей
     *
     * @param dtos список RequestDTO
     * @return список сущностей
     */
    @Override
    List<Order> toEntityList(List<AbstractRequestDTO> dtos);

    /**
     * Обновление сущности на основе DTO, игнорируя null поля и поле id
     *
     * @param dto    RequestDTO, проинициализированные поля которого будут обновлены в сущности
     * @param entity обновляемая сущность
     */
    @Override
    void updateEntityFromDto(AbstractRequestDTO dto, @MappingTarget Order entity);

}
