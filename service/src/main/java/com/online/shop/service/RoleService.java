package com.online.shop.service;

import com.online.shop.entity.Role;
import com.online.shop.enums.RoleEnum;
import com.online.shop.enums.PrivilegeEnum;

public interface RoleService extends BaseService<Role> {

    /**
     * Выборка роли по названию
     *
     * @param roleName название роли {@link String}
     * @return {@link Role} - роль по указанному наименованию {@code roleName}
     */
    Role findRoleByName(String roleName);

    /**
     * Создание роли на основе набора ролей, включающих в себя набор привилегий
     *
     * @param roleEnum набор ролей {@link RoleEnum}
     * @return {@link Role} - роль с набором привилегий из {@link PrivilegeEnum}
     */
    Role createRoleFromEnum(RoleEnum roleEnum);

}
