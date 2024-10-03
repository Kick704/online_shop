package com.online.shop.service;

import com.online.shop.dto.request.creation.UserCreationDTO;
import com.online.shop.dto.request.update.UserUpdateDTO;
import com.online.shop.dto.response.UserResponseDTO;
import com.online.shop.dto.response.GoodsResponseDTO;
import com.online.shop.dto.response.InformationDTO;
import com.online.shop.entity.User;
import com.online.shop.mapper.UserMapper;
import com.online.shop.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
     * Выборка всех товаров в корзине пользователя
     *
     * @return {@link List} - список всех товаров {@link GoodsResponseDTO} в корзине пользователя
     */
    @Override
    @Transactional(readOnly = true)
    public List<GoodsResponseDTO> findAllGoodsInUserCart(UUID id) {
        return goodsMapper.toDTOList(userService.findAllGoodsInUserCart(id));
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
    public List<UserResponseDTO> findAllUsersByEnabled(boolean enabled) {
        return userMapper.toDTOList(userService.findAllUsersByEnabled(enabled));
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
        userService.save(newUser);
        return userMapper.toDTO(newUser);
    }

    /**
     * Обновление пользователя в БД
     *
     * @param id                идентификатор пользователя {@link UUID}
     * @param userUpdateDTO DTO Пользователь {@link UserUpdateDTO} с изменёнными полями
     * @return обновлённый DTO Пользователь {@link UserResponseDTO}
     */
    @Override
    @Transactional
    public UserResponseDTO update(UUID id, UserUpdateDTO userUpdateDTO) {
        User user = userService.findById(id);
        userMapper.updateEntityFromDto(userUpdateDTO, user);
        userService.save(user);
        return userMapper.toDTO(user);
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
        return new InformationDTO(String.format("Пользователь с ID: %s удалён", id));
    }

}
