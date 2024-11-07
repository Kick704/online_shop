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

    @NotNull(message = "Не введён ID пользователя")
    @Schema(description = "ID пользователя")
    private UUID userId;

    @NotBlank(message = "Не введён адрес доставки")
    @Schema(description = "Адрес доставки")
    private String deliveryAddress;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
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
                "userId=" + userId +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                '}';
    }

}
