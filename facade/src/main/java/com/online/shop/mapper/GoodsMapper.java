package com.online.shop.mapper;

import com.online.shop.dto.GoodsDTO;
import com.online.shop.entity.Goods;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * Маппер для {@link Goods} и {@link GoodsDTO}
 */
@Mapper(componentModel = "spring", uses = GoodsCategoryMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface GoodsMapper extends BaseMapper<Goods, GoodsDTO> {

    /**
     * Маппинг из сущности в DTO
     *
     * @param entity сущность
     * @return DTO
     */
    @Override
    GoodsDTO toDTO(Goods entity);

    /**
     * Маппинг из DTO в сущность
     *
     * @param dto DTO
     * @return сущность
     */
    @Override
    Goods toEntity(GoodsDTO dto);

    /**
     * Маппинг из списка сущностей в список DTO
     *
     * @param goods список сущностей
     * @return список DTO
     */
    @Override
    List<GoodsDTO> toDTOList(List<Goods> goods);

    /**
     * Маппинг из списка DTO в список сущностей
     *
     * @param goodsDTOS список DTO
     * @return список сущностей
     */
    @Override
    List<Goods> toEntityList(List<GoodsDTO> goodsDTOS);

    /**
     * Обновление сущности на основе DTO, игнорируя null поля и поле id
     *
     * @param dto    DTO, проинициализированные поля которого, кроме id, будут обновлены в сущности
     * @param entity обновляемая сущность
     */
    @Override
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(GoodsDTO dto, @MappingTarget Goods entity);

}
