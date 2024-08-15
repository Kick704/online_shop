package com.online.shop.dto.response;

/**
 * Информационный DTO для предоставления информации об успешном выполнении операции
 */
public class InformationDTO {

    private String message;

    public InformationDTO() {
    }

    public InformationDTO(StringBuilder message) {
        this.message = message.toString();
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
