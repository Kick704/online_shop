package com.online.shop.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Информационный DTO для предоставления информации об успешном выполнении операции
 */
@Schema(description = "Информационный DTO")
public class InformationDTO {

    @Schema(description = "Сообщение")
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
