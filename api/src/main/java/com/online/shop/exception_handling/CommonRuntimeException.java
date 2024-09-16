package com.online.shop.exception_handling;

/**
 * Общее исключение для основных ошибок
 */
public class CommonRuntimeException extends RuntimeException {

    private final ErrorCode errorCode;

    public CommonRuntimeException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
