package com.online.shop.exception_handling;

import org.springframework.http.HttpStatus;

/**
 * Список основных ошибок
 */
public enum ErrorCode {

    INVALID_INPUT_DATA(1001, "Неверные данные запроса", HttpStatus.BAD_REQUEST),
    ENTITY_NOT_FOUND(1002, "Сущность не найдена", HttpStatus.NOT_FOUND),
    OBJECT_REFERENCE_IS_NULL(1003, "Объект ссылается на null", HttpStatus.PRECONDITION_FAILED),
    UNIQUE_CONSTRAINT_VIOLATION(1004, "Нарушение ограничения уникальности", HttpStatus.CONFLICT),
    EMPTY_CART(1005, "Корзина пользователя пуста", HttpStatus.NOT_FOUND),
    INSUFFICIENT_STOCK(1006, "Недостаточно товаров на складе", HttpStatus.CONFLICT),
    INSUFFICIENT_FUNDS(1007, "Недостаточно средств на счёте", HttpStatus.METHOD_NOT_ALLOWED),
    ENTITY_DELETION_FAILED(1008, "Ошибка при попытке удаления сущности", HttpStatus.INTERNAL_SERVER_ERROR),
    AUTHENTICATION_FAILED(1009, "Ошибка аутентификации", HttpStatus.UNAUTHORIZED),
    AUTHORIZATION_FAILED(10010, "Отказано в доступе", HttpStatus.FORBIDDEN),
    STATE_MACHINE_ERROR(1011, "Ошибка машины состояний", HttpStatus.INTERNAL_SERVER_ERROR),
    INTERNAL_SERVER_ERROR(1012, "Внутренняя ошибка сервера", HttpStatus.INTERNAL_SERVER_ERROR);

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
