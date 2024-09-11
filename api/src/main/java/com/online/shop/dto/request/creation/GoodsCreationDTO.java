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
    @DecimalMin(value = "1.0", message = "Некорректная стоимость товара")
    @Schema(description = "Стоимость товара")
    private double price;

    @NotNull(message = "Не введено количество товаров на складе")
    @Min(value = 1, message = "На складе должен быть минимум 1 товар")
    @Schema(description = "Количество на складе")
    private int count;

    @Min(value = 0, message = "Cкидка на товар не может быть меньше 0")
    @Max(value = 100, message = "Cкидка на товар не может быть больше 100")
    @Schema(description = "Скидка на товар")
    private int discount;

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

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
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
