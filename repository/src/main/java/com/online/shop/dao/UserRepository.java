package com.online.shop.dao;

import com.online.shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для управления сущностью {@link User} между приложением и БД
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Выборка пользователя по id
     *
     * @param id идентификатор пользователя {@link UUID}
     * @return {@link Optional} - контейнер, содержащий пользователя {@link User} по указанному {@code id}
     */
    @Query(value = "SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findUserById(@Param("id") UUID id);

    /**
     * Выборка пользователя по email
     * 
     * @param email электронная почта пользователя
     * @return {@link Optional} - контейнер, содержащий пользователя {@link User} по указанному {@code email}
     */
    @Query(value = "SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);

    /**
     * Выборка всех пользователей
     *
     * @return {@link List} - список, содержащий список всех пользователей {@link User}
     */
    @Query(value = "SELECT u FROM User u")
    List<User> findAllUsers();

    /**
     * Выборка пользователей по состоянию(активен или заблокирован) аккаунта
     *
     * @param enabled состояние аккаунта {@link boolean}
     * @return {@link List} - список, содержащий список всех пользователей {@link User} по указанному состоянию
     * аккаунта {@code enabled}
     */
    @Query(value = "SELECT u FROM User u WHERE u.enabled = :enabled")
    List<User> findAllUsersByEnabled(@Param("enabled") boolean enabled);

    /**
     * Удаление пользователя по id
     *
     * @param id идентификатор пользователя {@link UUID}
     * @return количество удалённых строк {@link int}
     */
    @Modifying
    @Query(value = "DELETE FROM User u WHERE u.id = :id")
    int deleteUserById(@Param("id") UUID id);

    /**
     * Проверка существования пользователя в БД с указанным номером телефона
     * @param phoneNumber номер телефона пользователя
     * @return результат проверки
     */
    boolean existsByPhoneNumber(String phoneNumber);

    /**
     * Проверка существования пользователя в БД с указанным email
     * @param email электронная почта пользователя
     * @return результат проверки
     */
    boolean existsByEmail(String email);

}
