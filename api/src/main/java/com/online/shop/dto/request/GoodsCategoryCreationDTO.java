package com.online.shop.dto.request;

import com.online.shop.entity.GoodsCategory;

/**
 * DTO для создания сущности Категория товаров {@link GoodsCategory}
 */
public class GoodsCategoryCreationDTO extends AbstractRequestDTO implements CreationDTO {

    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "GoodsCategoryCreationDTO{" +
                "categoryName='" + categoryName + '\'' +
                '}';
    }

}
