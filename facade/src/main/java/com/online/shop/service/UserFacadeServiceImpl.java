package com.online.shop.service;

import com.online.shop.dto.request.creation.UserCreationDTO;
import com.online.shop.dto.request.update.UserUpdateDTO;
import com.online.shop.dto.response.SelectedGoodsDTO;
import com.online.shop.dto.response.UserResponseDTO;
import com.online.shop.dto.response.GoodsResponseDTO;
import com.online.shop.dto.response.InformationDTO;
import com.online.shop.entity.User;
import com.online.shop.mapper.UserMapper;
import com.online.shop.mapper.GoodsMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Фасад-сервис слоя представления для управления DTO на основе сущности {@link User}
 */
@Service
public class UserFacadeServiceImpl implements UserFacadeService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * Выборка пользователя по id
     *
     * @param id идентификатор пользователя {@link UUID}
     * @return {@link UserResponseDTO} - пользователь по указанному {@code id}
     */
    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO findById(UUID id) {
        return userMapper.toDTO(userService.findById(id));
    }

    /**
     * Выборка всех пользователей
     *
     * @return {@link List} - список всех пользователей {@link UserResponseDTO}
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> findAll() {
        return userMapper.toDTOList(userService.findAll());
    }

    /**
     * Получение вошедшего в систему (авторизованного) пользователя
     *
     * @param principal информация об авторизованном пользователе {@link Principal}
     * @return DTO {@link UserResponseDTO} - пользователь, вошедший в систему
     */
    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO getCurrentUser(Principal principal) {
        return userMapper.toDTO(userService.getCurrentUser(principal));
    }

    /**
     * Выборка всех товаров в корзине пользователя
     *
     * @param id идентификатор пользователя {@link UUID}
     * @return {@link Set} - список всех товаров {@link GoodsResponseDTO} в корзине пользователя
     */
    @Override
    @Transactional(readOnly = true)
    public Set<SelectedGoodsDTO> findAllGoodsInUserCart(UUID id) {
        return goodsMapper.toSelectedGoodsDTOSet(userService.findAllGoodsInUserCart(id));
    }

    /**
     * Выборка всех товаров в корзине авторизованного пользователя
     *
     * @param principal информация об авторизованном пользователе {@link Principal}
     * @return {@link Set} - список всех товаров {@link GoodsResponseDTO} в корзине авторизованного пользователя
     */
    @Override
    @Transactional(readOnly = true)
    public Set<SelectedGoodsDTO> findAllGoodsInCurrentUserCart(Principal principal) {
        return goodsMapper.toSelectedGoodsDTOSet(userService.findAllGoodsInCurrentUserCart(principal));
    }

    /**
     * Выборка пользователей по состоянию(активен или заблокирован) аккаунта
     *
     * @param enabled состояние аккаунта {@link boolean}
     * @return {@link List} - список всех пользователей {@link UserResponseDTO} по указанному состоянию аккаунта
     * {@code enabled}
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> findAllByEnabled(boolean enabled) {
        return userMapper.toDTOList(userService.findAllByEnabled(enabled));
    }

    /**
     * Добавление нового пользователя в БД
     *
     * @param userCreationDTO DTO новый Пользователь {@link UserCreationDTO}
     * @return DTO Пользователь {@link UserResponseDTO}
     */
    @Override
    @Transactional
    public UserResponseDTO addNew(UserCreationDTO userCreationDTO) {
        User newUser = userMapper.toEntity(userCreationDTO);
        userService.validatePhoneNumberUniqueness(newUser.getPhoneNumber());
        userService.validateEmailUniqueness(newUser.getEmail());
        userService.create(newUser);
        return userMapper.toDTO(newUser);
    }

    /**
     * Обновление пользователя в БД
     *
     * @param id идентификатор пользователя {@link UUID}
     * @param userUpdateDTO DTO Пользователь {@link UserUpdateDTO} с изменёнными полями
     * @return обновлённый DTO Пользователь {@link UserResponseDTO}
     */
    @Override
    @Transactional
    public UserResponseDTO update(UUID id, UserUpdateDTO userUpdateDTO) {
        User user = userService.findById(id);
        if (!user.getPhoneNumber().equals(userUpdateDTO.getPhoneNumber())) {
            userService.validatePhoneNumberUniqueness(
                    userMapper.mapPhoneNumberToEntity(userUpdateDTO.getPhoneNumber())
            );
        }
        if (!user.getEmail().equals(userUpdateDTO.getEmail())) {
            userService.validateEmailUniqueness(userUpdateDTO.getEmail());
        }
        userMapper.updateEntityFromDto(userUpdateDTO, user);
        userService.update(user);
        return userMapper.toDTO(user);
    }

    /**
     * Обновление авторизованного пользователя в БД
     *
     * @param principal информация об авторизованном пользователе {@link Principal}
     * @param userUpdateDTO DTO Пользователь {@link UserUpdateDTO} с изменёнными полями
     * @return обновлённый DTO Пользователь {@link UserResponseDTO}
     */
    @Override
    @Transactional
    public UserResponseDTO updateCurrentUser(Principal principal, UserUpdateDTO userUpdateDTO) {
        UUID currentUserId = userService.getCurrentUser(principal).getId();
        return update(currentUserId, userUpdateDTO);
    }

    /**
     * Очистка корзины авторизованного пользователя
     *
     * @param principal информация об авторизованном пользователе {@link Principal}
     * @return {@link InformationDTO} с сообщением о результате
     */
    @Override
    public InformationDTO clearCurrentUserCart(Principal principal) {
        userService.clearCurrentUserCart(principal);
        return new InformationDTO("Ваша корзина очищена");
    }

    /**
     * Удаление пользователя по id
     *
     * @param id идентификатор пользователя {@link UUID}
     * @return {@link InformationDTO} с сообщением о результате
     */
    @Override
    @Transactional
    public InformationDTO deleteById(UUID id) {
        userService.deleteById(id);
        return new InformationDTO(String.format("Пользователь с ID %s удалён", id));
    }

    /**
     * Удаление авторизованного пользователя
     *
     * @param principal информация об авторизованном пользователе {@link Principal}
     * @param request {@link HttpServletRequest} для завершения сессии
     * @return {@link InformationDTO} с сообщением о результате
     */
    @Override
    @Transactional
    public InformationDTO deleteCurrentUser(Principal principal, HttpServletRequest request) {
        userService.deleteCurrentUser(principal, request);
        return new InformationDTO("Аккаунт успешно удалён");
    }

}
