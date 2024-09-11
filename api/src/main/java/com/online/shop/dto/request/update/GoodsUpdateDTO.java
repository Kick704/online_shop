package com.online.shop.dto.request.update;

import com.online.shop.dto.request.AbstractRequestDTO;
import com.online.shop.entity.Goods;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

/**
 * DTO для изменения сущности Товар {@link Goods}
 */
@Schema(description = "DTO для обновления товара")
public class GoodsUpdateDTO extends AbstractRequestDTO implements UpdateDTO {

    @Schema(description = "Название товара")
    private String name;

    @DecimalMin(value = "1.0", message = "Некорректная стоимость товара")
    @Schema(description = "Стоимость товара")
    private double price;

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
        return "GoodsUpdateDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", discount=" + discount +
                '}';
    }

}
