package entity;

import jakarta.persistence.*;

import java.util.List;

/**
 * Класс-сущность категории товара интернет-магазина
 */
@Entity
@Table(name = "goods_categories")
public class GoodsCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * Наименование категории товара
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * Список товаров данной категории
     */
    @OneToMany(mappedBy = "goodsCategory",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Goods> goodsInThisCategory;

    public GoodsCategory() {
    }

    public GoodsCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Goods> getGoodsInThisCategory() {
        return goodsInThisCategory;
    }

    public void setGoodsInThisCategory(List<Goods> goodsInThisCategory) {
        this.goodsInThisCategory = goodsInThisCategory;
    }

}
