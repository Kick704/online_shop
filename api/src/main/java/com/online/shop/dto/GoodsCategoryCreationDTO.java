package com.online.shop.dto;

public class GoodsCategoryCreationDTO {

    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "GoodsCategoryCreationDTO{" +
                "categoryName='" + categoryName + '\'' +
                '}';
    }

}
