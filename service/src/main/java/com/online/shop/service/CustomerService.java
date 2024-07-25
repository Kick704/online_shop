package com.online.shop.service;

import com.online.shop.entity.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    /**
     * Выборка покупателя по id
     * @param id идентификатор покупателя {@link UUID}
     * @return {@link Customer} - покупатель по указанному {@code id}, или {@code null}, если такого покупателя нет
     */
    Customer findCustomerById(UUID id);

    /**
     * Выборка всех покупателей
     * @return {@link List} - список всех покупателей, или {@code null}, если покупателей еще нет
     */
    List<Customer> findAllCustomers();

    /**
     * Выборка покупателей по состоянию(активен или заблокирован) аккаунта
     * @param enabled состояние аккаунта {@link boolean}
     * @return {@link List} - список всех покупателей по указанному состоянию аккаунта, или {@code null},
     * если таких покупателей нет
     */
    List<Customer> findAllCustomersByEnabled(boolean enabled);

    /**
     * Удаление покупателя по id
     * @param id идентификатор покупателя {@link UUID}
     */
    void deleteCustomerById(UUID id);

}
