package com.online.shop.dto.request;

import com.online.shop.entity.Goods;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

/**
 * DTO для создания сущности Товар {@link Goods}
 */
@Schema(description = "DTO для добавления товара")
public class GoodsCreationDTO extends AbstractRequestDTO implements CreationDTO {

    @Schema(description = "Название")
    private String name;
    @Schema(description = "ID категории товаров")
    private UUID goodsCategoryId;
    @Schema(description = "Стоимость")
    private double price;
    @Schema(description = "Количество на складе")
    private int count;
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
