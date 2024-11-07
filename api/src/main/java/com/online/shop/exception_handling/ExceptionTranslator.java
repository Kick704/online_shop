package com.online.shop.exception_handling;

import com.online.shop.dto.response.ErrorResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
    public ResponseEntity<ErrorResponseDTO> handleCommonRuntimeException(CommonRuntimeException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        String message = ex.getMessage();
        return new ResponseEntity<>(
                new ErrorResponseDTO(errorCode, message),
                errorCode.getStatus()
        );
    }

    /**
     * Обработчик ошибок валидаций при создании и изменении сущностей
     * @param ex обрабатываемое исключение {@link MethodArgumentNotValidException}
     * @return информация об ошибке
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleValidationException(MethodArgumentNotValidException ex) {
        ErrorCode errorCode = ErrorCode.INVALID_INPUT_DATA;
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String message = (fieldError != null) ? fieldError.getDefaultMessage() : errorCode.getDescription();
        return new ResponseEntity<>(
                new ErrorResponseDTO(errorCode, message),
                errorCode.getStatus()
        );
    }

    /**
     * Обработчик ошибок авторизации (проверки прав доступа)
     * @param ex обрабатываемое исключение {@link AuthorizationDeniedException}
     * @return информация об ошибке
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleAuthorizationDeniedException(AuthorizationDeniedException ex) {
        ErrorCode errorCode = ErrorCode.AUTHORIZATION_FAILED;
        String message = errorCode.getDescription();
        return new ResponseEntity<>(
                new ErrorResponseDTO(errorCode, message),
                errorCode.getStatus()
        );
    }

    /**
     * Обработчик остальных ошибок
     * @param ex обрабатываемое исключение {@link Exception}
     * @return информация об ошибке
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleOtherException(Exception ex) {
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        String message = ex.getMessage();
        return new ResponseEntity<>(
                new ErrorResponseDTO(errorCode, message),
                errorCode.getStatus()
        );
    }

}
