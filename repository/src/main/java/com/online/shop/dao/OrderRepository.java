package com.online.shop.dao;

import com.online.shop.entity.Order;
import com.online.shop.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для управления сущностями {@link Order} между приложением и БД
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    /**
     * Выборка заказа по id
     *
     * @param id идентификатор заказа {@link UUID}
     * @return {@link Optional} - контейнер, содержащий заказ {@link Order} по указанному {@code id}
     */
    @Query(value = "SELECT o FROM Order o JOIN FETCH o.user WHERE o.id = :id")
    Optional<Order> findOrderById(@Param("id") UUID id);

    /**
     * Выборка всех заказов
     *
     * @return {@link List} - список всех заказов {@link Order}
     */
    @Query(value = "SELECT o FROM Order o JOIN FETCH o.user")
    List<Order> findAllOrders();

    /**
     * Выборка заказов по статусу
     *
     * @param status статус заказа {@link OrderStatus}
     * @return {@link List} - список заказов {@link Order} по указанному статусу {@code status}
     */
    @Query(value = "SELECT o FROM Order o JOIN FETCH o.user WHERE o.status = :status")
    List<Order> findAllOrdersByStatus(@Param("status") OrderStatus status);

    /**
     * Удаление заказа по id
     *
     * @param id идентификатор заказа {@link UUID}
     * @return количество удалённых строк {@link int}
     */
    @Modifying
    @Query(value = "DELETE FROM Order o WHERE o.id = :id")
    int deleteOrderById(@Param("id") UUID id);

}
