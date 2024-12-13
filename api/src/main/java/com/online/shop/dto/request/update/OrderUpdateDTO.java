package com.online.shop.dto.request.update;

import com.online.shop.dto.request.AbstractRequestDTO;
import com.online.shop.entity.Order;
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

    @Override
    public String toString() {
        return "OrderUpdateDTO{" +
                "deliveryAddress='" + deliveryAddress + '\'' +
                ", receiptCode=" + receiptCode +
                '}';
    }

}
