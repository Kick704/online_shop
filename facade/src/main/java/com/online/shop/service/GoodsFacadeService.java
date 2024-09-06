package com.online.shop.service;

import com.online.shop.dto.request.GoodsCreationDTO;
import com.online.shop.dto.request.GoodsUpdateDTO;
import com.online.shop.dto.response.GoodsResponseDTO;
import com.online.shop.entity.Goods;

import java.util.List;

/**
 * Фасад-сервис слоя представления для управления DTO на основе сущности {@link Goods}
 */
public interface GoodsFacadeService extends BaseFacadeService<GoodsCreationDTO, GoodsUpdateDTO, GoodsResponseDTO> {

    /**
     * Выборка товаров по названию
     *
     * @param name наименование товара {@link String}
     * @return {@link List} - список товаров {@link GoodsResponseDTO} по указанному наименованию {@code enabled}
     */
    List<GoodsResponseDTO> findAllGoodsByName(String name);

//    /**
//     * Добавление товара в корзину покупателя
//     *
//     * @param goodsId    идентификатор товара {@link UUID}
//     * @param customerId идентификатор покупателя {@link UUID}
//     * @return {@link List} - список товаров {@link GoodsResponseDTO} в корзине покупателя
//     */
//    List<GoodsResponseDTO> addGoodsToCustomerCart(UUID goodsId, UUID customerId);
//
//    /**
//     * Удаление товара из корзины покупателя
//     *
//     * @param goodsId    идентификатор товара {@link UUID}
//     * @param customerId идентификатор покупателя {@link UUID}
//     * @return {@link InformationDTO} с сообщением о результате
//     */
//    InformationDTO removeGoodsFromCustomerCart(UUID goodsId, UUID customerId);

}
