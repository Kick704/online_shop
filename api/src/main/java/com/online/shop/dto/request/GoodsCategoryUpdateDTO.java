package com.online.shop.dto.request;

import com.online.shop.entity.GoodsCategory;

/**
 * DTO для изменения сущности Категория товаров {@link GoodsCategory}
 */
public class GoodsCategoryUpdateDTO extends AbstractRequestDTO implements UpdateDTO {

    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "GoodsCategoryUpdateDTO{" +
                "categoryName='" + categoryName + '\'' +
                '}';
    }

}
