package com.online.shop.enums;

public enum PrivilegeEnum {

    READ_GOODS("Просмотр товара"),
    CREATE_GOODS("Создание товара"),
    EDIT_GOODS("Редактирование товара"),
    DELETE_GOODS("Удаление товара"),
    READ_ORDER("Просмотр заказа"),
    CREATE_ORDER("Создание заказа"),
    EDIT_ORDER("Редактирование заказа"),
    DELETE_ORDER("Удаление заказа"),
    MANAGE_USERS("Управление пользователями");

    private final String description;

    PrivilegeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
