package com.online.shop.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Класс-сущность товара интернет-магазина
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "goods")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    /**
     * Наименование товара
     */
    @Column(name = "name")
    private String name;

    /**
     * Категория товара
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private GoodsCategory goodsCategory;

    /**
     * Стоимость товара
     */
    @Column(name = "price")
    private double price;

    /**
     * Оставшееся количество товаров на складе
     */
    @Column(name = "count")
    private int count;

    /**
     * Установленная скидка на товар от 0 до 1 с двумя знаками после точки
     */
    @Column(name = "discount")
    private double discount;

    /**
     * Дата и время создания записи о товаре
     * <p>Устанавливается на уровне БД в момент создания записи, неизменно
     */
    @CreatedDate
    @Column(name = "created", updatable = false)
    private LocalDateTime created;

    /**
     * Дата и время обновления записи о товаре
     * <p>Устанавливается в момент обновления записи
     */
    @LastModifiedDate
    @Column(name = "modified")
    private LocalDateTime modified;

    /**
     * Список заказов, в которых присутствует данный товар
     */
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "order_item",
            joinColumns = @JoinColumn(name = "goods_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Order> ordersWithThisGoods;

    public Goods() {
    }

    public Goods(String name, GoodsCategory goodsCategory, double price, int count, double discount) {
        this.name = name;
        this.goodsCategory = goodsCategory;
        this.price = price;
        this.count = count;
        this.discount = discount;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GoodsCategory getGoodsCategory() {
        return goodsCategory;
    }

    public void setGoodsCategory(GoodsCategory goodsCategory) {
        this.goodsCategory = goodsCategory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public List<Order> getOrdersWithThisGoods() {
        return ordersWithThisGoods;
    }

    public void setOrdersWithThisGoods(List<Order> ordersWithThisGoods) {
        this.ordersWithThisGoods = ordersWithThisGoods;
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
