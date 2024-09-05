package com.online.shop.exception_handling;

/**
 * Список основных ошибок
 */
public enum ErrorCode {

    INVALID_INPUT_DATA("Неверные данные запроса"),
    ENTITY_NOT_FOUND("Сущность не найдена"),
    EMPTY_CART("Корзина покупателя пуста"),
    OBJECT_REFERENCE_IS_NULL("Объект ссылается на null"),
    UNINITIALIZED_BUILDER_FIELD("Неинициализированное поле в билдере"),
    DATA_INTEGRITY_VIOLATION("Нарушение целостности данных"),
    INTERNAL_SERVER_ERROR("Внутренняя ошибка сервера");

    private final String description;

    ErrorCode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
