package com.online.shop.mapper;

import com.online.shop.dto.request.creation.UserCreationDTO;
import com.online.shop.dto.request.update.UserUpdateDTO;
import com.online.shop.dto.response.UserResponseDTO;
import com.online.shop.entity.User;
import org.mapstruct.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

/**
 * Маппер для сущности {@link User}
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper extends BaseMapper<User,
        UserCreationDTO,
        UserUpdateDTO,
        UserResponseDTO> {

    /**
     * Маппинг из сущности в DTO
     *
     * @param entity сущность
     * @return ResponseDTO
     */
    @Override
    @Mapping(target = "phoneNumber", qualifiedByName = "mapPhoneNumberToDTO")
    UserResponseDTO toDTO(User entity);

    /**
     * Маппинг из DTO в сущность для его создания
     *
     * @param dto RequestDTO
     * @return сущность
     */
    @Override
    @Mapping(target = "phoneNumber", qualifiedByName = "mapPhoneNumberToEntity")
    @Mapping(target = "password", qualifiedByName = "encodePassword")
    User toEntity(UserCreationDTO dto);

    /**
     * Маппинг из списка сущностей в список DTO
     *
     * @param users список сущностей
     * @return список ResponseDTO
     */
    @Override
    @Mapping(target = "phoneNumber", qualifiedByName = "mapPhoneNumberToDTO")
    List<UserResponseDTO> toDTOList(List<User> users);

    /**
     * Обновление сущности на основе DTO, игнорируя null поля
     *
     * @param dto    RequestDTO, проинициализированные поля которого будут обновлены в сущности
     * @param entity обновляемая сущность
     */
    @Override
    @Mapping(target = "phoneNumber", qualifiedByName = "mapPhoneNumberToEntity")
    @Mapping(target = "password", qualifiedByName = "encodePassword")
    void updateEntityFromDto(UserUpdateDTO dto, @MappingTarget User entity);

    /**
     * Маппинг номера телефона пользователя с исключением кода страны в сущности
     *
     * @param phoneNumber исходный номер телефона в DTO
     * @return номер телефона без кода страны
     */
    @Named("mapPhoneNumberToEntity")
    default String mapPhoneNumberToEntity(String phoneNumber) {
        return phoneNumber != null ? phoneNumber.substring(phoneNumber.length() - 10) : null;
    }

    /**
     * Маппинг номера телефона пользователя с добавлением кода страны в DTO
     *
     * @param phoneNumber исходный номер телефона в сущности
     * @return номер телефона с кодом страны
     */
    @Named("mapPhoneNumberToDTO")
    default String mapPhoneNumberToDTO(String phoneNumber) {
        return "+7".concat(phoneNumber);
    }

    /**
     * Маппинг пароля пользователя для шифрования с помощью bcrypt в сущности
     *
     * @param password исходный пароль в DTO
     * @return зашифрованный пароль
     */
    @Named("encodePassword")
    default String encodePassword(String password) {
        return password != null ? new BCryptPasswordEncoder().encode(password) : null;
    }

}
