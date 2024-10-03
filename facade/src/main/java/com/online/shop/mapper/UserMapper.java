package com.online.shop.mapper;

import com.online.shop.dto.request.creation.UserCreationDTO;
import com.online.shop.dto.request.update.UserUpdateDTO;
import com.online.shop.dto.response.UserResponseDTO;
import com.online.shop.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * Маппер для сущности {@link User} в DTO и обратно
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
    UserResponseDTO toDTO(User entity);

    /**
     * Маппинг из DTO в сущность для его создания
     *
     * @param dto RequestDTO
     * @return сущность
     */
    @Override
    User toEntity(UserCreationDTO dto);

    /**
     * Маппинг из списка сущностей в список DTO
     *
     * @param users список сущностей
     * @return список ResponseDTO
     */
    @Override
    List<UserResponseDTO> toDTOList(List<User> users);

    /**
     * Обновление сущности на основе DTO, игнорируя null поля и поле id
     *
     * @param dto    RequestDTO, проинициализированные поля которого будут обновлены в сущности
     * @param entity обновляемая сущность
     */
    @Override
    void updateEntityFromDto(UserUpdateDTO dto, @MappingTarget User entity);

}
