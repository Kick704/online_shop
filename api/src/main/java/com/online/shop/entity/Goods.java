package com.online.shop.entity;

import com.online.shop.exception_handling.CommonRuntimeException;
import com.online.shop.exception_handling.ErrorCode;
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
    @Column(name = "goods_name")
    private String goodsName;

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
     * Оставшееся количество товаров на складе
     */
    @Column(name = "count")
    private int count;

    /**
     * Установленная скидка на товар в процентах от 0% до 100%
     */
    @Column(name = "discount")
    private int discount;

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
        setGoodsName(builder.goodsName);
        setGoodsCategory(builder.goodsCategory);
        setPrice(builder.price);
        setCount(builder.count);
        setDiscount(builder.discount);
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
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

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
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
                Objects.equals(goodsName, goods.goodsName) &&
                Objects.equals(goodsCategory, goods.goodsCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, goodsName, goodsCategory, price, count, discount);
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", goodsCategory=" + goodsCategory +
                ", price=" + price +
                ", count=" + count +
                ", discount=" + discount +
                '}';
    }

    public static final class Builder {
        private String goodsName;
        private GoodsCategory goodsCategory;
        private double price;
        private int count;
        private int discount;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder name(String val) {
            goodsName = val;
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

        public Builder discount(int val) {
            discount = val;
            return this;
        }

        public Goods build() {
            if (goodsName == null || goodsCategory == null) {
                throw new CommonRuntimeException(
                        ErrorCode.INTERNAL_SERVER_ERROR,
                        "Goods: одно или несколько полей (goodsName, goodsCategory) ссылаются на null"
                );
            }
            return new Goods(this);
        }
    }
}
