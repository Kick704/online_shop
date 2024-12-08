package com.online.shop.service;

import com.online.shop.dao.UserRepository;
import com.online.shop.entity.Role;
import com.online.shop.entity.User;
import com.online.shop.entity.Goods;
import com.online.shop.exception_handling.CommonRuntimeException;
import com.online.shop.exception_handling.ErrorCode;
import com.online.shop.util.PriceUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Сервис для управления сущностью {@link User}
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    /**
     * Выборка пользователя по id
     *
     * @param id идентификатор пользователя {@link UUID}
     * @return {@link User} - пользователь по указанному {@code id}, или выбрасывается исключение
     * {@link CommonRuntimeException}, если такого пользователя нет
     */
    @Override
    public User findById(UUID id) {
        return userRepository.findUserById(id)
                .orElseThrow(() -> new CommonRuntimeException(
                        ErrorCode.NOT_FOUND,
                        String.format("Пользователь с ID %s не найден", id))
                );
    }

    /**
     * Выборка всех пользователей
     *
     * @return {@link List} - список всех пользователей {@link User}, или выбрасывается исключение
     * {@link CommonRuntimeException}, если пользователей ещё нет
     */
    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAllUsers();
        if (users.isEmpty()) {
            throw new CommonRuntimeException(ErrorCode.NOT_FOUND, "Ни один пользователь не найден в БД");
        }
        return users;
    }

    /**
     * Получение вошедшего в систему (авторизованного) пользователя
     *
     * @param principal информация об авторизованном пользователе {@link Principal}
     * @return сущность {@link User} - пользователь, вошедший в систему
     */
    @Override
    public User getCurrentUser(Principal principal) {
        return userRepository.findUserByEmail(principal.getName())
                .orElseThrow(() -> new CommonRuntimeException(
                        ErrorCode.AUTHENTICATION_ERROR,
                        String.format("Ошибка аутентификации пользователя с email %s", principal.getName()))
                );
    }

    /**
     * Получение актуальной корзины с учетом количества товаров на складе
     *
     * @param user сущность Пользователь
     * @return {@link List} - список всех товаров {@link Goods} в корзине пользователя после актуализации
     */
    @Override
    @Transactional
    public List<Goods> getActualCart(User user) {
        if (user == null) {
            throw new CommonRuntimeException(ErrorCode.INTERNAL_SERVER_ERROR, "User: получен пустой объект");
        }
        List<Goods> originCart = user.getGoodsInCart();
        List<Goods> actualCart = new ArrayList<>(originCart);
        if (!originCart.isEmpty()) {
            Set<Goods> uniqueGoods = new HashSet<>(originCart);
            uniqueGoods.forEach(goods -> {
                        int countInStock = goods.getCount();
                        int countInCart = Collections.frequency(originCart, goods);
                        while (countInStock < countInCart) {
                            actualCart.remove(goods);
                            countInCart--;
                        }
                    }
            );
            user.setGoodsInCart(actualCart);
            update(user);
        }
        return actualCart;
    }

    /**
     * Выборка всех товаров в корзине пользователя
     *
     * @param id идентификатор пользователя {@link UUID}
     * @return {@link List} - список всех товаров {@link Goods} в корзине пользователя, или выбрасывается исключение
     * (пока в разработке)
     */
    @Override
    public List<Goods> findAllGoodsInUserCart(UUID id) {
        User user = findById(id);
        List<Goods> goodsInCart = getActualCart(user);
        if (goodsInCart.isEmpty()) {
            throw new CommonRuntimeException(
                    ErrorCode.NOT_FOUND,
                    String.format("Корзина пользователя c ID %s пуста", id)
            );
        }
        return goodsInCart;
    }

    /**
     * Выборка всех товаров в корзине авторизованного пользователя
     *
     * @param principal авторизованный пользователь {@link Principal}
     * @return {@link List} - список всех товаров {@link Goods} в корзине авторизованного пользователя
     */
    @Override
    public List<Goods> findAllGoodsInCurrentUserCart(Principal principal) {
        User user = getCurrentUser(principal);
        List<Goods> goodsInCart = getActualCart(user);
        if (goodsInCart.isEmpty()) {
            throw new CommonRuntimeException(ErrorCode.NOT_FOUND, "Ваша корзина пуста");
        }
        return goodsInCart;
    }

    /**
     * Выборка пользователей по состоянию(активен или заблокирован) аккаунта
     *
     * @param enabled состояние аккаунта {@link boolean}
     * @return {@link List} - список всех пользователей {@link User} по указанному состоянию аккаунта, или
     * выбрасывается исключение {@link CommonRuntimeException}, если таких пользователей нет
     */
    @Override
    public List<User> findAllByEnabled(boolean enabled) {
        List<User> users = userRepository.findAllUsersByEnabled(enabled);
        if (users.isEmpty()) {
            throw new CommonRuntimeException(
                    ErrorCode.NOT_FOUND,
                    String.format("Ни один пользователь не найден по указанному состоянию аккаунта '%s'",
                            enabled ? "Активен" : "Заблокирован")
            );
        }
        return users;
    }

    /**
     * Добавление нового пользователя в БД
     *
     * @param user сущность Пользователь {@link User}
     */
    @Override
    @Transactional
    public void create(User user) {
        if (user == null) {
            throw new CommonRuntimeException(ErrorCode.BAD_REQUEST, "User: предан пустой объект для сохранения");
        }
        Role role = roleService.findByName("CUSTOMER");
        user.setRoles(Set.of(role));
        userRepository.save(user);
    }

    /**
     * Обновление пользователя в БД
     *
     * @param user сущность Пользователь {@link User}
     */
    @Override
    public void update(User user) {
        if (user == null) {
            throw new CommonRuntimeException(ErrorCode.BAD_REQUEST, "User: предан пустой объект для сохранения");
        }
        userRepository.save(user);
    }

    /**
     * Обновление пользователя после оформления заказа
     *
     * @param user сущность Пользователь {@link User}
     * @param orderAmount сумма заказа
     */
    @Override
    public void updateAfterOrder(User user, double orderAmount) {
        clearCart(user);
        double updatedBalance = PriceUtils.formatPrice(user.getBalance() - orderAmount);
        user.setBalance(updatedBalance);
        update(user);
    }

    /**
     * Очистка корзины авторизованного пользователя
     *
     * @param principal информация об авторизованном пользователе {@link Principal}
     */
    @Override
    public void clearCurrentUserCart(Principal principal) {
        findAllGoodsInCurrentUserCart(principal);
        User user = getCurrentUser(principal);
        clearCart(user);
        update(user);
    }

    /**
     * Очистка корзины пользователя.
     * <p>Для внутреннего использования во избежание дублирования кода
     *
     * @param user сущность Пользователь
     */
    private void clearCart(User user) {
        if (user == null) {
            throw new CommonRuntimeException(ErrorCode.BAD_REQUEST, "Пользователь не найден");
        }
        user.getGoodsInCart().clear();
    }

    /**
     * Удаление пользователя по id
     *
     * @param id идентификатор пользователя {@link UUID}
     */
    @Override
    public void deleteById(UUID id) {
        if (userRepository.deleteUserById(id) == 0) {
            throw new CommonRuntimeException(
                    ErrorCode.INTERNAL_SERVER_ERROR,
                    String.format("Пользователь с ID %s не найден или не может быть удалён", id)
            );
        }
    }

    /**
     * Удаление авторизованного пользователя с завершением сессии
     *
     * @param principal информация об авторизованном пользователе {@link Principal}
     * @param request {@link HttpServletRequest} для завершения сессии
     */
    @Override
    public void deleteCurrentUser(Principal principal, HttpServletRequest request) {
        UUID currentUserId = getCurrentUser(principal).getId();
        if (userRepository.deleteUserById(currentUserId) == 0) {
            throw new CommonRuntimeException(
                    ErrorCode.INTERNAL_SERVER_ERROR,
                    "Пользователь не найден или не может быть удалён"
            );
        }
        new SecurityContextLogoutHandler().logout(request, null, null);
    }

    /**
     * Проверка номера телефона пользователя на уникальность в БД
     *
     * @param phoneNumber номер телефона пользователя
     */
    @Override
    public void validatePhoneNumberUniqueness(String phoneNumber) {
        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new CommonRuntimeException(
                    ErrorCode.CONFLICT,
                    "Пользователь с таким номером телефона уже зарегистрирован"
            );
        }
    }

    /**
     * Проверка email пользователя на уникальность в БД
     *
     * @param email электронная почта пользователя
     */
    @Override
    public void validateEmailUniqueness(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new CommonRuntimeException(ErrorCode.CONFLICT, "Пользователь с таким email уже зарегистрирован");
        }
    }

    /**
     * Загрузка пользователя из БД для аутентификации по email
     *
     * @param email электронная почта пользователя
     * @return пользователь со всеми данными для аутентификации
     * @throws UsernameNotFoundException если пользователь не найден
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("Пользователь с email %s не найден", email))
                );
    }

}
