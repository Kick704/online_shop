package com.online.shop.service;

import com.online.shop.entity.User;
import com.online.shop.entity.Goods;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

/**
 * Сервис для управления сущностью {@link User}
 */
public interface UserService extends BaseService<User>, UserDetailsService {

    /**
     * Выборка всех товаров в корзине пользователя
     *
     * @param id идентификатор пользователя {@link UUID}
     * @return {@link List} - список всех товаров {@link Goods} в корзине пользователя
     */
    List<Goods> findAllGoodsInUserCart(UUID id);

    /**
     * Получение общей стоимости товаров в корзине пользователя
     * @param id идентификатор пользователя {@link UUID}
     * @return Общая стоимость товаров в корзине пользователя
     */
    double getAmountOfGoodsInUserCart(UUID id);

    /**
     * Выборка пользователей по состоянию(активен или заблокирован) аккаунта
     *
     * @param enabled состояние аккаунта {@link boolean}
     * @return {@link List} - список всех пользователей по указанному состоянию аккаунта {@code enabled}
     */
    List<User> findAllByEnabled(boolean enabled);

    /**
     * Добавление/обновление пользователя в БД
     *
     * @param user сущность Пользователь {@link User}
     */
    void save(User user);

    /**
     * Проверка номера телефона пользователя на уникальность в БД
     *
     * @param phoneNumber номер телефона пользователя
     */
    void validatePhoneNumberUniqueness(String phoneNumber);

    /**
     * Проверка email пользователя на уникальность в БД
     *
     * @param email электронная почта пользователя
     */
    void validateEmailUniqueness(String email);

}
