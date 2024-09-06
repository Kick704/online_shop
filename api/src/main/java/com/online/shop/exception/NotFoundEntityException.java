package com.online.shop.exception;

/**
 * Исключение для обработки случаев, когда ни одна сущность не найдена по запросу или не существует в БД
 */
public class NotFoundEntityException extends RuntimeException {

    public NotFoundEntityException(String message) {
        super(message);
    }

}
