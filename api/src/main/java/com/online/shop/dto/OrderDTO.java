package com.online.shop.dto;

public class OrderDTO {

    private CustomerDTO customer;
    private double amount;
    private String deliveryAddress;

    public OrderDTO() {
    }

    public OrderDTO(CustomerDTO customer, double amount, String deliveryAddress) {
        this.customer = customer;
        this.amount = amount;
        this.deliveryAddress = deliveryAddress;
    }

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
                "customer=" + customer +
                ", amount=" + amount +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                '}';
    }

}
