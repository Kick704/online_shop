package com.online.shop.service;

import com.online.shop.entity.GoodsCategory;

import java.util.List;
import java.util.UUID;

public interface GoodsCategoryService {

    /**
     * Выборка категории товаров по id
     *
     * @param id идентификатор заказа {@link UUID}
     * @return {@link GoodsCategory} - категория товаров по указанному {@code id}, или {@code null},
     * если такой категории нет
     */
    GoodsCategory findGoodsCategoryById(UUID id);

    /**
     * Выборка всех категорий товаров
     * @return {@link List} - список всех категорий товаров, или {@code null}, если категорий еще нет
     */
    List<GoodsCategory> findAllGoodsCategory();

    /**
     * Выборка категории товаров по названию
     *
     * @param name наименование категории товаров {@link String}
     * @return {@link GoodsCategory} - категория товаров по указанному наименованию, или {@code null},
     * если такой категории нет
     */
    GoodsCategory findGoodsCategoryByName(String name);

    /**
     * Удаление категории товара по id
     * @param id идентификатор категории товара {@link UUID}
     */
    void deleteGoodsCategoryById(UUID id);

}
