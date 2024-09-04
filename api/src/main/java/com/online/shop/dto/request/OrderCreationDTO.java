package com.online.shop.dto.request;

import com.online.shop.entity.Order;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

/**
 * DTO для создания сущности Заказ {@link Order}
 */
@Schema(description = "DTO для создания заказа")
public class OrderCreationDTO extends AbstractRequestDTO implements CreationDTO {

    @Schema(description = "ID покупателя")
    private UUID customerId;
    @Schema(description = "Адрес доставки")
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
