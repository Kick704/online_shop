package com.online.shop.service;

import com.online.shop.entity.GoodsCategory;

import java.util.List;
import java.util.UUID;

public interface GoodsCategoryService {

    /**
     * Выборка категории товаров по id
     *
     * @param id идентификатор заказа {@link UUID}
     * @return {@link GoodsCategory} - категория товаров по указанному {@code id}
     */
    GoodsCategory findGoodsCategoryById(UUID id);

    /**
     * Выборка всех категорий товаров
     *
     * @return {@link List} - список всех категорий товаров
     */
    List<GoodsCategory> findAllGoodsCategory();

    /**
     * Выборка категории товаров по названию
     *
     * @param name наименование категории товаров {@link String}
     * @return {@link GoodsCategory} - категория товаров по указанному наименованию {@code name}
     */
    GoodsCategory findGoodsCategoryByName(String name);

    /**
     * Добавление/обновление категории товаров в БД
     *
     * @param category сущность Категория товаров {@link GoodsCategory}
     */
    void saveGoodsCategory(GoodsCategory category);

    /**
     * Удаление категории товара по id
     *
     * @param id идентификатор категории товара {@link UUID}
     */
    void deleteGoodsCategoryById(UUID id);

}
