package com.online.shop.exception;

/**
 * Исключение для обработки случаев, когда сущности не найдены или их не существует
 */
public class NoEntitiesFoundException extends RuntimeException{

    public NoEntitiesFoundException(String message) {
        super(message);
    }

}
