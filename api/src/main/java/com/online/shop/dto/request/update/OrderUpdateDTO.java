package com.online.shop.dto.request.update;

import com.online.shop.dto.request.AbstractRequestDTO;
import com.online.shop.entity.Order;
import com.online.shop.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO для изменения сущности Заказ {@link Order}
 */
@Schema(description = "DTO для обновления заказа")
public class OrderUpdateDTO extends AbstractRequestDTO implements UpdateDTO {

    @Schema(description = "Адрес доставки")
    private String deliveryAddress;

    @Schema(description = "Код для получения заказа")
    private Integer receiptCode;

    @Schema(description = "Статус заказа")
    private OrderStatus status;

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Integer getReceiptCode() {
        return receiptCode;
    }

    public void setReceiptCode(Integer receiptCode) {
        this.receiptCode = receiptCode;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderUpdateDTO{" +
                "deliveryAddress='" + deliveryAddress + '\'' +
                ", receiptCode=" + receiptCode +
                ", status=" + status +
                '}';
    }

}
