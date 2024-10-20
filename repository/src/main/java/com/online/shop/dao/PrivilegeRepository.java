package com.online.shop.dao;

import com.online.shop.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Репозиторий для управления сущностью {@link Privilege} между приложением и БД
 */
@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, UUID> {
}
