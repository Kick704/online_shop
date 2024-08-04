package com.online.shop.service;

import com.online.shop.dto.GoodsDTO;
import com.online.shop.entity.Goods;

import java.util.List;
import java.util.UUID;

public interface GoodsFacadeService {

    /**
     * Выборка товара по id
     *
     * @param id идентификатор товара {@link UUID}
     * @return {@link GoodsDTO} - товар по указанному {@code id}
     */
    GoodsDTO findGoodsById(UUID id);

    /**
     * Выборка всех товаров
     *
     * @return {@link List} - список всех товаров {@link GoodsDTO}
     */
    List<GoodsDTO> findAllGoods();

    /**
     * Выборка товаров по названию
     *
     * @param name наименование товара {@link String}
     * @return {@link List} - список товаров {@link GoodsDTO} по указанному наименованию {@code enabled}
     */
    List<GoodsDTO> findAllGoodsByName(String name);

    /**
     * Добавление нового товара в БД
     *
     * @param goods сущность Товар {@link Goods}
     * @return DTO Товар {@link GoodsDTO}
     */
    GoodsDTO addNewGoods(Goods goods);

    /**
     * Обновление товара в БД
     *
     * @param id          идентификатор товара {@link UUID}
     * @param goodsDTO DTO Товар {@link GoodsDTO} с изменёнными полями
     * @return обновлённый DTO Товар {@link GoodsDTO}
     */
    GoodsDTO updateGoods(UUID id, GoodsDTO goodsDTO);

    /**
     * Добавление товара в корзину покупателя
     *
     * @param goodsId идентификатор товара {@link UUID}
     * @param customerId идентификатор покупателя {@link UUID}
     * @return {@link List} - список товаров {@link GoodsDTO} в корзине покупателя
     */
    List<GoodsDTO> addGoodsToCustomerCart(UUID goodsId, UUID customerId);

    /**
     * Удаление товара по id
     *
     * @param id идентификатор товара {@link UUID}
     */
    void deleteGoodsById(UUID id);

    /**
     * Удаление товара из корзины покупателя
     *
     * @param goodsId идентификатор товара {@link UUID}
     * @param customerId идентификатор покупателя {@link UUID}
     */
    void removeGoodsFromCustomerCart(UUID goodsId, UUID customerId);

}
