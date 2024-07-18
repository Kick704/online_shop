package com.online.shop.entity;

import com.online.shop.enums.OrderStatus;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Класс-сущность заказа из интернет-магазина
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    /**
     * Покупатель
     */
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /**
     * Полная стоимость заказа
     */
    @Column(name = "amount")
    private double amount;

    /**
     * Дата и время создания записи о заказе
     * <p>Устанавливается на уровне БД в момент создания записи, неизменно
     */
    @CreatedDate
    @Column(name = "created", updatable = false)
    private LocalDateTime created;

    /**
     * Дата и время обновления записи о заказе
     * <p>Устанавливается в момент обновления записи
     */
    @LastModifiedDate
    @Column(name = "modified")
    private LocalDateTime modified;

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
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    /**
     * Список товаров в заказе
     */
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "order_item",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "goods_id"))
    private List<Goods> goodsInOrder;

    public Order() {
    }

    public Order(Customer customer, double amount, String deliveryAddress, int receiptCode) {
        this.customer = customer;
        this.amount = amount;
        this.deliveryAddress = deliveryAddress;
        this.status = OrderStatus.CREATED;
        this.receiptCode = receiptCode;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
}
