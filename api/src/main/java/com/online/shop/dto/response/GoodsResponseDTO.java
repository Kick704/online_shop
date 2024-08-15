package com.online.shop.dto.response;

import com.online.shop.entity.Goods;

/**
 * DTO Товары на основе сущности {@link Goods}
 */
public class GoodsResponseDTO extends AbstractResponseDTO {

    private String name;
    private GoodsCategoryResponseDTO goodsCategory;
    private double price;
    private int count;
    private double discount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GoodsCategoryResponseDTO getGoodsCategory() {
        return goodsCategory;
    }

    public void setGoodsCategory(GoodsCategoryResponseDTO goodsCategory) {
        this.goodsCategory = goodsCategory;
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
        return "GoodsResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", goodsCategory=" + goodsCategory +
                ", price=" + price +
                ", count=" + count +
                ", discount=" + discount +
                '}';
    }

}
