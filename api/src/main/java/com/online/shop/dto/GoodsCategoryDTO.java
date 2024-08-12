package com.online.shop.dto;

import com.online.shop.entity.GoodsCategory;

/**
 * DTO Категория товаров на основе сущности {@link GoodsCategory}
 */
public class GoodsCategoryDTO extends AbstractDTO {

    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "GoodsCategoryDTO{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

}
