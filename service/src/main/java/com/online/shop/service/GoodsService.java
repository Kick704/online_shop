package com.online.shop.service;

import com.online.shop.entity.Goods;

import java.util.List;
import java.util.UUID;

public interface GoodsService {

    /**
     * Выборка товара по id
     * @param id идентификатор товара {@link UUID}
     * @return {@link Goods} - товар по указанному {@code id}, или {@code null}, если такого товара нет
     */
    Goods findGoodsById(UUID id);

    /**
     * Выборка всех товаров
     * @return {@link List} - список всех товаров, или {@code null}, если товаров еще нет
     */
    List<Goods> findAllGoods();

    /**
     * Выборка товаров по названию
     * @param name наименование товара {@link String}
     * @return {@link List} - список товаров по указанному наименованию, или {@code null}, если такого товара нет
     */
    List<Goods> findAllGoodsByName(String name);

    /**
     * Удаление товара по id
     * @param id идентификатор товара {@link UUID}
     */
    void deleteGoodsById(UUID id);

}
