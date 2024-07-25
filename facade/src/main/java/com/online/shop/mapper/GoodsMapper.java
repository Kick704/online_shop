package com.online.shop.mapper;

import com.online.shop.dto.GoodsDTO;
import com.online.shop.entity.Goods;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = GoodsCategoryMapper.class)
public interface GoodsMapper {

    GoodsDTO toGoodsDTO(Goods goods);
    Goods toGoods(GoodsDTO goodsDTO);

}
