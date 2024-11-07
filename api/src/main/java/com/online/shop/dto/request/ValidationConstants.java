package com.online.shop.dto.request;

/**
 * Класс, содержащий константы для валидации полей DTO, использующихся для запросов
 */
public final class ValidationConstants {

    private ValidationConstants() {
    }

    /**
     * Минимальная длина пароля для аккаунта пользователя
     */
    public static final int MIN_USER_PASSWORD_LENGTH = 8;

    /**
     * Шаблон номера телефона
     */
    public static final String PHONE_NUMBER_REGEXP = "^(\\+7|8)\\d{10}$";

}
