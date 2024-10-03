package com.online.shop.enums;

import java.util.Set;

import static com.online.shop.enums.PrivilegeEnum.*;

public enum RoleEnum {

    ADMIN("Администратор",
            Set.of(READ_GOODS,
                    CREATE_GOODS,
                    EDIT_GOODS,
                    DELETE_GOODS,
                    MANAGE_USERS,
                    READ_ORDER,
                    EDIT_ORDER,
                    DELETE_ORDER)),
    SELLER("Продавец",
            Set.of(READ_GOODS,
                    CREATE_GOODS,
                    EDIT_GOODS,
                    READ_ORDER,
                    EDIT_ORDER)),
    CUSTOMER("Покупатель",
            Set.of(READ_GOODS,
                    CREATE_ORDER));

    private final String description;
    private final Set<PrivilegeEnum> privileges;

    RoleEnum(String description, Set<PrivilegeEnum> privileges) {
        this.description = description;
        this.privileges = privileges;
    }

    public String getDescription() {
        return description;
    }

    public Set<PrivilegeEnum> getPrivileges() {
        return privileges;
    }

}
