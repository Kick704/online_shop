package com.online.shop.service;

import com.online.shop.entity.GoodsCategory;

/**
 * Интерфейс для управления сущностью {@link GoodsCategory} на сервисном слое
 */
public interface GoodsCategoryService extends BaseService<GoodsCategory> {

    /**
     * Добавление/обновление категории товаров в БД
     *
     * @param category сущность Категория товаров {@link GoodsCategory}
     */
    void save(GoodsCategory category);

    /**
     * Выборка категории товаров по названию
     *
     * @param name наименование категории товаров {@link String}
     * @return {@link GoodsCategory} - категория товаров по указанному наименованию {@code name}
     */
    GoodsCategory findGoodsCategoryByName(String name);

}
