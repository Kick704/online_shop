package com.online.shop.service;

import com.online.shop.dto.request.creation.CreationDTO;
import com.online.shop.dto.request.update.UpdateDTO;
import com.online.shop.dto.response.AbstractResponseDTO;
import com.online.shop.dto.response.InformationDTO;

import java.util.List;
import java.util.UUID;

/**
 *  Базовый интерфейс для всех фасад-сервисов
 *
 * @param <CreationRequestDTO> DTO, содержащий информацию для создания сущности
 * @param <UpdateRequestDTO> DTO, содержащий информацию для изменения сущности
 * @param <ResponseDTO> DTO, предоставляющий информацию о сущности
 */
public interface BaseFacadeService<CreationRequestDTO extends CreationDTO,
        UpdateRequestDTO extends UpdateDTO,
        ResponseDTO extends AbstractResponseDTO> {

    /**
     * Выборка объектов по id
     *
     * @param id идентификатор объекта {@link UUID}
     * @return {@link ResponseDTO} по указанному {@code id}
     */
    ResponseDTO findById(UUID id);

    /**
     * Выборка всех объектов
     *
     * @return {@link List} - список всех объектов {@link ResponseDTO}
     */
    List<ResponseDTO> findAll();

    /**
     * Добавление нового объекта в БД
     *
     * @param creationRequestDTO новый объект для создания {@link CreationRequestDTO}
     * @return Созданный объект {@link ResponseDTO}
     */
    ResponseDTO addNew(CreationRequestDTO creationRequestDTO);

    /**
     * Обновление объекта в БД
     *
     * @param id               идентификатор объекта {@link UUID}
     * @param updateRequestDTO объект {@link UpdateRequestDTO} с изменёнными полями
     * @return Обновлённый объект {@link ResponseDTO}
     */
    ResponseDTO update(UUID id, UpdateRequestDTO updateRequestDTO);

    /**
     * Удаление объекта по id
     *
     * @param id идентификатор объекта {@link UUID}
     * @return {@link InformationDTO} с сообщением о результате
     */
    InformationDTO deleteById(UUID id);

}
