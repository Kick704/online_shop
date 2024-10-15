package com.online.shop.entity;

import com.online.shop.exception_handling.CommonRuntimeException;
import com.online.shop.exception_handling.ErrorCode;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

/**
 * Класс-сущность категории товара интернет-магазина
 */
@Entity
@Table(name = "goods_categories")
public class GoodsCategory extends AbstractEntity {

    /**
     * Название категории товара
     */
    @Column(name = "name")
    private String name;

    /**
     * Список товаров данной категории
     */
    @OneToMany(mappedBy = "goodsCategory")
    private List<Goods> goodsInThisCategory;

    public GoodsCategory() {
    }

    private GoodsCategory(Builder builder) {
        setName(builder.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Goods> getGoodsInThisCategory() {
        return goodsInThisCategory;
    }

    public void setGoodsInThisCategory(List<Goods> goodsInThisCategory) {
        this.goodsInThisCategory = goodsInThisCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodsCategory that = (GoodsCategory) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "GoodsCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


    public static final class Builder {
        private String name;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder categoryName(String val) {
            name = val;
            return this;
        }

        public GoodsCategory build() {
            if (name == null) {
                throw new CommonRuntimeException(
                        ErrorCode.INTERNAL_SERVER_ERROR,
                        "GoodsCategory: поле name не может быть null"
                );
            }
            return new GoodsCategory(this);
        }
    }

}

