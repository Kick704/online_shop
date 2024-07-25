package com.online.shop.mapper;

import com.online.shop.dto.GoodsCategoryDTO;
import com.online.shop.entity.GoodsCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GoodsCategoryMapper {

    GoodsCategoryDTO toGoodsCategoryDTO(GoodsCategory goodsCategory);
    GoodsCategory toGoodsCategory(GoodsCategoryDTO goodsCategoryDTO);

}
