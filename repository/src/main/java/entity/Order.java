package entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Класс-сущность заказа из интернет-магазина
 */
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * Покупатель
     */
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /**
     * Полная стоимость заказа
     */
    @Column(name = "amount")
    private double amount;

    /**
     * Дата и время создания заказа
     * <p>Устанавливается на уровне БД в момент создания записи о заказе
     */
    @Column(name = "creation_datetime")
    private LocalDateTime creationDateTime;

    /**
     * Адрес доставки товара, указанный покупателем
     */
    @Column(name = "delivery_address")
    private String deliveryAddress;

    /**
     * Код получения заказа
     */
    @Column(name = "receipt_code")
    private int receiptCode;

    /**
     * Статус заказа
     */
    @Column(name = "status")
    private String status;

    /**
     * Список товаров в заказе
     */
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "order_item",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "goods_id"))
    private List<Goods> goodsInOrder;

    public Order() {
    }

    public Order(Customer customer, double amount, String deliveryAddress, String status, int receiptCode) {
        this.customer = customer;
        this.amount = amount;
        this.deliveryAddress = deliveryAddress;
        this.status = status;
        this.receiptCode = receiptCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public int getReceiptCode() {
        return receiptCode;
    }

    public void setReceiptCode(int receiptCode) {
        this.receiptCode = receiptCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Goods> getGoodsInOrder() {
        return goodsInOrder;
    }

    public void setGoodsInOrder(List<Goods> goodsInOrder) {
        this.goodsInOrder = goodsInOrder;
    }

}
