package com.online.shop.dto.response;

import com.online.shop.entity.Order;
import com.online.shop.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;

/**
 * DTO Заказ на основе сущности {@link Order}
 */
@Schema(description = "DTO Заказ")
public class OrderResponseDTO extends AbstractResponseDTO {

    @Schema(description = "Пользователь")
    private UserResponseDTO user;

    @Schema(description = "Список товаров в заказе")
    private Set<SelectedGoodsDTO> goodsInOrder;

    @Schema(description = "Итоговая стоимость в рублях")
    private double amount;

    @Schema(description = "Адрес доставки")
    private String deliveryAddress;

    @Schema(description = "Статус заказа")
    private OrderStatus status;

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderResponseDTO{" +
                "id=" + id +
                ", user=" + user +
                ", goodsInOrder=" + goodsInOrder +
                ", amount=" + amount +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", status=" + status +
                '}';
    }

}
