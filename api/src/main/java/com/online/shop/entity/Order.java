package com.online.shop.entity;

import com.online.shop.enums.OrderStatus;
import com.online.shop.exception_handling.CommonRuntimeException;
import com.online.shop.exception_handling.ErrorCode;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

/**
 * Класс-сущность заказа из интернет-магазина
 */
@Entity
@Table(name = "orders")
public class Order extends AbstractEntity {

    /**
     * Пользователь
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Итоговая стоимость заказа
     */
    @Column(name = "amount")
    private double amount;

    /**
     * Адрес доставки товара, указанный пользователем
     */
    @Column(name = "delivery_address")
    private String deliveryAddress;

    /**
     * Код для получения заказа
     */
    @Column(name = "receipt_code")
    private int receiptCode;

    /**
     * Статус заказа
     * <p>При создании на уровне приложения устанавливается {@code CREATED}
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    /**
     * Список товаров в заказе
     */
    @ManyToMany
    @JoinTable(name = "order_item",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "goods_id"))
    private List<Goods> goodsInOrder;

    /**
     * Конфигурация заказа при его создании
     */
    @PrePersist
    public void createOrder() {
        amount = goodsInOrder.stream().mapToDouble(Goods::getPrice).sum();
        status = OrderStatus.CREATED;
    }

    /**
     * Конфигурация заказа при его обновлении
     */
    @PreUpdate
    public void updateOrder() {
        amount = goodsInOrder.stream().mapToDouble(Goods::getPrice).sum();
    }

    public Order() {
    }

    private Order(Builder builder) {
        setUser(builder.user);
        setDeliveryAddress(builder.deliveryAddress);
        setReceiptCode(builder.receiptCode);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public int getReceiptCode() {
        return receiptCode;
    }

    public void setReceiptCode(int receiptCode) {
        this.receiptCode = receiptCode;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<Goods> getGoodsInOrder() {
        return goodsInOrder;
    }

    public void setGoodsInOrder(List<Goods> goodsInOrder) {
        this.goodsInOrder = goodsInOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Double.compare(amount, order.amount) == 0 &&
                receiptCode == order.receiptCode &&
                Objects.equals(user, order.user) &&
                Objects.equals(deliveryAddress, order.deliveryAddress) &&
                status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, amount, deliveryAddress, receiptCode, status);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", amount=" + amount +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", receiptCode=" + receiptCode +
                ", status=" + status +
                '}';
    }

    public static final class Builder {
        private User user;
        private String deliveryAddress;
        private int receiptCode;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder user(User val) {
            user = val;
            return this;
        }

        public Builder deliveryAddress(String val) {
            deliveryAddress = val;
            return this;
        }

        public Builder receiptCode(int val) {
            receiptCode = val;
            return this;
        }

        public Order build() {
            if (user == null || deliveryAddress == null) {
                throw new CommonRuntimeException(
                        ErrorCode.INTERNAL_SERVER_ERROR,
                        "Order: одно или несколько полей (user, deliveryAddress) ссылаются на null"
                );
            }
            return new Order(this);
        }
    }
}
