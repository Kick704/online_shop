package com.online.shop.mapper;

import com.online.shop.dto.GoodsCategoryCreationDTO;
import com.online.shop.dto.GoodsCategoryDTO;
import com.online.shop.entity.GoodsCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * Маппер для {@link GoodsCategory} и {@link GoodsCategoryDTO}
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface GoodsCategoryMapper extends BaseMapper<GoodsCategory, GoodsCategoryDTO> {

    /**
     * Маппинг из сущности в DTO
     *
     * @param entity сущность
     * @return DTO
     */
    @Override
    GoodsCategoryDTO toDTO(GoodsCategory entity);

    /**
     * Маппинг из DTO в сущность
     *
     * @param dto DTO
     * @return сущность
     */
    @Override
    GoodsCategory toEntity(GoodsCategoryDTO dto);

    /**
     * Маппинг из DTO в сущность при добавлении новой категории товаров
     * @param dto DTO с информацией о новой категории товаров
     * @return сущность
     */
    GoodsCategory toEntity(GoodsCategoryCreationDTO dto);

    /**
     * Маппинг из списка сущностей в список DTO
     *
     * @param goodsCategories список сущностей
     * @return список DTO
     */
    @Override
    List<GoodsCategoryDTO> toDTOList(List<GoodsCategory> goodsCategories);

    /**
     * Маппинг из списка DTO в список сущностей
     *
     * @param goodsCategoryDTOS список DTO
     * @return список сущностей
     */
    @Override
    List<GoodsCategory> toEntityList(List<GoodsCategoryDTO> goodsCategoryDTOS);

    /**
     * Обновление сущности на основе DTO, игнорируя null поля и поле id
     *
     * @param dto    DTO, проинициализированные поля которого, кроме id, будут обновлены в сущности
     * @param entity обновляемая сущность
     */
    @Override
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(GoodsCategoryDTO dto, @MappingTarget GoodsCategory entity);

}
