package com.online.shop.exception;

/**
 * Исключение для обработки случаев, когда сущность не найдена или не существует в БД
 */
public class NoSuchEntityException extends RuntimeException{

    public NoSuchEntityException(String message) {
        super(message);
    }

}
