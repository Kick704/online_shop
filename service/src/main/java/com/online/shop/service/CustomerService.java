package com.online.shop.service;

import com.online.shop.entity.Customer;
import com.online.shop.entity.Goods;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

/**
 * Интерфейс для управления сущностью {@link Customer} на сервисном слое
 */
public interface CustomerService extends BaseService<Customer>, UserDetailsService {

    /**
     * Добавление/обновление покупателя в БД
     *
     * @param customer сущность Покупатель {@link Customer}
     */
    void save(Customer customer);

    /**
     * Выборка всех товаров в корзине покупателя
     *
     * @param id идентификатор покупателя {@link UUID}
     * @return {@link List} - список всех товаров {@link Goods} в корзине покупателя
     */
    List<Goods> findAllGoodsInCustomerCart(UUID id);

    /**
     * Получение общей стоимости товаров в корзине покупателя
     * @param id идентификатор покупателя {@link UUID}
     * @return Общая стоимость товаров в корзине покупателя
     */
    double getAmountOfGoodsInCustomerCart(UUID id);

    /**
     * Выборка покупателей по состоянию(активен или заблокирован) аккаунта
     *
     * @param enabled состояние аккаунта {@link boolean}
     * @return {@link List} - список всех покупателей по указанному состоянию аккаунта {@code enabled}
     */
    List<Customer> findAllCustomersByEnabled(boolean enabled);

}
