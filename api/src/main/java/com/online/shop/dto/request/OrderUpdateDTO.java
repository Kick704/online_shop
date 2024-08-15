package com.online.shop.dto.request;

import com.online.shop.entity.Order;
import com.online.shop.enums.OrderStatus;

/**
 * DTO для изменения сущности Заказ {@link Order}
 */
public class OrderUpdateDTO extends AbstractRequestDTO implements UpdateDTO {

    private String deliveryAddress;
    private int receiptCode;
    private OrderStatus status;

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public int getReceiptCode() {
        return receiptCode;
    }

    public void setReceiptCode(int receiptCode) {
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
