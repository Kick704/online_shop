package com.online.shop.entity;

import com.online.shop.exception.UninitializedBuilderFieldException;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

/**
 * Класс-сущность товара интернет-магазина
 */
@Entity
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
    @ManyToOne(fetch = FetchType.LAZY)
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

    public Goods() {
    }

    private Goods(Builder builder) {
        setName(builder.name);
        setGoodsCategory(builder.goodsCategory);
        setPrice(builder.price);
        setCount(builder.count);
        setDiscount(builder.discount);
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
        return Objects.equals(id, goods.id) &&
                Double.compare(price, goods.price) == 0 &&
                count == goods.count &&
                Double.compare(discount, goods.discount) == 0 &&
                Objects.equals(name, goods.name) &&
                Objects.equals(goodsCategory, goods.goodsCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, goodsCategory, price, count, discount);
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", goodsCategory=" + goodsCategory +
                ", price=" + price +
                ", count=" + count +
                ", discount=" + discount +
                '}';
    }


    public static final class Builder {
        private String name;
        private GoodsCategory goodsCategory;
        private double price;
        private int count;
        private double discount;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder goodsCategory(GoodsCategory val) {
            goodsCategory = val;
            return this;
        }

        public Builder price(double val) {
            price = val;
            return this;
        }

        public Builder count(int val) {
            count = val;
            return this;
        }

        public Builder discount(double val) {
            discount = val;
            return this;
        }

        public Goods build() {
            if (name == null && goodsCategory == null) {
                throw new UninitializedBuilderFieldException("Goods: одно или несколько полей (name, goodsCategory) " +
                        "ссылаются на null");
            }
            return new Goods(this);
        }
    }
}
