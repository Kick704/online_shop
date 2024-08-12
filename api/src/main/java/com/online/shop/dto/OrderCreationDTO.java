package com.online.shop.dto;

import java.util.UUID;

public class OrderCreationDTO {

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
