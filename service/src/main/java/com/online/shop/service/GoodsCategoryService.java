package com.online.shop.service;

import com.online.shop.entity.GoodsCategory;

/**
 * Сервис для управления сущностью {@link GoodsCategory}
 */
public interface GoodsCategoryService extends BaseService<GoodsCategory> {

    /**
     * Выборка категории товаров по названию
     *
     * @param name название категории товаров {@link String}
     * @return {@link GoodsCategory} - категория товаров по указанному названию {@code name}
     */
    GoodsCategory findByName(String name);

    /**
     * Добавление/обновление категории товаров в БД
     *
     * @param category сущность Категория товаров {@link GoodsCategory}
     */
    void save(GoodsCategory category);

    /**
     * Проверка названия категории товаров на уникальность в БД
     *
     * @param name название категории товаров
     */
    void validateNameUniqueness(String name);

}
