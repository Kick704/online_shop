package com.online.shop.service;

import com.online.shop.entity.Customer;
import com.online.shop.entity.Goods;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    /**
     * Выборка покупателя по id
     *
     * @param id идентификатор покупателя {@link UUID}
     * @return {@link Customer}, покупатель по указанному {@code id}
     */
    Customer findCustomerById(UUID id);

    /**
     * Выборка всех покупателей
     *
     * @return {@link List} - список всех покупателей {@link Customer}
     */
    List<Customer> findAllCustomers();

    /**
     * Выборка всех товаров в корзине покупателя
     *
     * @return {@link List} - список всех товаров {@link Goods} в корзине покупателя
     */
    List<Goods> findAllGoodsInCustomerCart(UUID id);

    /**
     * Выборка покупателей по состоянию(активен или заблокирован) аккаунта
     *
     * @param enabled состояние аккаунта {@link boolean}
     * @return {@link List} - список всех покупателей по указанному состоянию аккаунта {@code enabled}
     */
    List<Customer> findAllCustomersByEnabled(boolean enabled);

    /**
     * Добавление/обновление покупателя в БД
     *
     * @param customer сущность Покупатель {@link Customer}
     */
    void saveCustomer(Customer customer);

    /**
     * Удаление покупателя по id
     *
     * @param id идентификатор покупателя {@link UUID}
     */
    void deleteCustomerById(UUID id);

}
