package com.online.shop.dto.request;

import com.online.shop.entity.Goods;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO для изменения сущности Товар {@link Goods}
 */
@Schema(description = "DTO для обновления товара")
public class GoodsUpdateDTO extends AbstractRequestDTO implements UpdateDTO {

    @Schema(description = "Название")
    private String name;
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
        return "GoodsUpdateDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", discount=" + discount +
                '}';
    }

}
