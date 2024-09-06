package com.online.shop.dto.request;

import com.online.shop.entity.GoodsCategory;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO для создания сущности Категория товаров {@link GoodsCategory}
 */
@Schema(description = "DTO для добавления категории товаров")
public class GoodsCategoryCreationDTO extends AbstractRequestDTO implements CreationDTO {

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
        return "GoodsCategoryCreationDTO{" +
                "categoryName='" + categoryName + '\'' +
                '}';
    }

}
