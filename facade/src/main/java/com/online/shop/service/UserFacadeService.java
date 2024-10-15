package com.online.shop.service;

import com.online.shop.dto.request.creation.UserCreationDTO;
import com.online.shop.dto.request.update.UserUpdateDTO;
import com.online.shop.dto.response.UserResponseDTO;
import com.online.shop.dto.response.GoodsResponseDTO;
import com.online.shop.entity.User;

import java.util.List;
import java.util.UUID;

/**
 * Фасад-сервис для управления DTO на основе сущности {@link User}
 */
public interface UserFacadeService
        extends BaseFacadeService<UserCreationDTO, UserUpdateDTO, UserResponseDTO> {

    /**
     * Выборка всех товаров в корзине пользователя
     *
     * @return {@link List} - список всех товаров {@link GoodsResponseDTO} в корзине пользователя
     */
    List<GoodsResponseDTO> findAllGoodsInUserCart(UUID id);

    /**
     * Выборка пользователей по состоянию(активен или заблокирован) аккаунта
     *
     * @param enabled состояние аккаунта {@link boolean}
     * @return {@link List} - список всех пользователей {@link UserResponseDTO} по указанному состоянию аккаунта
     * {@code enabled}
     */
    List<UserResponseDTO> findAllByEnabled(boolean enabled);

}
