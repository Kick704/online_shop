package com.online.shop.dto.response;

import com.online.shop.entity.GoodsCategory;

/**
 * DTO Категория товаров на основе сущности {@link GoodsCategory}
 */
public class GoodsCategoryResponseDTO extends AbstractResponseDTO {

    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "GoodsCategoryResponseDTO{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

}
