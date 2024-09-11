package com.online.shop.exception_handling;

import com.online.shop.enums.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Глобальный обработчик исключений(ошибок)
 */
@RestControllerAdvice
public class ExceptionTranslator {

    /**
     * Обработчик основных ошибок
     * @param ex обрабатываемое исключение {@link CommonRuntimeException}
     * @return информация об ошибке
     */
    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleCommonRuntimeException(CommonRuntimeException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        String message = ex.getMessage();
        return new ResponseEntity<>(
                ErrorResponse(errorCode, message),
                errorCode.getStatus()
        );
    }

    /**
     * Обработчик ошибок валидаций при создании и изменении сущностей
     * @param ex обрабатываемое исключение {@link MethodArgumentNotValidException}
     * @return информация об ошибке
     */
    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
        ErrorCode errorCode = ErrorCode.INVALID_INPUT_DATA;
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String message = (fieldError != null) ? fieldError.getDefaultMessage() : errorCode.getDescription();
        return new ResponseEntity<>(
                ErrorResponse(errorCode, message),
                errorCode.getStatus()
        );
    }

    /**
     * Обработчик остальных ошибок
     * @param ex обрабатываемое исключение {@link Exception}
     * @return информация об ошибке
     */
    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleOtherException(Exception ex) {
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        String message = ex.getMessage();
        return new ResponseEntity<>(
                ErrorResponse(errorCode, message),
                errorCode.getStatus()
        );
    }

    /**
     * Метод для формирования ответа с информацией об ошибке
     * @param errorCode код ошибки {@link ErrorCode}
     * @param message сообщение об ошибке {@link String}
     * @return {@link Map}, содержащий информацию об ошибке
     */
    private static Map<String, Object> ErrorResponse(ErrorCode errorCode, String message) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("errorCode", String.format("%d: %s", errorCode.getCode(), errorCode.name()));
        errorResponse.put("message", message);
        errorResponse.put("timestamp", ZonedDateTime.now());
        return errorResponse;
    }

}
