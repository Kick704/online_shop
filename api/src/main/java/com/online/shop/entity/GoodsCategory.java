package com.online.shop.entity;

import com.online.shop.exception.UninitializedBuilderFieldException;
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
     * Наименование категории товара
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * Список товаров данной категории
     */
    @OneToMany(mappedBy = "goodsCategory")
    private List<Goods> goodsInThisCategory;

    public GoodsCategory() {
    }

    private GoodsCategory(Builder builder) {
        setCategoryName(builder.categoryName);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
                Objects.equals(categoryName, that.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryName);
    }

    @Override
    public String toString() {
        return "GoodsCategory{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }


    public static final class Builder {
        private String categoryName;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder categoryName(String val) {
            categoryName = val;
            return this;
        }

        public GoodsCategory build() {
            if (categoryName == null) {
                throw new UninitializedBuilderFieldException("GoodsCategory: поле categoryName ссылается на null");
            }
            return new GoodsCategory(this);
        }
    }

}

