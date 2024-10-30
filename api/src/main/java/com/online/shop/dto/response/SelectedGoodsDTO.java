package com.online.shop.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

/**
 * DTO Товар с дополнительной информацией, включая количество, для отображения в корзине и заказе покупателя
 */
public class SelectedGoodsDTO extends AbstractResponseDTO{

    @Schema(description = "Название")
    private String name;

    @Schema(description = "ID категории товаров")
    private UUID goodsCategoryId;

    @Schema(description = "Стоимость товара в рублях")
    private double price;

    @Schema(description = "Скидка на товар в процентах")
    private int discount;

    @Schema(description = "Стоимость товара в рублях c учётом скидки")
    private double discountedPrice;

    @Schema(description = "Итоговая стоимость в рублях с учётом количества")
    private double totalPrice;

    @Schema(description = "Выбранное количество")
    private int quantity;

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

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "SelectedGoodsDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", goodsCategoryId=" + goodsCategoryId +
                ", price=" + price +
                ", discount=" + discount +
                ", discountedPrice=" + discountedPrice +
                ", totalPrice=" + totalPrice +
                ", quantity=" + quantity +
                '}';
    }

}
