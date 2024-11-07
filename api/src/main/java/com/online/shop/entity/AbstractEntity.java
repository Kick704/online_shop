package com.online.shop.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Абстрактный класс с общими полями и методами для всех сущностей
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity {

    /**
     * Идентификатор сущности
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    protected UUID id;

    /**
     * Дата и время создания сущности
     * <p>Устанавливается при создании сущности, неизменно
     */
    @CreatedDate
    @Column(name = "created", updatable = false)
    protected ZonedDateTime created;

    /**
     * Дата и время обновления сущности
     * <p>Изменяется при обновлении сущности
     */
    @LastModifiedDate
    @Column(name = "modified")
    protected ZonedDateTime modified;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    public ZonedDateTime getModified() {
        return modified;
    }

    public void setModified(ZonedDateTime modified) {
        this.modified = modified;
    }
}
