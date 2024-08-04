package com.online.shop.dto;

public class GoodsCategoryDTO extends AbstractDTO{

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
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

}
