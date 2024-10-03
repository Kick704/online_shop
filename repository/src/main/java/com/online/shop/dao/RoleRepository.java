package com.online.shop.dao;

import com.online.shop.entity.Role;
import com.online.shop.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для управления сущностями {@link Role} между приложением и БД
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    /**
     * Выборка роли по id
     *
     * @param id идентификатор роли {@link UUID}
     * @return {@link Optional} - контейнер, содержащий роль {@link Role} по указанному {@code id}
     */
    @Query(value = "SELECT r FROM Role r WHERE r.id = :id")
    Optional<Role> findRoleById(@Param("id") UUID id);

    /**
     * Выборка всех ролей
     *
     * @return {@link List} - список всех ролей {@link Role}
     */
    @Query(value = "SELECT r FROM Role r")
    List<Role> findAllRoles();

    /**
     * Выборка роли по названию
     *
     * @param roleName название роли {@link String}
     * @return {@link Optional} - контейнер, содержащий роль {@link Role} по указанному
     * названию {@code roleName}
     */
    @Query(value = "SELECT r FROM Role r WHERE r.roleName = :roleName")
    Optional<Role> findRoleByName(@Param("roleName") RoleEnum roleName);

    /**
     * Удаление роли по id
     *
     * @param id идентификатор роли {@link UUID}
     * @return количество удалённых строк {@link int}
     */
    @Modifying
    @Query(value = "DELETE FROM Role r WHERE r.id = :id")
    int deleteRoleById(@Param("id") UUID id);

}
