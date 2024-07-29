package com.online.shop.service;

import com.online.shop.dto.CustomerDTO;

import java.util.List;
import java.util.UUID;

public interface CustomerDTOService {

    /**
     * Выборка покупателя по id
     * @param id идентификатор покупателя {@link UUID}
     * @return {@link CustomerDTO} - покупатель по указанному {@code id}, или {@code null}, если такого покупателя нет
     */
    CustomerDTO findCustomerById(UUID id);

    /**
     * Выборка всех покупателей
     * @return {@link List} - список всех покупателей, или {@code null}, если покупателей еще нет
     */
    List<CustomerDTO> findAllCustomers();

    /**
     * Выборка покупателей по состоянию(активен или заблокирован) аккаунта
     * @param enabled состояние аккаунта {@link boolean}
     * @return {@link List} - список всех покупателей по указанному состоянию аккаунта, или {@code null},
     * если таких покупателей нет
     */
    List<CustomerDTO> findAllCustomersByEnabled(boolean enabled);

    /**
     * Удаление покупателя по id
     * @param id идентификатор покупателя {@link UUID}
     */
    void deleteCustomerById(UUID id);

}
