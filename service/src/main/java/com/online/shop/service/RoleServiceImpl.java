package com.online.shop.service;

import com.online.shop.dao.RoleRepository;
import com.online.shop.entity.Role;
import com.online.shop.exception_handling.CommonRuntimeException;
import com.online.shop.exception_handling.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Сервис для управления сущностью {@link Role}
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Выборка сущности по id
     *
     * @param id идентификатор сущности {@link UUID}
     * @return {@link Role}, сущность по указанному {@code id}
     */
    @Override
    public Role findById(UUID id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new CommonRuntimeException(
                        ErrorCode.ENTITY_NOT_FOUND,
                        String.format("Роль с ID %s не найдена", id))
                );
    }

    /**
     * Выборка всех сущностей
     *
     * @return {@link List} - список всех сущностей {@link Role}
     */
    @Override
    public List<Role> findAll() {
        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty()) {
            throw new CommonRuntimeException(ErrorCode.ENTITY_NOT_FOUND, "Ни одна роль не найдена в БД");
        }
        return roles;
    }

    /**
     * Выборка роли по названию
     *
     * @param name название роли {@link String}
     * @return {@link Role} - роль по указанному названию {@code name}
     */
    @Override
    public Role findByName(String name) {
        return roleRepository.findRoleByName(name)
                .orElseThrow(() -> new CommonRuntimeException(
                        ErrorCode.ENTITY_NOT_FOUND,
                        String.format("Роль с названием '%s' не найдена", name))
                );
    }

    /**
     * Удаление сущности по id
     *
     * @param id идентификатор сущности {@link UUID}
     */
    @Override
    public void deleteById(UUID id) {
        if (roleRepository.deleteRoleById(id) == 0) {
            throw new CommonRuntimeException(
                    ErrorCode.ENTITY_NOT_FOUND,
                    String.format("Роль с ID %s не найдена или не может быть удалена", id)
            );
        }
    }
}
