package com.online.shop.entity;

import com.online.shop.exception.UninitializedBuilderFieldException;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

/**
 * Класс-сущность покупателя интернет-магазина
 */
@Entity
@Table(name = "customers")
public class Customer extends AbstractEntity {

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

    public Customer() {
    }

    private Customer(Builder builder) {
        setSurname(builder.surname);
        setFirstname(builder.firstname);
        setPatronymic(builder.patronymic);
        setPhoneNumber(builder.phoneNumber);
        setEmail(builder.email);
        setPassword(builder.password);
        setEnabled(builder.enabled);
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

    public List<Goods> getGoodsInCart() {
        return goodsInCart;
    }

    public void setGoodsInCart(List<Goods> goodsInCart) {
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
        return Objects.equals(id, customer.id) &&
                Objects.equals(surname, customer.surname) &&
                Objects.equals(firstname, customer.firstname) &&
                Objects.equals(patronymic, customer.patronymic) &&
                Objects.equals(phoneNumber, customer.phoneNumber) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(password, customer.password) &&
                Double.compare(balance, customer.balance) == 0 &&
                enabled == customer.enabled;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, firstname, patronymic, phoneNumber, email, password, balance, enabled);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", enabled=" + enabled +
                '}';
    }

    public static final class Builder {
        private String surname;
        private String firstname;
        private String patronymic;
        private String phoneNumber;
        private String email;
        private String password;
        private final boolean enabled = true;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder surname(String val) {
            surname = val;
            return this;
        }

        public Builder firstname(String val) {
            firstname = val;
            return this;
        }

        public Builder patronymic(String val) {
            patronymic = val;
            return this;
        }

        public Builder phoneNumber(String val) {
            phoneNumber = val;
            return this;
        }

        public Builder email(String val) {
            email = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Customer build() {
            if (surname == null || firstname == null || phoneNumber == null || email == null || password == null) {
                throw new UninitializedBuilderFieldException("Customer: одно или несколько полей (surname, firstname, " +
                        "phoneNumber, email, password) ссылаются на null");
            }
            return new Customer(this);
        }
    }

}


