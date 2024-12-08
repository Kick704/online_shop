package com.online.shop.mapper;

import com.online.shop.dto.request.creation.GoodsCreationDTO;
import com.online.shop.dto.request.update.GoodsUpdateDTO;
import com.online.shop.dto.response.GoodsResponseDTO;
import com.online.shop.dto.response.SelectedGoodsDTO;
import com.online.shop.entity.Goods;
import com.online.shop.util.PriceUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Маппер для сущности {@link Goods}
 */
@Mapper(componentModel = "spring", uses = GoodsCategoryMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, imports = PriceUtils.class)
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
     * Обновление сущности на основе DTO, игнорируя null поля
     *
     * @param dto RequestDTO, проинициализированные поля которого будут обновлены в сущности
     * @param entity обновляемая сущность
     */
    @Override
    void updateEntityFromDto(GoodsUpdateDTO dto, @MappingTarget Goods entity);

    /**
     * Маппинг выбранного товара и его количества в DTO
     *
     * @param goods выбранный товар
     * @param quantity количество выбранного товара
     * @return {@link SelectedGoodsDTO} с информацией о выбранном товаре
     */
    @Mapping(target = "discountedPrice", expression = "java(PriceUtils.formatPrice(" +
            "PriceUtils.getDiscountedPrice(goods.getPrice(), goods.getPercentageDiscount())))")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "totalPrice", expression = "java(PriceUtils.formatPrice(" +
            "PriceUtils.getDiscountedPrice(goods.getPrice(), goods.getPercentageDiscount()) * quantity))")
    SelectedGoodsDTO toSelectedGoodsDTO(Goods goods, int quantity);

    /**
     * Маппинг списка товаров (в корзине или заказе) в набор DTO
     *
     * @param goodsList список товаров
     * @return набор {@link Set} выбранных товаров {@link SelectedGoodsDTO}
     */
    default Set<SelectedGoodsDTO> toSelectedGoodsDTOSet(List<Goods> goodsList) {
        Set<Goods> goodsSet = new HashSet<>(goodsList);
        return goodsSet.stream()
                .map(goods -> {
                            int quantity = Collections.frequency(goodsList, goods);
                            return toSelectedGoodsDTO(goods, quantity);
                        }
                )
                .collect(Collectors.toSet());
    }

}
