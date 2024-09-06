package com.online.shop.dto.response;

import com.online.shop.entity.Goods;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO Товары на основе сущности {@link Goods}
 */
@Schema(description = "DTO Товар")
public class GoodsResponseDTO extends AbstractResponseDTO {

    @Schema(description = "Название")
    private String name;
    private GoodsCategoryResponseDTO goodsCategory;
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

    public GoodsCategoryResponseDTO getGoodsCategory() {
        return goodsCategory;
    }

    public void setGoodsCategory(GoodsCategoryResponseDTO goodsCategory) {
        this.goodsCategory = goodsCategory;
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
        return "GoodsResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", goodsCategory=" + goodsCategory +
                ", price=" + price +
                ", count=" + count +
                ", discount=" + discount +
                '}';
    }

}
