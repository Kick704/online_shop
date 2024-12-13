package com.online.shop.entity;

import com.online.shop.exception_handling.CommonRuntimeException;
import com.online.shop.exception_handling.ErrorCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.List;
import java.util.Objects;

/**
 * Класс-сущность товара интернет-магазина
 */
@Entity
@Table(name = "goods")
public class Goods extends AbstractEntity {

    /**
     * Название товара
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
     * Стоимость товара в рублях
     */
    @Column(name = "price")
    private double price;

    /**
     * Установленная скидка на товар в процентах от 0% до 100%
     */
    @Column(name = "percentageDiscount")
    private int percentageDiscount;

    /**
     * Оставшееся количество товаров на складе
     */
    @Column(name = "count")
    private int count;

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
        setPercentageDiscount(builder.percentageDiscount);
        setCount(builder.count);
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

    public int getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(int percentageDiscount) {
        this.percentageDiscount= percentageDiscount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
                Objects.equals(name, goods.name) &&
                Double.compare(price, goods.price) == 0 &&
                Double.compare(percentageDiscount, goods.percentageDiscount) == 0 &&
                count == goods.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, percentageDiscount, count);
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", goodsCategory=" + goodsCategory +
                ", price=" + price +
                ", percentageDiscount=" + percentageDiscount +
                ", count=" + count +
                '}';
    }

    public static final class Builder {
        private String name;
        private GoodsCategory goodsCategory;
        private double price;
        private int percentageDiscount;
        private int count;

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

        public Builder percentageDiscount(int val) {
            percentageDiscount= val;
            return this;
        }

        public Builder count(int val) {
            count = val;
            return this;
        }

        public Goods build() {
            if (name == null || goodsCategory == null) {
                throw new CommonRuntimeException(
                        ErrorCode.INTERNAL_SERVER_ERROR,
                        "Goods: одно или несколько полей (name, goodsCategory) не могут быть null"
                );
            }
            return new Goods(this);
        }
    }

}
