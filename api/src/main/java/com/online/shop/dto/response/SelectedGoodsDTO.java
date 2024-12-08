package com.online.shop.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO Товар с дополнительной информацией, включая количество, для отображения в корзине и заказе покупателя
 */
public class SelectedGoodsDTO extends AbstractResponseDTO {

    @Schema(description = "Название")
    private String name;

    @Schema(description = "Категории товара")
    private GoodsCategoryResponseDTO goodsCategory;

    @Schema(description = "Стоимость товара в рублях")
    private double price;

    @Schema(description = "Скидка на товар в процентах")
    private int percentageDiscount;

    @Schema(description = "Стоимость товара в рублях c учётом скидки")
    private double discountedPrice;

    @Schema(description = "Выбранное количество")
    private int quantity;

    @Schema(description = "Итоговая стоимость в рублях с учётом количества")
    private double totalPrice;

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

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "SelectedGoodsDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", goodsCategory=" + goodsCategory +
                ", price=" + price +
                ", percentageDiscount=" + percentageDiscount+
                ", discountedPrice=" + discountedPrice +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }

}
