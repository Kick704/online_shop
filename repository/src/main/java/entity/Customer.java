package entity;

import jakarta.persistence.*;

import java.util.List;

/**
 * Класс-сущность покупателя интернет-магазина
 */
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * Имя покупателя
     */
    @Column(name = "firstname")
    private String firstname;

    /**
     * Фамилия покупателя
     */
    @Column(name = "surname")
    private String surname;

    /**
     * Номер телефона покупателя
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * Email покупателя
     */
    @Column(name = "email")
    private String email;

    /**
     * Пароль покупателя
     */
    @Column(name = "password")
    private String password;

    /**
     * Баланс покупателя
     */
    @Column(name = "balance")
    private double balance;

    /**
     * Статус аккаунта покупателя:
     * <p>0 - заблокирован
     * <p>1 - активен
     * <p>При создании устанавливается 0
     */
    @Column(name = "enabled")
    private boolean enabled;

    /**
     * Список заказов покупателя
     */
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;

    /**
     * Корзина покупателя
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "cart",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "goods_id"))
    private List<Goods> cart;

    public Customer() {
    }

    public Customer(String firstname, String surname, String phoneNumber, String email, String password) {
        this.firstname = firstname;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Goods> getCart() {
        return cart;
    }

    public void setCart(List<Goods> cart) {
        this.cart = cart;
    }

}
