package com.online.shop.mapper;

import com.online.shop.dto.request.creation.GoodsCreationDTO;
import com.online.shop.dto.request.update.GoodsUpdateDTO;
import com.online.shop.dto.response.GoodsResponseDTO;
import com.online.shop.entity.Goods;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * Маппер для сущности {@link Goods} и обратно
 */
@Mapper(componentModel = "spring", uses = GoodsCategoryMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface GoodsMapper extends BaseMapper<Goods,
        GoodsCreationDTO,
        GoodsUpdateDTO,
        GoodsResponseDTO> {

    /**
     * Маппинг из сущности в DTO
     *
     * @param entity сущность
     * @return ResponseDTO
     */
    @Override
    GoodsResponseDTO toDTO(Goods entity);

    /**
     * Маппинг из DTO в сущность для его создания
     *
     * @param dto RequestDTO
     * @return сущность
     */
    @Override
    Goods toEntity(GoodsCreationDTO dto);

    /**
     * Маппинг из списка сущностей в список DTO
     *
     * @param goods список сущностей
     * @return список ResponseDTO
     */
    @Override
    List<GoodsResponseDTO> toDTOList(List<Goods> goods);

    /**
     * Обновление сущности на основе DTO, игнорируя null поля и поле id
     *
     * @param dto    RequestDTO, проинициализированные поля которого будут обновлены в сущности
     * @param entity обновляемая сущность
     */
    @Override
    void updateEntityFromDto(GoodsUpdateDTO dto, @MappingTarget Goods entity);

}
