package com.online.shop.service;

import com.online.shop.entity.Role;

/**
 * Сервис для управления сущностью {@link Role}
 */
public interface RoleService extends BaseService<Role> {

    /**
     * Выборка роли по названию
     *
     * @param name название роли {@link String}
     * @return {@link Role} - роль по указанному названию {@code name}
     */
    Role findByName(String name);

}
