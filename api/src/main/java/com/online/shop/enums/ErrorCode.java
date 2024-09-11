package com.online.shop.enums;

import org.springframework.http.HttpStatus;

/**
 * Список основных ошибок
 */
public enum ErrorCode {

    INVALID_INPUT_DATA(1001, "Неверные данные запроса", HttpStatus.BAD_REQUEST),
    ENTITY_NOT_FOUND(1002, "Сущность не найдена", HttpStatus.NOT_FOUND),
    OBJECT_REFERENCE_IS_NULL(1003, "Объект ссылается на null", HttpStatus.PRECONDITION_FAILED),
    UNINITIALIZED_BUILDER_FIELD(1004, "Неинициализированное поле в билдере", HttpStatus.PRECONDITION_FAILED),
    UNIQUE_CONSTRAINT_VIOLATION(1005, "Нарушение ограничения уникальности", HttpStatus.CONFLICT),
    EMPTY_CART(1006, "Корзина покупателя пуста", HttpStatus.BAD_REQUEST),
    AUTHENTICATION_FAILED(1007, "Ошибка аутентификации", HttpStatus.UNAUTHORIZED),
    AUTHORIZATION_FAILED(1008, "Отказ в доступе", HttpStatus.FORBIDDEN),
    RATE_LIMIT_EXCEEDED(1009, "Превышено количество запросов", HttpStatus.TOO_MANY_REQUESTS),
    INTERNAL_SERVER_ERROR(1010, "Внутренняя ошибка сервера", HttpStatus.INTERNAL_SERVER_ERROR);

    /**
     * Внутренний код ошибки
     */
    private final int code;

    /**
     * Описание ошибки по умолчанию
     */
    private final String description;

    /**
     * HTTP статус ошибки сервера
     */
    private final HttpStatus status;

    ErrorCode(int code, String description, HttpStatus status) {
        this.code = code;
        this.description = description;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
