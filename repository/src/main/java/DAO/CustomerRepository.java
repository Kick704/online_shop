package DAO;

import entity.Customer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Выборка покупателя по id
     * @param id идентификатор покупателя {@link int}
     * @return {@link Customer} - покупатель по указанному {@code id}, или {@code null}, если такого покупателя нет
     */
    @Query(value = "SELECT c FROM Customer c WHERE c.id = :id")
    Customer findCustomerById(@Param("id") int id);

    /**
     * Выборка всех покупателей с сортировкой
     * @param sort критерий сортировки {@link Sort}
     * @return {@link List} - список всех покупателей, или {@code null}, если покупателей еще нет
     */
    @Query(value = "SELECT c FROM Customer c")
    List<Customer> findAllCustomers(Sort sort);

    /**
     * Выборка покупателей по состоянию(активен или заблокирован) аккаунта с сортировкой
     * @param enabled состояние аккаунта {@link boolean}
     * @param sort критерий сортировки {@link Sort}
     * @return {@link List} - список всех покупателей по указнному состоянию аккаунта, или {@code null},
     * если таких покупателей нет
     */
    @Query(value = "SELECT c FROM Customer c WHERE c.enabled = :enabled")
    List<Customer> findAllCustomersByEnabled(@Param("enabled") boolean enabled, Sort sort);

}
