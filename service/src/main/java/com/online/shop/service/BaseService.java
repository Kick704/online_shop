package com.online.shop.service;

import com.online.shop.entity.AbstractEntity;

import java.util.List;
import java.util.UUID;

/**
 * Базовый интерфейс для всех сервисов
 *
 * @param <Entity> сущность
 */
public interface BaseService<Entity extends AbstractEntity> {

    /**
     * Выборка сущности по id
     *
     * @param id идентификатор сущности {@link UUID}
     * @return {@link Entity}, сущность по указанному {@code id}
     */
    Entity findById(UUID id);

    /**
     * Выборка всех сущностей
     *
     * @return {@link List} - список всех сущностей {@link Entity}
     */
    List<Entity> findAll();

    /**
     * Удаление сущности по id
     *
     * @param id идентификатор сущности {@link UUID}
     */
    void deleteById(UUID id);

}
