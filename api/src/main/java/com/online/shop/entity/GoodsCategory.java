package com.online.shop.entity;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;
import java.util.Objects;

/**
 * Класс-сущность категории товара интернет-магазина
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "goods_categories")
public class GoodsCategory extends AbstractEntity{

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

    protected GoodsCategory() {
    }

    private GoodsCategory(@NotNull GoodsCategoryBuilder categoryBuilder) {
        this.categoryName = categoryBuilder.categoryName;
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
        return Objects.equals(categoryName, that.categoryName) &&
                Objects.equals(goodsInThisCategory, that.goodsInThisCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryName, goodsInThisCategory);
    }

    @Override
    public String toString() {
        return "GoodsCategory{" +
                "categoryName='" + categoryName + '\'' +
                ", goodsInThisCategory=" + goodsInThisCategory +
                '}';
    }

    public static class GoodsCategoryBuilder {

        //Обязательно
        private final String categoryName;

        public GoodsCategoryBuilder(String categoryName) {
            this.categoryName = categoryName;
        }

        public GoodsCategory build() {
            return new GoodsCategory(this);
        }
    }

}
