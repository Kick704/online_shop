package com.online.shop.service;

import com.online.shop.dto.request.creation.GoodsCreationDTO;
import com.online.shop.dto.request.update.GoodsUpdateDTO;
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
//     * Добавление товара в корзину пользователя
//     *
//     * @param goodsId    идентификатор товара {@link UUID}
//     * @param userId идентификатор пользователя {@link UUID}
//     * @return {@link List} - список товаров {@link GoodsResponseDTO} в корзине пользователя
//     */
//    List<GoodsResponseDTO> addGoodsToUserCart(UUID goodsId, UUID userId);
//
//    /**
//     * Удаление товара из корзины пользователя
//     *
//     * @param goodsId    идентификатор товара {@link UUID}
//     * @param userId идентификатор пользователя {@link UUID}
//     * @return {@link InformationDTO} с сообщением о результате
//     */
//    InformationDTO removeGoodsFromUserCart(UUID goodsId, UUID userId);

}
