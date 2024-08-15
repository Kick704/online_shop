package com.online.shop.service;

import com.online.shop.entity.Goods;

import java.util.List;

/**
 * Интерфейс для управления сущностью {@link Goods} на сервисном слое
 */
public interface GoodsService extends BaseService<Goods> {

    /**
     * Добавление/обновление товара в БД
     *
     * @param goods сущность Товар {@link Goods}
     */
    void save(Goods goods);

    /**
     * Выборка товаров по названию
     *
     * @param name наименование товара {@link String}
     * @return {@link List} - список товаров по указанному наименованию {@code name}
     */
    List<Goods> findAllGoodsByName(String name);

}
