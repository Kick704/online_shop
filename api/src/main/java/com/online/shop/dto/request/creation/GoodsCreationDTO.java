package com.online.shop.dto.request.creation;

import com.online.shop.dto.request.AbstractRequestDTO;
import com.online.shop.entity.Goods;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.UUID;

/**
 * DTO для создания сущности Товар {@link Goods}
 */
@Schema(description = "DTO для добавления товара")
public class GoodsCreationDTO extends AbstractRequestDTO implements CreationDTO {

    @NotBlank(message = "Не введено название товара")
    @Schema(description = "Название товара")
    private String name;

    @NotNull(message = "Не введён ID категории товаров")
    @Schema(description = "ID категории товаров")
    private UUID goodsCategoryId;

    @NotNull(message = "Не введена стоимость товара")
    @DecimalMin(value = "0.01", message = "Некорректная стоимость товара (минимум 0.01 руб.)")
    @Digits(integer = 15, fraction = 2, message = "Некорректная стоимость товара (до двух цифр в дробной части)")
    @Schema(description = "Стоимость товара в рублях")
    private Double price;

    @Min(value = 0, message = "Некорректное значение числа товаров на складе")
    @Schema(description = "Количество на складе")
    private Integer count;

    @Min(value = 0, message = "Cкидка на товар не может быть меньше 0%")
    @Max(value = 100, message = "Cкидка на товар не может быть больше 100%")
    @Schema(description = "Скидка на товар в процентах")
    private Integer discount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getGoodsCategoryId() {
        return goodsCategoryId;
    }

    public void setGoodsCategoryId(UUID goodsCategoryId) {
        this.goodsCategoryId = goodsCategoryId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public @NotNull Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "GoodsCreationDTO{" +
                "name='" + name + '\'' +
                ", goodsCategoryId=" + goodsCategoryId +
                ", price=" + price +
                ", count=" + count +
                ", discount=" + discount +
                '}';
    }

}
