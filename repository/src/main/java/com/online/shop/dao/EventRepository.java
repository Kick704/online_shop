package com.online.shop.dao;

import com.online.shop.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Репозиторий для управления сущностью {@link Event} между приложением и БД
 */
public interface EventRepository extends JpaRepository<Event, UUID> {
}
