package com.online.shop.entity;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;
import java.util.Objects;

/**
 * Класс-сущность товара интернет-магазина
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "goods")
public class Goods extends AbstractEntity {

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
     * Список заказов, в которых присутствует данный товар
     */
    @ManyToMany
    @JoinTable(name = "order_item",
            joinColumns = @JoinColumn(name = "goods_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Order> ordersWithThisGoods;

    protected Goods() {
    }

    private Goods(@NotNull GoodsBuilder goodsBuilder) {
        this.name = goodsBuilder.name;
        this.goodsCategory = goodsBuilder.goodsCategory;
        this.price = goodsBuilder.price;
        this.count = goodsBuilder.count;
        this.discount = goodsBuilder.discount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return Double.compare(price, goods.price) == 0 && count == goods.count &&
                Double.compare(discount, goods.discount) == 0 &&
                Objects.equals(name, goods.name) &&
                Objects.equals(goodsCategory, goods.goodsCategory) &&
                Objects.equals(ordersWithThisGoods, goods.ordersWithThisGoods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, goodsCategory, price, count, discount, ordersWithThisGoods);
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", goodsCategory=" + goodsCategory +
                ", price=" + price +
                ", count=" + count +
                ", discount=" + discount +
                ", ordersWithThisGoods=" + ordersWithThisGoods +
                '}';
    }

    public static class GoodsBuilder {

        //Обязательно
        private final String name;
        private final GoodsCategory goodsCategory;
        private final double price;
        private final int count;

        // Опционально
        private double discount;

        public GoodsBuilder(String name, GoodsCategory goodsCategory, double price, int count) {
            this.name = name;
            this.goodsCategory = goodsCategory;
            this.price = price;
            this.count = count;
        }

        public GoodsBuilder setDiscount(double discount) {
            this.discount = discount;
            return this;
        }

        public Goods build() {
            return new Goods(this);
        }
    }

}
