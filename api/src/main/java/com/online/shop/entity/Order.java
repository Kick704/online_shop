package com.online.shop.entity;

import com.online.shop.enums.OrderStatus;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;
import java.util.Objects;

/**
 * Класс-сущность заказа из интернет-магазина
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "orders")
public class Order extends AbstractEntity{

    /**
     * Покупатель
     */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /**
     * Полная стоимость заказа
     */
    @Column(name = "amount")
    private double amount;

    /**
     * Адрес доставки товара, указанный покупателем
     */
    @Column(name = "delivery_address")
    private String deliveryAddress;

    /**
     * Код получения заказа
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

    protected Order() {
    }

    private Order(@NotNull OrderBuilder orderBuilder) {
        this.customer = orderBuilder.customer;
        this.amount = orderBuilder.amount;
        this.deliveryAddress = orderBuilder.deliveryAddress;
        this.receiptCode = orderBuilder.receiptCode;
        this.status = OrderStatus.CREATED;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
        return Double.compare(amount, order.amount) == 0 &&
                receiptCode == order.receiptCode &&
                Objects.equals(customer, order.customer) &&
                Objects.equals(deliveryAddress, order.deliveryAddress) &&
                status == order.status &&
                Objects.equals(goodsInOrder, order.goodsInOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, amount, deliveryAddress, receiptCode, status, goodsInOrder);
    }

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer +
                ", amount=" + amount +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", receiptCode=" + receiptCode +
                ", status=" + status +
                ", goodsInOrder=" + goodsInOrder +
                '}';
    }

    public static class OrderBuilder{

        // Обязательно
        private final Customer customer;
        private final double amount;
        private final String deliveryAddress;
        private final int receiptCode;

        public OrderBuilder(Customer customer, double amount, String deliveryAddress, int receiptCode) {
            this.customer = customer;
            this.amount = amount;
            this.deliveryAddress = deliveryAddress;
            this.receiptCode = receiptCode;
        }

        public Order build() {
            return new Order(this);
        }
    }

}
