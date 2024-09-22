package com.online.shop.dto.request.creation;

import com.online.shop.dto.request.AbstractRequestDTO;
import com.online.shop.entity.GoodsCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO для создания сущности Категория товаров {@link GoodsCategory}
 */
@Schema(description = "DTO для добавления категории товаров")
public class GoodsCategoryCreationDTO extends AbstractRequestDTO implements CreationDTO {

    @NotBlank(message = "Не введено наименование категории товаров")
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
