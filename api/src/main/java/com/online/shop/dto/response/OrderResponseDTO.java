package com.online.shop.dto.response;

import com.online.shop.entity.Order;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * DTO Заказ на основе сущности {@link Order}
 */
@Schema(description = "DTO Заказ")
public class OrderResponseDTO extends AbstractResponseDTO {

    private CustomerResponseDTO customer;

    @Schema(description = "Список товаров в заказе")
    private List<GoodsResponseDTO> goodsInOrder;

    @Schema(description = "Итоговая стоимость в рублях")
    private double amount;

    @Schema(description = "Адрес доставки")
    private String deliveryAddress;

    public CustomerResponseDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerResponseDTO customer) {
        this.customer = customer;
    }

    public List<GoodsResponseDTO> getGoodsInOrder() {
        return goodsInOrder;
    }

    public void setGoodsInOrder(List<GoodsResponseDTO> goodsInOrder) {
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
                ", customer=" + customer +
                ", goodsInOrder=" + goodsInOrder +
                ", amount=" + amount +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                '}';
    }

}
