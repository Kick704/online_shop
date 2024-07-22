package com.online.shop.entity;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;
import java.util.Objects;

/**
 * Класс-сущность покупателя интернет-магазина
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customers")
public class Customer extends AbstractEntity{

    /**
     * Фамилия покупателя
     */
    @Column(name = "surname")
    private String surname;

    /**
     * Имя покупателя
     */
    @Column(name = "firstname")
    private String firstname;

    /**
     * Отчество покупателя
     */
    @Column(name = "patronymic")
    private String patronymic;

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
     * <p>При создании на уровне БД устанавливается {@code 0.0}
     */
    @Column(name = "balance")
    private double balance;

    /**
     * Статус аккаунта покупателя:
     * <p>0 - заблокирован
     * <p>1 - активен
     * <p>При создании на уровне БД устанавливается {@code 1}
     */
    @Column(name = "enabled")
    private boolean enabled;

    /**
     * Список заказов покупателя
     */
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    /**
     * Корзина покупателя
     */
    @OneToMany
    @JoinTable(name = "cart",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "goods_id"))
    private List<Goods> goodsInCart;

    protected Customer() {
    }

    private Customer(@NotNull CustomerBuilder customerBuilder) {
        this.firstname = customerBuilder.firstname;
        this.surname = customerBuilder.surname;
        this.patronymic = customerBuilder.patronymic;
        this.phoneNumber = customerBuilder.phoneNumber;
        this.email = customerBuilder.email;
        this.password = customerBuilder.password;
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
        return goodsInCart;
    }

    public void setCart(List<Goods> goodsInCart) {
        this.goodsInCart = goodsInCart;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Double.compare(balance, customer.balance) == 0 && enabled == customer.enabled &&
                Objects.equals(surname, customer.surname) && Objects.equals(firstname, customer.firstname) &&
                Objects.equals(patronymic, customer.patronymic) && Objects.equals(phoneNumber, customer.phoneNumber) &&
                Objects.equals(email, customer.email) && Objects.equals(password, customer.password) &&
                Objects.equals(orders, customer.orders) && Objects.equals(goodsInCart, customer.goodsInCart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, firstname, patronymic, phoneNumber, email, password, balance, enabled, orders,
                goodsInCart);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "surname='" + surname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", enabled=" + enabled +
                ", orders=" + orders +
                ", goodsInCart=" + goodsInCart +
                '}';
    }

    public static class CustomerBuilder{

        // Обязательно
        private final String surname;
        private final String firstname;
        private final String phoneNumber;
        private final String email;
        private final String password;

        // Опционально
        private String patronymic;

        public CustomerBuilder(String surname, String firstname, String phoneNumber, String email, String password) {
            this.surname = surname;
            this.firstname = firstname;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.password = password;
        }

        public CustomerBuilder setPatronymic(String patronymic) {
            this.patronymic = patronymic;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }

}
