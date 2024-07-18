package com.online.shop.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Класс-сущность категории товара интернет-магазина
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "goods_categories")
public class GoodsCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    /**
     * Наименование категории товара
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * Дата и время создания записи о категории товара
     * <p>Устанавливается на уровне БД в момент создания записи, неизменно
     */
    @CreatedDate
    @Column(name = "created", updatable = false)
    private LocalDateTime created;

    /**
     * Дата и время обновления записи о категории товара
     * <p>Устанавливается в момент обновления записи
     */
    @LastModifiedDate
    @Column(name = "modified")
    private LocalDateTime modified;

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
}
