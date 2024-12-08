package com.online.shop.mapper;

import com.online.shop.dto.request.creation.CreationDTO;
import com.online.shop.dto.request.update.UpdateDTO;
import com.online.shop.dto.response.AbstractResponseDTO;
import com.online.shop.entity.AbstractEntity;

import java.util.List;

/**
 * Базовый интерфейс для всех мапперов
 * <p> mapstruct не поддерживает обобщенные интерфейсы, поэтому данный интерфейс лишь описывает правила для мапперов
 *
 * @param <Entity> сущность
 * @param <ResponseDTO> DTO, предоставляющий информацию о сущности
 */
public interface BaseMapper<Entity extends AbstractEntity,
        CreationRequestDTO extends CreationDTO,
        UpdateRequestDTO extends UpdateDTO,
        ResponseDTO extends AbstractResponseDTO> {

    /**
     * Маппинг из сущности в DTO
     *
     * @param entity сущность
     * @return ResponseDTO
     */
    ResponseDTO toDTO(Entity entity);

    /**
     * Маппинг из DTO в сущность для его создания
     *
     * @param dto RequestDTO
     * @return сущность
     */
    Entity toEntity(CreationRequestDTO dto);

    /**
     * Маппинг из списка сущностей в список DTO
     *
     * @param entities список сущностей
     * @return список ResponseDTO
     */
    List<ResponseDTO> toDTOList(List<Entity> entities);

    /**
     * Обновление сущности на основе DTO, игнорируя null поля и поле id
     *
     * @param dto    RequestDTO, проинициализированные поля которого будут обновлены в сущности
     * @param entity обновляемая сущность
     */
    void updateEntityFromDto(UpdateRequestDTO dto, Entity entity);

}
