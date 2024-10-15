package com.online.shop.service;

import com.online.shop.dao.UserRepository;
import com.online.shop.entity.Role;
import com.online.shop.entity.User;
import com.online.shop.entity.Goods;
import com.online.shop.exception_handling.CommonRuntimeException;
import com.online.shop.exception_handling.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
                        ErrorCode.ENTITY_NOT_FOUND,
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
            throw new CommonRuntimeException(ErrorCode.ENTITY_NOT_FOUND, "Ни один пользователь не найден в БД");
        }
        return users;
    }

    /**
     * Выборка всех товаров в корзине пользователя
     *
     * @return {@link List} - список всех товаров {@link Goods} в корзине пользователя, или выбрасывается исключение
     * (пока в разработке)
     */
    @Override
    public List<Goods> findAllGoodsInUserCart(UUID id) {
        User user = findById(id);
        List<Goods> goodsInCart = user.getGoodsInCart();
        if (goodsInCart.isEmpty()) {
            throw new CommonRuntimeException(
                    ErrorCode.EMPTY_CART,
                    String.format("Корзина пользователя c ID %s пуста", id)
            );
        }
        return goodsInCart;
    }

    /**
     * Получение общей стоимости товаров в корзине пользователя
     *
     * @param id идентификатор пользователя {@link UUID}
     * @return Общая стоимость товаров в корзине пользователя
     */
    @Override
    public double getAmountOfGoodsInUserCart(UUID id) {
        List<Goods> goodsInCart = findAllGoodsInUserCart(id);
        return goodsInCart.stream().mapToDouble(Goods::getPrice).sum();
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
                    ErrorCode.ENTITY_NOT_FOUND,
                    String.format("Ни один пользователь не найден по указанному состоянию аккаунта '%s'",
                            enabled ? "Активен" : "Заблокирован")
            );
        }
        return users;
    }

    /**
     * Добавление/обновление пользователя в БД
     *
     * @param user сущность Пользователь {@link User}
     */
    @Override
    public void save(User user) {
        if (user == null) {
            throw new CommonRuntimeException(
                    ErrorCode.OBJECT_REFERENCE_IS_NULL,
                    "User: предан пустой объект для сохранения"
            );
        }
        if (CollectionUtils.isEmpty(user.getRoles())) {
            Role role = roleService.findByName("CUSTOMER");
            user.setRoles(Set.of(role));
        }
        userRepository.save(user);
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
                    ErrorCode.ENTITY_NOT_FOUND,
                    String.format("Пользователь с ID %s не найден или не может быть удалён", id)
            );
        }
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
                    ErrorCode.UNIQUE_CONSTRAINT_VIOLATION,
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
            throw new CommonRuntimeException(
                    ErrorCode.UNIQUE_CONSTRAINT_VIOLATION,
                    "Пользователь с таким email уже зарегистрирован"
            );
        }
    }

    /**
     * Загрузка пользователя из БД для аутентификации по email
     *
     * @param email электронная почта пользователя
     * @return пользователь со всеми данными для аутентификации
     * @throws UsernameNotFoundException, если пользователь не найден
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new CommonRuntimeException(
                        ErrorCode.ENTITY_NOT_FOUND,
                        String.format("Пользователь с email %s не найден", email))
                );
    }

}
