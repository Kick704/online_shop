package com.online.shop.dao;

import com.online.shop.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для управления сущностью {@link Role} между приложением и БД
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    /**
     * Выборка роли по названию
     *
     * @param name название роли {@link String}
     * @return {@link Optional} - контейнер, содержащий роль {@link Role} по указанному
     * названию {@code name}
     */
    @Query(value = "SELECT r FROM Role r WHERE r.name = :name")
    Optional<Role> findRoleByName(@Param("name") String name);

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
