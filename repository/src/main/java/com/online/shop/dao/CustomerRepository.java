package com.online.shop.dao;

import com.online.shop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для управления сущностями {@link Customer} между приложением и БД
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    /**
     * Выборка покупателя по id
     *
     * @param id идентификатор покупателя {@link UUID}
     * @return {@link Optional} - контейнер, содержащий покупателя {@link Customer} по указанному {@code id}
     */
    @Query(value = "SELECT c FROM Customer c WHERE c.id = :id")
    Optional<Customer> findCustomerById(@Param("id") UUID id);

    @Query(value = "SELECT c FROM Customer c WHERE c.email = :email")
    Optional<Customer> findCustomerByEmail(@Param("email") String email);

    /**
     * Выборка всех покупателей
     *
     * @return {@link List} - список, содержащий список всех покупателей {@link Customer}
     */
    @Query(value = "SELECT c FROM Customer c")
    List<Customer> findAllCustomers();

    /**
     * Выборка покупателей по состоянию(активен или заблокирован) аккаунта
     *
     * @param enabled состояние аккаунта {@link boolean}
     * @return {@link List} - список, содержащий список всех покупателей {@link Customer} по указанному состоянию
     * аккаунта {@code enabled}
     */
    @Query(value = "SELECT c FROM Customer c WHERE c.enabled = :enabled")
    List<Customer> findAllCustomersByEnabled(@Param("enabled") boolean enabled);

    /**
     * Удаление покупателя по id
     *
     * @param id идентификатор покупателя {@link UUID}
     * @return количество удалённых строк {@link int}
     */
    @Modifying
    @Query(value = "DELETE FROM Customer c WHERE c.id = :id")
    int deleteCustomerById(@Param("id") UUID id);

    /**
     * Проверка существования покупателя в БД с указанным номером телефона
     * @param phoneNumber номер телефона покупателя
     * @return результат проверки
     */
    boolean existsByPhoneNumber(String phoneNumber);

    /**
     * Проверка существования покупателя в БД с указанным e-mail
     * @param email e-mail покупателя
     * @return результат проверки
     */
    boolean existsByEmail(String email);

}
