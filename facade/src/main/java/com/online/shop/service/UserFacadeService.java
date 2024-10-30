package com.online.shop.service;

import com.online.shop.dto.request.creation.UserCreationDTO;
import com.online.shop.dto.request.update.UserUpdateDTO;
import com.online.shop.dto.response.InformationDTO;
import com.online.shop.dto.response.SelectedGoodsDTO;
import com.online.shop.dto.response.UserResponseDTO;
import com.online.shop.dto.response.GoodsResponseDTO;
import com.online.shop.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Фасад-сервис для управления DTO на основе сущности {@link User}
 */
public interface UserFacadeService
        extends BaseFacadeService<UserCreationDTO, UserUpdateDTO, UserResponseDTO> {

    /**
     * Получение вошедшего в систему (авторизованного) пользователя
     *
     * @param principal информация об авторизованном пользователе {@link Principal}
     * @return DTO {@link UserResponseDTO} - пользователь, вошедший в систему
     */
    UserResponseDTO getCurrentUser(Principal principal);

    /**
     * Выборка всех товаров в корзине пользователя
     *
     * @param id идентификатор пользователя {@link UUID}
     * @return {@link List} - список всех товаров {@link GoodsResponseDTO} в корзине пользователя
     */
    Set<SelectedGoodsDTO> findAllGoodsInUserCart(UUID id);

    /**
     * Выборка всех товаров в корзине авторизованного пользователя
     *
     * @param principal информация об авторизованном пользователе {@link Principal}
     * @return {@link List} - список всех товаров {@link GoodsResponseDTO} в корзине авторизованного пользователя
     */
    Set<SelectedGoodsDTO> findAllGoodsInCurrentUserCart(Principal principal);

    /**
     * Выборка пользователей по состоянию(активен или заблокирован) аккаунта
     *
     * @param enabled состояние аккаунта {@link boolean}
     * @return {@link List} - список всех пользователей {@link UserResponseDTO} по указанному состоянию аккаунта
     * {@code enabled}
     */
    List<UserResponseDTO> findAllByEnabled(boolean enabled);

    /**
     * Добавление нового пользователя в БД
     *
     * @param userCreationDTO DTO новый Пользователь {@link UserCreationDTO}
     * @return DTO Пользователь {@link UserResponseDTO}
     */
    UserResponseDTO addNew(UserCreationDTO userCreationDTO);

    /**
     * Обновление авторизованного пользователя в БД
     *
     * @param principal информация об авторизованном пользователе {@link Principal}
     * @param userUpdateDTO DTO Пользователь {@link UserUpdateDTO} с изменёнными полями
     * @return обновлённый DTO Пользователь {@link UserResponseDTO}
     */
    UserResponseDTO updateCurrentUser(Principal principal, UserUpdateDTO userUpdateDTO);

    /**
     * Удаление авторизованного пользователя
     *
     * @param principal информация об авторизованном пользователе {@link Principal}
     * @param request {@link HttpServletRequest} для завершения сессии
     * @return {@link InformationDTO} с сообщением о результате
     */
    InformationDTO deleteCurrentUser(Principal principal, HttpServletRequest request);

}
