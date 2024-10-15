package com.online.shop.dto.request.update;

import com.online.shop.dto.request.AbstractRequestDTO;
import com.online.shop.entity.GoodsCategory;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO для изменения сущности Категория товаров {@link GoodsCategory}
 */
@Schema(description = "DTO для обновления категории товаров")
public class GoodsCategoryUpdateDTO extends AbstractRequestDTO implements UpdateDTO {

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
        return "GoodsCategoryUpdateDTO{" +
                "name='" + name + '\'' +
                '}';
    }

}
