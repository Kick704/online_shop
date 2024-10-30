package com.online.shop.service;

import com.online.shop.entity.Goods;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

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

    /**
     * Добавление товара в корзину текущего покупателя
     *
     * @param id идентификатор товара {@link UUID}
     * @param quantity количество товара для добавления
     * @param principal информация об авторизованном пользователе {@link Principal}
     */
    void addToCart(UUID id, int quantity, Principal principal);

    /**
     * Получение общей стоимости товаров в корзине покупателя
     *
     * @param goodsList список товаров в корзине {@link List}
     * @return общая стоимость товаров в корзине
     */
    double getCartTotalPrice(List<Goods> goodsList);

    /**
     * Вычитание товаров на складе на основе списка приобретаемых покупателем
     *
     * @param goodsList список товаров для приобретения {@link List}
     */
    void deductGoodsCount(List<Goods> goodsList);

}
