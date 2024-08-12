package com.online.shop.dto;

import java.util.UUID;

public class GoodsCreationDTO {

    private String name;
    private UUID goodsCategoryId;
    private double price;
    private int count;
    private double discount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getGoodsCategoryId() {
        return goodsCategoryId;
    }

    public void setGoodsCategoryId(UUID goodsCategoryId) {
        this.goodsCategoryId = goodsCategoryId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "GoodsCreationDTO{" +
                "name='" + name + '\'' +
                ", goodsCategoryId=" + goodsCategoryId +
                ", price=" + price +
                ", count=" + count +
                ", discount=" + discount +
                '}';
    }

}
