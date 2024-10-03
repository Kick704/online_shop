package com.online.shop.controller;

import com.online.shop.dto.request.creation.UserCreationDTO;
import com.online.shop.dto.request.update.UserUpdateDTO;
import com.online.shop.dto.response.UserResponseDTO;
import com.online.shop.dto.response.GoodsResponseDTO;
import com.online.shop.dto.response.InformationDTO;
import com.online.shop.service.UserFacadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST-контроллер для взаимодействия с пользователями интернет-магазина
 */
@RestController
@RequestMapping(ApiPath.API_BASE + "/user")
@Tag(name = "Пользователи", description = "Взаимодействие с пользователями интернет-магазина")
public class UserController {

    @Autowired
    private UserFacadeService userFacadeService;

    /**
     * Обработчик GET запроса для получения списка пользователей
     *
     * @return {@link List} список пользователей {@link UserResponseDTO}
     */
    @PreAuthorize("hasAuthority(T(com.online.shop.enums.PrivilegeEnum).MANAGE_USERS.name())")
    @GetMapping
    @Operation(summary = "Получение всех пользователей",
            description = "Позволяет получить список всех пользователей(клиентов) интернет-магазина")
    public List<UserResponseDTO> getAllUsers() {
        return userFacadeService.findAll();
    }

    /**
     * Обработчик GET запроса для получения информации о пользователе по его {@code id}
     *
     * @param id идентификатор пользователя {@link UUID}
     * @return DTO {@link UserResponseDTO}, содержащий информацию о пользователе
     */
    @GetMapping("/{id}")
    @Operation(summary = "Получение пользователя по ID", description = "Позволяет получить пользователя по его ID")
    public UserResponseDTO getUser(@PathVariable UUID id) {
        return userFacadeService.findById(id);
    }

    /**
     * Обработчик GET запроса для получения информации о корзине пользователе по его {@code id}
     *
     * @param id идентификатор пользователя {@link UUID}
     * @return {@link List} список товаров {@link GoodsResponseDTO} в корзине пользователя
     */
    @GetMapping(value = "/cart", params = "id")
    @Operation(summary = "Получение корзины пользователя",
            description = "Позволяет получить список товаров в корзине по ID пользователя")
    public List<GoodsResponseDTO> getUserCart(@RequestParam UUID id) {
        return userFacadeService.findAllGoodsInUserCart(id);
    }

    /**
     * Обработчик GET запроса для получения списка пользователей по состоянию(активен или заблокирован) аккаунта
     *
     * @param enabled состояние аккаунта {@link boolean}
     * @return {@link List} список пользователей {@link UserResponseDTO} по указанному состоянию аккаунта {@code enabled}
     */
    @GetMapping(value = "/by-enabled", params = "enabled")
    @Operation(summary = "Получение всех пользователей по состоянию аккаунта",
            description = "Позволяет получить список всех активных/неактивных аккаунтов пользователей")
    public List<UserResponseDTO> getAllUsersByEnabled(@RequestParam boolean enabled) {
        return userFacadeService.findAllUsersByEnabled(enabled);
    }

    /**
     * Обработчик POST запроса для регистрации пользователя
     *
     * @param userCreationDTO DTO {@link UserCreationDTO}, содержащая информацию для регистрации пользователя
     * @return DTO {@link UserResponseDTO}, содержащий информацию о зарегистрированном пользователе
     */
    @PostMapping
    @Operation(summary = "Регистрация пользователя",
            description = "Позволяет зарегистрировать нового пользователя")
    public UserResponseDTO addNewUser(@Valid @RequestBody UserCreationDTO userCreationDTO) {
        return userFacadeService.addNew(userCreationDTO);
    }

    /**
     * Обработчик PUT запроса для обновления информации о пользователе
     *
     * @param id                идентификатор пользователя {@link UUID}
     * @param userUpdateDTO DTO {@link UserUpdateDTO}, содержащий новую информацию о пользователе
     * @return DTO {@link UserResponseDTO} с обновленной информацией о пользователе
     */
    @PutMapping("/{id}")
    @Operation(summary = "Изменение информации о пользователе", description = "Позволяет изменить данные пользователя")
    public UserResponseDTO updateUser(@PathVariable UUID id,
                                          @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        return userFacadeService.update(id, userUpdateDTO);
    }

    /**
     * Обработчик DELETE запроса для удаления пользователя
     *
     * @param id идентификатор пользователя {@link UUID}
     * @return {@link InformationDTO} с сообщением о результате
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление пользователя", description = "Позволяет удалить пользователя по его ID")
    public InformationDTO deleteUser(@PathVariable UUID id) {
        return userFacadeService.deleteById(id);
    }


}
