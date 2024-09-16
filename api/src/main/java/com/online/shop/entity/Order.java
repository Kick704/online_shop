package com.online.shop.entity;

import com.online.shop.enums.OrderStatus;
import com.online.shop.exception_handling.CommonRuntimeException;
import com.online.shop.enums.ErrorCode;
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
     * Покупатель
     */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /**
     * Итоговая стоимость заказа
     */
    @Column(name = "amount")
    private double amount;

    /**
     * Адрес доставки товара, указанный покупателем
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
     * Расчёт итоговой стоимости заказа и установка статуса при создании заказа
     */
    @PrePersist
    public void createOrder() {
        amount = goodsInOrder
                .stream()
                .mapToDouble(Goods::getPrice)
                .sum();
        status = OrderStatus.CREATED;
    }

    /**
     * Расчёт итоговой стоимости заказа при обновлении заказа
     */
    @PreUpdate
    public void updateOrder() {
        amount = goodsInOrder.stream().mapToDouble(Goods::getPrice).sum();
    }

    public Order() {
    }

    private Order(Builder builder) {
        setCustomer(builder.customer);
        setDeliveryAddress(builder.deliveryAddress);
        setReceiptCode(builder.receiptCode);
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
        return Objects.equals(id, order.id) &&
                Double.compare(amount, order.amount) == 0 &&
                receiptCode == order.receiptCode &&
                Objects.equals(customer, order.customer) &&
                Objects.equals(deliveryAddress, order.deliveryAddress) &&
                status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, amount, deliveryAddress, receiptCode, status);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", amount=" + amount +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", receiptCode=" + receiptCode +
                ", status=" + status +
                '}';
    }

    public static final class Builder {
        private Customer customer;
        private String deliveryAddress;
        private int receiptCode;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder customer(Customer val) {
            customer = val;
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
            if (customer == null || deliveryAddress == null) {
                throw new CommonRuntimeException(
                        ErrorCode.INTERNAL_SERVER_ERROR,
                        "Order: одно или несколько полей (customer, deliveryAddress) ссылаются на null"
                );
            }
            return new Order(this);
        }
    }
}
