package com.online.shop.dto;

public class GoodsDTO extends AbstractDTO{

    private String name;
    private GoodsCategoryDTO goodsCategory;
    private double price;
    private int count;
    private double discount;

    public GoodsDTO() {
    }

    public GoodsDTO(String name, GoodsCategoryDTO goodsCategory, double price, int count, double discount) {
        this.name = name;
        this.goodsCategory = goodsCategory;
        this.price = price;
        this.count = count;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GoodsCategoryDTO getGoodsCategory() {
        return goodsCategory;
    }

    public void setGoodsCategory(GoodsCategoryDTO goodsCategory) {
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
        return "GoodsDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", goodsCategory=" + goodsCategory +
                ", price=" + price +
                ", count=" + count +
                ", discount=" + discount +
                '}';
    }

}
