package com.online.shop.dto.request;

import com.online.shop.entity.Order;

import java.util.UUID;

/**
 * DTO для создания сущности Заказ {@link Order}
 */
public class OrderCreationDTO extends AbstractRequestDTO implements CreationDTO {

    private UUID customerId;
    private String deliveryAddress;

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public String toString() {
        return "OrderCreationDTO{" +
                "customerId=" + customerId +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                '}';
    }

}
