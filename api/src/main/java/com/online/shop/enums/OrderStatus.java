package com.online.shop.enums;

/**
 * Всевозможные статусы заказа
 */
public enum OrderStatus {

    CREATED("Создан"),
    COLLECTING("Собирается"),
    COMING("В пути"),
    RECEIVED("Получен"),
    CANCELLED("Отменён");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
