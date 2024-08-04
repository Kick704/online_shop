package com.online.shop.service;

import com.online.shop.entity.Goods;

import java.util.List;
import java.util.UUID;

public interface GoodsService {

    /**
     * Выборка товара по id
     *
     * @param id идентификатор товара {@link UUID}
     * @return {@link Goods} - товар по указанному {@code id}
     */
    Goods findGoodsById(UUID id);

    /**
     * Выборка всех товаров
     *
     * @return {@link List} - список всех товаров {@link Goods}
     */
    List<Goods> findAllGoods();

    /**
     * Выборка товаров по названию
     *
     * @param name наименование товара {@link String}
     * @return {@link List} - список товаров по указанному наименованию {@code name}
     */
    List<Goods> findAllGoodsByName(String name);

    /**
     * Добавление/обновление товара в БД
     *
     * @param goods сущность Товар {@link Goods}
     */
    void saveGoods(Goods goods);

    /**
     * Удаление товара по id
     *
     * @param id идентификатор товара {@link UUID}
     */
    void deleteGoodsById(UUID id);

}
