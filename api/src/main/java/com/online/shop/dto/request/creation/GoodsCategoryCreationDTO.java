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

    @NotBlank(message = "Не введено название категории товаров")
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
        return "GoodsCategoryCreationDTO{" +
                "name='" + name + '\'' +
                '}';
    }

}
