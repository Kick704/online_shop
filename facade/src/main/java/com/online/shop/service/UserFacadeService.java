package com.online.shop.service;

import com.online.shop.dto.request.creation.UserCreationDTO;
import com.online.shop.dto.request.update.UserUpdateDTO;
import com.online.shop.dto.response.UserResponseDTO;
import com.online.shop.dto.response.GoodsResponseDTO;
import com.online.shop.entity.User;

import java.security.Principal;
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
     * @param id идентификатор пользователя {@link UUID}
     * @return {@link List} - список всех товаров {@link GoodsResponseDTO} в корзине пользователя
     */
    List<GoodsResponseDTO> findAllGoodsInUserCart(UUID id);

    /**
     * Выборка всех товаров в корзине пользователя
     *
     * @param principal информация об авторизованном пользователе {@link Principal}
     * @return {@link List} - список всех товаров {@link GoodsResponseDTO} в корзине пользователя
     */
    List<GoodsResponseDTO> findAllGoodsInMyCart(Principal principal);

    /**
     * Выборка пользователей по состоянию(активен или заблокирован) аккаунта
     *
     * @param enabled состояние аккаунта {@link boolean}
     * @return {@link List} - список всех пользователей {@link UserResponseDTO} по указанному состоянию аккаунта
     * {@code enabled}
     */
    List<UserResponseDTO> findAllByEnabled(boolean enabled);

}
