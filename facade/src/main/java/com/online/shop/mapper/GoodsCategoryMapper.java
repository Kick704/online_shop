package com.online.shop.mapper;

import com.online.shop.dto.request.AbstractRequestDTO;
import com.online.shop.dto.response.GoodsCategoryResponseDTO;
import com.online.shop.entity.GoodsCategory;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * Маппер для сущности {@link GoodsCategory} и обратно
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface GoodsCategoryMapper extends BaseMapper<GoodsCategory, GoodsCategoryResponseDTO> {

    /**
     * Маппинг из сущности в DTO
     *
     * @param entity сущность
     * @return ResponseDTO
     */
    @Override
    GoodsCategoryResponseDTO toDTO(GoodsCategory entity);

    /**
     * Маппинг из DTO в сущность
     *
     * @param dto RequestDTO
     * @return сущность
     */
    @Override
    GoodsCategory toEntity(AbstractRequestDTO dto);

    /**
     * Маппинг из списка сущностей в список DTO
     *
     * @param goodsCategories список сущностей
     * @return список ResponseDTO
     */
    @Override
    List<GoodsCategoryResponseDTO> toDTOList(List<GoodsCategory> goodsCategories);

    /**
     * Маппинг из списка DTO в список сущностей
     *
     * @param dtos список RequestDTO
     * @return список сущностей
     */
    @Override
    List<GoodsCategory> toEntityList(List<AbstractRequestDTO> dtos);

    /**
     * Обновление сущности на основе DTO, игнорируя null поля и поле id
     *
     * @param dto    RequestDTO, проинициализированные поля которого будут обновлены в сущности
     * @param entity обновляемая сущность
     */
    @Override
    void updateEntityFromDto(AbstractRequestDTO dto, @MappingTarget GoodsCategory entity);

}
