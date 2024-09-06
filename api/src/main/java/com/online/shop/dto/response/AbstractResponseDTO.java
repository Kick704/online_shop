package com.online.shop.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

/**
 * Абстрактный класс с общими полями и методами для всех DTO, предоставляющих информацию о сущностях
 */
public abstract class AbstractResponseDTO {

    @Schema(description = "ID")
    protected UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
