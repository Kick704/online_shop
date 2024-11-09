package com.online.shop.enums;

/**
 * События для инициации переходов статусов заказа в машине состояний
 */
public enum OrderEvent {

    COLLECT("Сборка"),
    SHIP("Отправка"),
    CONFIRM_DELIVERY("Подтверждение доставки"),
    CANCEL("Отмена");

    private final String value;

    OrderEvent(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
