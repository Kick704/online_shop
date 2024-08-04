package com.online.shop.mapper;

import com.online.shop.dto.AbstractDTO;
import com.online.shop.entity.AbstractEntity;

import java.util.List;

/**
 * Базовый интерфейс для всех мапперов
 * <p> mapstruct не поддерживает обобщенные интерфейсы, поэтому данный интерфейс лишь описывает правила для мапперов
 */
public interface BaseMapper<Entity extends AbstractEntity, DTO extends AbstractDTO> {

    /**
     * Маппинг из сущности в DTO
     *
     * @param entity сущность
     * @return DTO
     */
    DTO toDTO(Entity entity);

    /**
     * Маппинг из DTO в сущность
     *
     * @param dto DTO
     * @return сущность
     */
    Entity toEntity(DTO dto);

    /**
     * Маппинг из списка сущностей в список DTO
     *
     * @param entities список сущностей
     * @return список DTO
     */
    List<DTO> toDTOList(List<Entity> entities);

    /**
     * Маппинг из списка DTO в список сущностей
     *
     * @param dtos список DTO
     * @return список сущностей
     */
    List<Entity> toEntityList(List<DTO> dtos);

    /**
     * Обновление сущности на основе DTO, игнорируя null поля и поле id
     *
     * @param dto    DTO, проинициализированные поля которого, кроме id, будут обновлены в сущности
     * @param entity обновляемая сущность
     */
    void updateEntityFromDto(DTO dto, Entity entity);

}
