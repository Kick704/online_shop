package com.online.shop.dao;

import com.online.shop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    /**
     * Выборка покупателя по id
     * @param id идентификатор покупателя {@link UUID}
     * @return {@link Customer} - покупатель по указанному {@code id}, или {@code null}, если такого покупателя нет
     */
    @Query(value = "SELECT c FROM Customer c WHERE c.id = :id")
    Customer findCustomerById(@Param("id") UUID id);

    /**
     * Выборка всех покупателей
     * @return {@link List} - список всех покупателей, или {@code null}, если покупателей еще нет
     */
    @Query(value = "SELECT c FROM Customer c")
    List<Customer> findAllCustomers();

    /**
     * Выборка покупателей по состоянию(активен или заблокирован) аккаунта
     * @param enabled состояние аккаунта {@link boolean}
     * @return {@link List} - список всех покупателей по указанному состоянию аккаунта, или {@code null},
     * если таких покупателей нет
     */
    @Query(value = "SELECT c FROM Customer c WHERE c.enabled = :enabled")
    List<Customer> findAllCustomersByEnabled(@Param("enabled") boolean enabled);

    /**
     * Удаление покупателя по id
     * @param id идентификатор покупателя {@link UUID}
     */
    @Modifying
    @Query(value = "DELETE FROM Customer c WHERE c.id = :id")
    void deleteCustomerById(@Param("id") UUID id);

}
