package com.online.shop.dao;

import com.online.shop.entity.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Выборка заказа по id
     * @param id идентификатор заказа {@link int}
     * @return {@link Order} - заказ по указанному {@code id}, или {@code null}, если такого заказа нет
     */
    @Query(value = "SELECT o FROM Order o WHERE o.id = :id")
    Order findOrderById(@Param("id") int id);

    /**
     * Выборка всех заказов с сортировкой
     * @param sort критерий сортировки {@link Sort}
     * @return {@link List} - список всех заказов, или {@code null}, если заказов еще нет
     */
    @Query(value = "SELECT o FROM Order o")
    List<Order> findAllOrders(Sort sort);

    /**
     * Выборка заказов по статусу с сортировкой
     * @param status статус заказа {@link String}
     * @param sort критерий сортировки {@link Sort}
     * @return {@link List} - список заказов по указанному статусу,  или {@code null}, если таких заказов нет
     */
    @Query(value = "SELECT o FROM Order o WHERE o.status = :status")
    List<Order> findAllOrdersByStatus(@Param("status") String status, Sort sort);

    /**
     * Удаление заказа по id
     * @param id идентификатор заказа {@link int}
     */
    @Query(value = "DELETE o FROM Order o WHERE o.id = :id")
    void deleteOrderById(@Param("id") int id);

}
