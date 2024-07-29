package com.online.shop.service;

import com.online.shop.dto.GoodsDTO;

import java.util.List;
import java.util.UUID;

public interface GoodsDTOService {

    /**
     * Выборка товара по id
     * @param id идентификатор товара {@link UUID}
     * @return {@link GoodsDTO} - товар по указанному {@code id}, или {@code null}, если такого товара нет
     */
    GoodsDTO findGoodsById(UUID id);

    /**
     * Выборка всех товаров
     * @return {@link List} - список всех товаров, или {@code null}, если товаров еще нет
     */
    List<GoodsDTO> findAllGoods();

    /**
     * Выборка товаров по названию
     * @param name наименование товара {@link String}
     * @return {@link List} - список товаров по указанному наименованию, или {@code null}, если такого товара нет
     */
    List<GoodsDTO> findAllGoodsByName(String name);

    /**
     * Удаление товара по id
     * @param id идентификатор товара {@link UUID}
     */
    void deleteGoodsById(UUID id);

}
