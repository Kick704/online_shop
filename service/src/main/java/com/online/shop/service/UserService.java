package com.online.shop.service;

import com.online.shop.entity.User;
import com.online.shop.entity.Goods;
import com.online.shop.enums.RoleEnum;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Интерфейс для управления сущностью {@link User} на сервисном слое
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
    List<User> findAllUsersByEnabled(boolean enabled);

    /**
     * Добавление/обновление пользователя в БД
     *
     * @param user сущность Пользователь {@link User}
     */
    void save(User user);

}
