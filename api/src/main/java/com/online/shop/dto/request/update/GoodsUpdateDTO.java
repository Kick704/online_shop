package com.online.shop.dto.request.update;

import com.online.shop.dto.request.AbstractRequestDTO;
import com.online.shop.entity.Goods;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

/**
 * DTO для изменения сущности Товар {@link Goods}
 */
@Schema(description = "DTO для обновления товара")
public class GoodsUpdateDTO extends AbstractRequestDTO implements UpdateDTO {

    @Schema(description = "Название товара")
    private String name;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCount() {
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
        return "GoodsUpdateDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", discount=" + discount +
                '}';
    }

}
