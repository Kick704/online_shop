package com.online.shop.dao;

import com.online.shop.entity.Privilege;
import com.online.shop.enums.PrivilegeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для управления сущностями {@link Privilege} между приложением и БД
 */
@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, UUID> {

    /**
     * Выборка привилегии по id
     *
     * @param id идентификатор привилегии {@link UUID}
     * @return {@link Optional} - контейнер, содержащий привилегию {@link Privilege} по указанному {@code id}
     */
    @Query(value = "SELECT p FROM Privilege p WHERE p.id = :id")
    Optional<Privilege> findPrivilegeById(@Param("id") UUID id);

    /**
     * Выборка всех привилегий
     *
     * @return {@link List} - список всех привилегий {@link Privilege}
     */
    @Query(value = "SELECT p FROM Privilege p")
    List<Privilege> findAllPrivileges();

    /**
     * Выборка привилегии по названию
     *
     * @param privilegeName название привилегии {@link String}
     * @return {@link Optional} - контейнер, содержащий привилегию {@link Privilege} по указанному
     * названию {@code privilegeName}
     */
    @Query(value = "SELECT p FROM Privilege p WHERE p.privilegeName = :privilegeName")
    Optional<Privilege> findPrivilegeByName(@Param("privilegeName") PrivilegeEnum privilegeName);

    /**
     * Удаление привилегии по id
     *
     * @param id идентификатор привилегии {@link UUID}
     * @return количество удалённых строк {@link int}
     */
    @Modifying
    @Query(value = "DELETE FROM Privilege p WHERE p.id = :id")
    int deletePrivilegeById(@Param("id") UUID id);

}
