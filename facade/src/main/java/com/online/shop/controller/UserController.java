package com.online.shop.controller;

import com.online.shop.dto.request.creation.UserCreationDTO;
import com.online.shop.dto.request.update.UserUpdateDTO;
import com.online.shop.dto.response.UserResponseDTO;
import com.online.shop.dto.response.InformationDTO;
import com.online.shop.service.UserFacadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

/**
 * REST-контроллер для управления пользователями интернет-магазина
 */
@RestController
@RequestMapping(ApiPath.API_BASE + "/user")
@Tag(name = "Пользователи", description = "Управление пользователями интернет-магазина")
public class UserController {

    @Autowired
    private UserFacadeService userFacadeService;

    /**
     * Обработчик GET запроса для получения списка пользователей
     *
     * @return {@link List} список пользователей {@link UserResponseDTO}
     */
    @PreAuthorize("hasAuthority('MANAGE_USERS')")
    @GetMapping
    @Operation(summary = "Получение всех пользователей",
            description = "Позволяет получить список всех пользователей интернет-магазина")
    public List<UserResponseDTO> getAllUsers() {
        return userFacadeService.findAll();
    }

    /**
     * Обработчик GET запроса для получения информации о пользователе по его {@code id}
     *
     * @param id идентификатор пользователя {@link UUID}
     * @return DTO {@link UserResponseDTO}, содержащий информацию о пользователе
     */
    @PreAuthorize("hasAuthority('MANAGE_USERS')")
    @GetMapping("/{id}")
    @Operation(summary = "Получение пользователя по ID", description = "Позволяет получить пользователя по его ID")
    public UserResponseDTO getUser(@PathVariable UUID id) {
        return userFacadeService.findById(id);
    }

    /**
     * Обработчик GET запроса для получения информации об авторизованном пользователе
     *
     * @param principal информация об авторизованном пользователе {@link Principal}
     * @return DTO {@link UserResponseDTO}, содержащий информацию о пользователе
     */
    @PreAuthorize("hasAuthority('READ_USER')")
    @GetMapping("/current")
    @Operation(summary = "Получение текущего пользователя",
            description = "Позволяет получить авторизованного пользователя")
    public UserResponseDTO getCurrentUser(Principal principal) {
        return userFacadeService.getCurrentUser(principal);
    }

    /**
     * Обработчик GET запроса для получения списка пользователей по состоянию(активен или заблокирован) аккаунта
     *
     * @param enabled состояние аккаунта {@link boolean}
     * @return {@link List} список пользователей {@link UserResponseDTO} по указанному состоянию аккаунта {@code enabled}
     */
    @PreAuthorize("hasAuthority('MANAGE_USERS')")
    @GetMapping(value = "/by-enabled", params = "enabled")
    @Operation(summary = "Получение всех пользователей по состоянию аккаунта",
            description = "Позволяет получить список всех активных/неактивных аккаунтов пользователей")
    public List<UserResponseDTO> getAllUsersByEnabled(@RequestParam boolean enabled) {
        return userFacadeService.findAllByEnabled(enabled);
    }

    /**
     * Обработчик POST запроса для регистрации пользователя
     *
     * @param userCreationDTO DTO {@link UserCreationDTO}, содержащая информацию для регистрации пользователя
     * @return DTO {@link UserResponseDTO}, содержащий информацию о зарегистрированном пользователе
     */
    @PostMapping
    @Operation(summary = "Регистрация пользователя", description = "Позволяет зарегистрировать нового пользователя")
    public UserResponseDTO addNewUser(@Valid @RequestBody UserCreationDTO userCreationDTO) {
        return userFacadeService.addNew(userCreationDTO);
    }

    /**
     * Обработчик PUT запроса для обновления информации о пользователе
     *
     * @param id идентификатор пользователя {@link UUID}
     * @param userUpdateDTO DTO {@link UserUpdateDTO}, содержащий новую информацию о пользователе
     * @return DTO {@link UserResponseDTO} с обновленной информацией о пользователе
     */
    @PreAuthorize("hasAuthority('MANAGE_USERS')")
    @PutMapping("/{id}")
    @Operation(summary = "Изменение информации о пользователе", description = "Позволяет изменить данные пользователя")
    public UserResponseDTO updateUser(@PathVariable UUID id, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        return userFacadeService.update(id, userUpdateDTO);
    }

    /**
     * Обработчик PUT запроса для обновления информации об авторизованном пользователе
     *
     * @param principal информация об авторизованном пользователе {@link Principal}
     * @param userUpdateDTO DTO {@link UserUpdateDTO}, содержащий новую информацию о пользователе
     * @return DTO {@link UserResponseDTO} с обновленной информацией об авторизованном пользователе
     */
    @PreAuthorize("hasAuthority('EDIT_USER')")
    @PutMapping("/current")
    @Operation(summary = "Изменение информации о текущем пользователе",
            description = "Позволяет изменить данные авторизованного пользователя")
    public UserResponseDTO updateCurrentUser(Principal principal, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        return userFacadeService.updateCurrentUser(principal, userUpdateDTO);
    }

    /**
     * Обработчик DELETE запроса для удаления пользователя
     *
     * @param id идентификатор пользователя {@link UUID}
     * @return {@link InformationDTO} с сообщением о результате
     */
    @PreAuthorize("hasAuthority('MANAGE_USERS')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление пользователя", description = "Позволяет удалить пользователя по его ID")
    public InformationDTO deleteUser(@PathVariable UUID id) {
        return userFacadeService.deleteById(id);
    }

    /**
     * Обработчик DELETE запроса для удаления авторизованного пользователя
     *
     * @param principal информация об авторизованном пользователе {@link Principal}
     * @param request {@link HttpServletRequest} для завершения сессии
     * @return {@link InformationDTO} с сообщением о результате
     */
    @PreAuthorize("hasAuthority('DELETE_USER')")
    @DeleteMapping("/current")
    @Operation(summary = "Удаление текущего пользователя",
            description = "Позволяет удалить авторизованного пользователя")
    public InformationDTO deleteCurrentUser(Principal principal, HttpServletRequest request) {
        return userFacadeService.deleteCurrentUser(principal, request);
    }

}
