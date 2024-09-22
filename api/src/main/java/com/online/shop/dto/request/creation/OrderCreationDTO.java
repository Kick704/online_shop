package com.online.shop.dto.request.creation;

import com.online.shop.dto.request.AbstractRequestDTO;
import com.online.shop.entity.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * DTO для создания сущности Заказ {@link Order}
 */
@Schema(description = "DTO для создания заказа")
public class OrderCreationDTO extends AbstractRequestDTO implements CreationDTO {

    @NotNull(message = "Не введён ID покупателя")
    @Schema(description = "ID покупателя")
    private UUID customerId;

    @NotBlank(message = "Не введён адрес доставки")
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
