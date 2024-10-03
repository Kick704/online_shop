package com.online.shop.service;

import com.online.shop.dao.PrivilegeRepository;
import com.online.shop.dao.RoleRepository;
import com.online.shop.entity.Privilege;
import com.online.shop.entity.Role;
import com.online.shop.enums.RoleEnum;
import com.online.shop.enums.PrivilegeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    /**
     * Выборка сущности по id
     *
     * @param id идентификатор сущности {@link UUID}
     * @return {@link Role}, сущность по указанному {@code id}
     */
    @Override
    public Role findById(UUID id) {
        return null;
    }

    /**
     * Выборка всех сущностей
     *
     * @return {@link List} - список всех сущностей {@link Role}
     */
    @Override
    public List<Role> findAll() {
        return List.of();
    }

    /**
     * Выборка роли по названию
     *
     * @param roleName название роли {@link String}
     * @return {@link Role} - роль по указанному наименованию {@code roleName}
     */
    @Override
    public Role findRoleByName(String roleName) {
        return null;
    }

    /**
     * Создание роли на основе набора ролей, включающих в себя набор привилегий
     *
     * @param roleEnum набор ролей {@link RoleEnum}
     * @return {@link Role} - роль с набором привилегий из {@link PrivilegeEnum}
     */
    @Override
    @Transactional
    public Role createRoleFromEnum(RoleEnum roleEnum) {
        Set<Privilege> privileges = new HashSet<>();
        for (PrivilegeEnum privilegeEnum : roleEnum.getPrivileges()) {
            Privilege privilege = privilegeRepository.findPrivilegeByName(privilegeEnum)
                    .orElseGet(() -> privilegeRepository.save(
                            Privilege.Builder.newBuilder()
                                    .privilegeName(privilegeEnum)
                                    .build()));
            privileges.add(privilege);
        }
        Role role = roleRepository.findRoleByName(roleEnum)
                .orElseGet(() -> Role.Builder.newBuilder()
                        .roleName(roleEnum)
                        .privileges(privileges)
                        .build());
        return roleRepository.save(role);
    }

    /**
     * Удаление сущности по id
     *
     * @param id идентификатор сущности {@link UUID}
     */
    @Override
    public void deleteById(UUID id) {

    }
}
