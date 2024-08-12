package com.online.shop.dto;

import com.online.shop.entity.Order;

/**
 * DTO Заказ на основе сущности {@link Order}
 */
public class OrderDTO extends AbstractDTO {

    private CustomerDTO customer;
    private double amount;
    private String deliveryAddress;

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
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
        return "OrderDTO{" +
                "id=" + id +
                ", customer=" + customer +
                ", amount=" + amount +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                '}';
    }

}
