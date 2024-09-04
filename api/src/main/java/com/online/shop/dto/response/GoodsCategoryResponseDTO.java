package com.online.shop.dto.response;

import com.online.shop.entity.GoodsCategory;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO Категория товаров на основе сущности {@link GoodsCategory}
 */
@Schema(description = "DTO категория товаров")
public class GoodsCategoryResponseDTO extends AbstractResponseDTO {

    @Schema(description = "Наименование категории товаров")
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
