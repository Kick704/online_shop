package com.online.shop.dto.response;

import com.online.shop.entity.Order;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;
import java.util.UUID;

/**
 * DTO Заказ на основе сущности {@link Order}
 */
@Schema(description = "DTO Заказ")
public class OrderResponseDTO extends AbstractResponseDTO {

    @Schema(description = "ID пользователя")
    private UUID userId;

    @Schema(description = "Список товаров в заказе")
    private Set<SelectedGoodsDTO> goodsInOrder;

    @Schema(description = "Итоговая стоимость в рублях")
    private double amount;

    @Schema(description = "Адрес доставки")
    private String deliveryAddress;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Set<SelectedGoodsDTO> getGoodsInOrder() {
        return goodsInOrder;
    }

    public void setGoodsInOrder(Set<SelectedGoodsDTO> goodsInOrder) {
        this.goodsInOrder = goodsInOrder;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public String toString() {
        return "OrderResponseDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", goodsInOrder=" + goodsInOrder +
                ", amount=" + amount +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                '}';
    }
}
