package com.online.shop.dto.response;

import com.online.shop.entity.Goods;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO Товар на основе сущности {@link Goods}
 */
@Schema(description = "DTO Товар")
public class GoodsResponseDTO extends AbstractResponseDTO {

    @Schema(description = "Название")
    private String name;

    @Schema(description = "Категория товара")
    private GoodsCategoryResponseDTO goodsCategory;

    @Schema(description = "Стоимость товара в рублях")
    private double price;

    @Schema(description = "Скидка на товар в процентах")
    private int percentageDiscount;

    @Schema(description = "Количество на складе")
    private int count;

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

    public int getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(int percentageDiscount) {
        this.percentageDiscount= percentageDiscount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "GoodsResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", goodsCategory=" + goodsCategory +
                ", price=" + price +
                ", percentageDiscount=" + percentageDiscount +
                ", count=" + count +
                '}';
    }

}
