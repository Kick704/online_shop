package com.online.shop.mapper;

import com.online.shop.dto.GoodsDTO;
import com.online.shop.entity.Goods;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Маппер для {@link Goods} и {@link GoodsDTO}
 */
@Mapper(componentModel = "spring", uses = GoodsCategoryMapper.class)
public interface GoodsMapper {

    GoodsDTO toGoodsDTO(Goods goods);
    Goods toGoods(GoodsDTO goodsDTO);
    List<GoodsDTO> toGoodsDTOList(List<Goods> goods);
    List<Goods> toGoodsList(List<GoodsDTO> goodsDTOS);

}
