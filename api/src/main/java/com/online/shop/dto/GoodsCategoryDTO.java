package com.online.shop.dto;

public class GoodsCategoryDTO {

    private String categoryName;

    public GoodsCategoryDTO() {
    }

    public GoodsCategoryDTO(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "GoodsCategoryDTO{" +
                "categoryName='" + categoryName + '\'' +
                '}';
    }

}
