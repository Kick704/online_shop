package com.online.shop.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.Objects;

/**
 * Класс-сущность Событие для регистрации завершенных заказов
 */
@Entity
@Table(name = "completed_order_events")
public class CompletedOrderEvent extends AbstractEntity {

    /**
     * Заказ
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    /**
     * Данные о событии
     */
    @Column(name = "description")
    private String description;

    public CompletedOrderEvent() {
    }

    private CompletedOrderEvent(Builder builder) {
        setOrder(builder.order);
        setDescription(builder.description);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompletedOrderEvent event)) return false;
        return Objects.equals(order, event.order) && Objects.equals(description, event.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, description);
    }

    @Override
    public String toString() {
        return "CompletedOrderEvent{" +
                "id=" + id +
                ", order=" + order +
                ", description='" + description + '\'' +
                '}';
    }

    public static final class Builder {
        private Order order;
        private String description;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder order(Order val) {
            order = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public CompletedOrderEvent build() {
            return new CompletedOrderEvent(this);
        }
    }

}
