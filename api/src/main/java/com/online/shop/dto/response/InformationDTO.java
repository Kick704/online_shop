package com.online.shop.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Результат выполнения операции
 */
@Schema(description = "Информационный DTO")
public class InformationDTO {

    @Schema(description = "Результат")
    private String message;

    public InformationDTO() {
    }

    public InformationDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "InformationDTO{" +
                "message=" + message +
                '}';
    }

}
