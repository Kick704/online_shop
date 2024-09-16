package com.online.shop.dto.response;

import com.online.shop.enums.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.ZonedDateTime;

/**
 * DTO с информацией об ошибке
 */
@Schema(description = "DTO Информация об ошибке")
public class ErrorResponseDTO {

    @Schema(description = "Код ошибки")
    private String errorCode;

    @Schema(description = "Сообщение об ошибке")
    private String message;

    @Schema(description = "Время создания ошибки")
    private final ZonedDateTime timestamp = ZonedDateTime.now();

    public ErrorResponseDTO() {
    }

    public ErrorResponseDTO(ErrorCode code, String message){
        this.errorCode = String.format("%d: %s", code.getCode(), code.name());
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "ErrorResponseDTO{" +
                "errorCode='" + errorCode + '\'' +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

}
