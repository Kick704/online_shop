package com.online.shop.exception_handling;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
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
        return new ResponseEntity<>(ErrorResponse(ex.getErrorCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Обработчик ошибок валидаций
     * @param ex обрабатываемое исключение {@link MethodArgumentNotValidException}
     * @return информация об ошибке
     */
    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
        ErrorCode errorCode = ErrorCode.INVALID_INPUT_DATA;

        FieldError fieldError = ex.getBindingResult().getFieldError();
        String message = (fieldError != null) ? fieldError.getDefaultMessage() : errorCode.getDescription();

        return new ResponseEntity<>(ErrorResponse(errorCode, message), HttpStatus.BAD_REQUEST);
    }

    /**
     * Обработчик нарушений целостности данных
     * @param ex обрабатываемое исключение {@link DataIntegrityViolationException}
     * @return информация об ошибке
     */
    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ErrorCode errorCode = ErrorCode.DATA_INTEGRITY_VIOLATION;
        String message = errorCode.getDescription();

        Throwable rootCause = ex.getRootCause();
        if (rootCause != null) {
            if (rootCause.getMessage().contains("customers_phone_number_key")) {
                message = "Пользователь с таким номером телефона уже зарегистрирован";
            } else if (rootCause.getMessage().contains("customers_email_key")) {
                message = "Пользователь с таким e-mail уже зарегистрирован";
            } else if (rootCause.getMessage().contains("goods_categories_category_name_key")) {
                message = "Такая категория товаров уже существует";
            }
        }

        return new ResponseEntity<>(ErrorResponse(errorCode, message), HttpStatus.CONFLICT);
    }

    /**
     * Обработчик остальных ошибок
     * @param ex обрабатываемое исключение {@link Exception}
     * @return информация об ошибке
     */
    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleOtherException(Exception ex) {
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(ErrorResponse(errorCode, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Метод для формирования ответа с информацией об ошибке
     * @param errorCode код ошибки {@link ErrorCode}
     * @param message сообщение об ошибке {@link String}
     * @return {@link Map}, содержащий информацию об ошибке
     */
    private static Map<String, Object> ErrorResponse(ErrorCode errorCode, String message) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("errorCode", errorCode.name());
        errorResponse.put("message", message);
        errorResponse.put("timestamp", ZonedDateTime.now());
        return errorResponse;
    }

}
