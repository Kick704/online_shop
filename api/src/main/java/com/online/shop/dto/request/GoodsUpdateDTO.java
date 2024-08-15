package com.online.shop.dto.request;

import com.online.shop.entity.Goods;

/**
 * DTO для изменения сущности Товар {@link Goods}
 */
public class GoodsUpdateDTO extends AbstractRequestDTO implements UpdateDTO {

    private String name;
    private double price;
    private int count;
    private double discount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "GoodsUpdateDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", discount=" + discount +
                '}';
    }

}
