package com.online.shop.exception;

/**
 * Исключение для обработки инициализации обязательных полей ссылочного типа данных при создании объекта через Builder.
 * Необходим для избежания ошибок при создании записей в БД
 */
public class UninitializedBuilderFieldException extends RuntimeException {

    public UninitializedBuilderFieldException(String message) {
        super(message);
    }

}
