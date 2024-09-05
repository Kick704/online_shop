package com.online.shop.dto.request.creation;

import com.online.shop.dto.request.AbstractRequestDTO;
import com.online.shop.entity.Goods;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * DTO для создания сущности Товар {@link Goods}
 */
@Schema(description = "DTO для добавления товара")
public class GoodsCreationDTO extends AbstractRequestDTO implements CreationDTO {

    @NotBlank(message = "Не введено название товара")
    @Schema(description = "Название")
    private String name;

    @NotNull(message = "Не введён ID категории товаров")
    @Schema(description = "ID категории товаров")
    private UUID goodsCategoryId;

    @NotNull(message = "Не введена стоимость товара")
    @DecimalMin(value = "1.0", message = "Некорректная стоимость товара")
    @Schema(description = "Стоимость")
    private double price;

    @NotNull(message = "Не введено количество товаров на складе")
    @Min(value = 1, message = "На складе должен быть минимум 1 товар")
    @Schema(description = "Количество на складе")
    private int count;

    @DecimalMin(value = "0.0", message = "Некорректная скидка на товар")
    @Schema(description = "Скидка")
    private double discount;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
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
