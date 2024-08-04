package com.online.shop.service;

import com.online.shop.dto.CustomerDTO;
import com.online.shop.dto.GoodsDTO;
import com.online.shop.entity.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerFacadeService {

    /**
     * Выборка покупателя по id
     *
     * @param id идентификатор покупателя {@link UUID}
     * @return {@link CustomerDTO} - покупатель по указанному {@code id}
     */
    CustomerDTO findCustomerById(UUID id);

    /**
     * Выборка всех покупателей
     *
     * @return {@link List} - список всех покупателей {@link CustomerDTO}
     */
    List<CustomerDTO> findAllCustomers();

    /**
     * Выборка всех товаров в корзине покупателя
     *
     * @return {@link List} - список всех товаров {@link GoodsDTO} в корзине покупателя
     */
    List<GoodsDTO> findAllGoodsInCustomerCart(UUID id);

    /**
     * Выборка покупателей по состоянию(активен или заблокирован) аккаунта
     *
     * @param enabled состояние аккаунта {@link boolean}
     * @return {@link List} - список всех покупателей {@link CustomerDTO} по указанному состоянию аккаунта
     * {@code enabled}
     */
    List<CustomerDTO> findAllCustomersByEnabled(boolean enabled);

    /**
     * Добавление нового покупателя в БД
     *
     * @param customer сущность Покупатель {@link Customer}
     * @return DTO Покупатель {@link CustomerDTO}
     */
    CustomerDTO addNewCustomer(Customer customer);

    /**
     * Обновление покупателя в БД
     *
     * @param id идентификатор покупателя {@link UUID}
     * @param customerDTO DTO Покупатель {@link CustomerDTO} с изменёнными полями
     * @return обновлённый DTO Покупатель {@link CustomerDTO}
     */
    CustomerDTO updateCustomer(UUID id, CustomerDTO customerDTO);

    /**
     * Удаление покупателя по id
     *
     * @param id идентификатор покупателя {@link UUID}
     */
    void deleteCustomerById(UUID id);

}
