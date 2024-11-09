package com.online.shop.enums;

/**
 * Всевозможные статусы/состояния заказа
 */
public enum OrderStatus {

    CREATED("Создан"),
    COLLECTING("Собирается"),
    SHIPPED("Отправлен"),
    DELIVERED("Доставлен"),
    CANCELLED("Отменён");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
