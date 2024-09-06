package com.online.shop.service;

import com.online.shop.dto.request.CustomerCreationDTO;
import com.online.shop.dto.request.CustomerUpdateDTO;
import com.online.shop.dto.response.CustomerResponseDTO;
import com.online.shop.dto.response.GoodsResponseDTO;
import com.online.shop.entity.Customer;

import java.util.List;
import java.util.UUID;

/**
 * Фасад-сервис слоя представления для управления DTO на основе сущности {@link Customer}
 */
public interface CustomerFacadeService
        extends BaseFacadeService<CustomerCreationDTO, CustomerUpdateDTO, CustomerResponseDTO> {

    /**
     * Выборка всех товаров в корзине покупателя
     *
     * @return {@link List} - список всех товаров {@link GoodsResponseDTO} в корзине покупателя
     */
    List<GoodsResponseDTO> findAllGoodsInCustomerCart(UUID id);

    /**
     * Выборка покупателей по состоянию(активен или заблокирован) аккаунта
     *
     * @param enabled состояние аккаунта {@link boolean}
     * @return {@link List} - список всех покупателей {@link CustomerResponseDTO} по указанному состоянию аккаунта
     * {@code enabled}
     */
    List<CustomerResponseDTO> findAllCustomersByEnabled(boolean enabled);

}
