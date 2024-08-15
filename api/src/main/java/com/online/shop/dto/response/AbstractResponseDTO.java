package com.online.shop.dto.response;

import java.util.UUID;

/**
 * Абстрактный класс с общими полями и методами для всех DTO, предоставляющих информацию о сущностях
 */
public abstract class AbstractResponseDTO {

    protected UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
