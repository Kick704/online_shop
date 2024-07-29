package com.online.shop.mapper;

import com.online.shop.dto.GoodsCategoryDTO;
import com.online.shop.entity.GoodsCategory;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Маппер для {@link GoodsCategory} и {@link GoodsCategoryDTO}
 */
@Mapper(componentModel = "spring")
public interface GoodsCategoryMapper {

    GoodsCategoryDTO toGoodsCategoryDTO(GoodsCategory goodsCategory);
    GoodsCategory toGoodsCategory(GoodsCategoryDTO goodsCategoryDTO);
    List<GoodsCategoryDTO> toGoodsCategoryDTOList(List<GoodsCategory> goodsCategories);
    List<GoodsCategory> toCGoodsCategoryList(List<GoodsCategoryDTO> goodsCategoryDTOS);

}
