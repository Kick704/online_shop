package com.online.shop.dto.response;

import com.online.shop.entity.GoodsCategory;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO Категория товаров на основе сущности {@link GoodsCategory}
 */
@Schema(description = "DTO Категория товаров")
public class GoodsCategoryResponseDTO extends AbstractResponseDTO {

    @Schema(description = "Название категории товаров")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GoodsCategoryResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
