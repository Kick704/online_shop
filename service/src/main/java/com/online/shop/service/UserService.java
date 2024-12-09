package com.online.shop.service;

import com.online.shop.entity.User;
import com.online.shop.entity.Goods;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

/**
 * Сервис для управления сущностью {@link User}
 */
public interface UserService extends BaseService<User>, UserDetailsService {

    /**
     * Получение вошедшего в систему (авторизованного) пользователя
     *
     * @param principal информация об авторизованном пользователе {@link Principal}
     * @return сущность {@link User} - пользователь, вошедший в систему
     */
    User getCurrentUser(Principal principal);

    /**
     * Получение актуальной корзины с учетом количества товаров на складе
     *
     * @param user сущность Пользователь
     * @return {@link List} - список всех товаров {@link Goods} в корзине пользователя после актуализации
     */
    List<Goods> getActualCart(User user);

    /**
     * Выборка всех товаров в корзине пользователя
     *
     * @param id идентификатор пользователя {@link UUID}
     * @return {@link List} - список всех товаров {@link Goods} в корзине пользователя
     */
    List<Goods> findAllGoodsInUserCart(UUID id);

    /**
     * Выборка всех товаров в корзине авторизованного пользователя
     *
     * @param principal авторизованный пользователь {@link Principal}
     * @return {@link List} - список всех товаров {@link Goods} в корзине авторизованного пользователя
     */
    List<Goods> findAllGoodsInCurrentUserCart(Principal principal);

    /**
     * Выборка пользователей по состоянию(активен или заблокирован) аккаунта
     *
     * @param enabled состояние аккаунта {@link boolean}
     * @return {@link List} - список всех пользователей по указанному состоянию аккаунта {@code enabled}
     */
    List<User> findAllByEnabled(boolean enabled);

    /**
     * Добавление нового пользователя в БД
     *
     * @param user сущность Пользователь {@link User}
     */
    void create(User user);

    /**
     * Обновление пользователя в БД
     *
     * @param user сущность Пользователь {@link User}
     */
    void update(User user);

    /**
     * Очистка корзины авторизованного пользователя
     *
     * @param principal информация об авторизованном пользователе {@link Principal}
     */
    void clearCurrentUserCart(Principal principal);

    /**
     * Удаление авторизованного пользователя с завершением сессии
     *
     * @param principal информация об авторизованном пользователе {@link Principal}
     * @param request {@link HttpServletRequest} для завершения сессии
     */
    void deleteCurrentUser(Principal principal, HttpServletRequest request);

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
