package entity;

import jakarta.persistence.*;

import java.util.List;

/**
 * Класс-сущность товара интернет-магазина
 */
@Entity
@Table(name = "goods")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * Наименование товара
     */
    @Column(name = "name")
    private String name;

    /**
     * Категория товара
     */
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    private GoodsCategory goodsCategory;

    /**
     * Стоимость товара
     */
    @Column(name = "price")
    private double price;

    /**
     * Оставшееся количество товаров на складе
     */
    @Column(name = "count")
    private int count;

    /**
     * Установленная скидка на товар от 0 до 1 с двумя знаками после точки
     */
    @Column(name = "discount")
    private double discount;

    /**
     * Список заказов, в которых присутствует данный товар
     */
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "order_item",
            joinColumns = @JoinColumn(name = "goods_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Order> ordersWithThisGoods;

    public Goods() {
    }

    public Goods(String name, GoodsCategory goodsCategory, double price, int count, double discount) {
        this.name = name;
        this.goodsCategory = goodsCategory;
        this.price = price;
        this.count = count;
        this.discount = discount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GoodsCategory getGoodsCategory() {
        return goodsCategory;
    }

    public void setGoodsCategory(GoodsCategory goodsCategory) {
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

    public List<Order> getOrdersWithThisGoods() {
        return ordersWithThisGoods;
    }

    public void setOrdersWithThisGoods(List<Order> ordersWithThisGoods) {
        this.ordersWithThisGoods = ordersWithThisGoods;
    }

}
