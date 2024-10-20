package com.online.shop.service;

import com.online.shop.entity.Goods;

import java.util.List;

/**
 * Сервис для управления сущностью {@link Goods}
 */
public interface GoodsService extends BaseService<Goods> {

    /**
     * Выборка товаров по названию
     *
     * @param name название товара {@link String}
     * @return {@link List} - список товаров по указанному названию {@code name}
     */
    List<Goods> findAllByName(String name);

    /**
     * Добавление/обновление товара в БД
     *
     * @param goods сущность Товар {@link Goods}
     */
    void save(Goods goods);

}
