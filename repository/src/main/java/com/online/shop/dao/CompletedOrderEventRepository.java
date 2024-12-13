package com.online.shop.dao;

import com.online.shop.entity.CompletedOrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Репозиторий для управления сущностью {@link CompletedOrderEvent} между приложением и БД
 */
public interface CompletedOrderEventRepository extends JpaRepository<CompletedOrderEvent, UUID> {
}
