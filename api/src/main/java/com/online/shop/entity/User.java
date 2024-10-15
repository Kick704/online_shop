package com.online.shop.entity;

import com.online.shop.exception_handling.CommonRuntimeException;
import com.online.shop.exception_handling.ErrorCode;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс-сущность пользователя интернет-магазина
 */
@Entity
@Table(name = "users")
public class User extends AbstractEntity implements UserDetails {

    /**
     * Фамилия пользователя
     */
    @Column(name = "surname")
    private String surname;

    /**
     * Имя пользователя
     */
    @Column(name = "firstname")
    private String firstname;

    /**
     * Отчество пользователя
     */
    @Column(name = "patronymic")
    private String patronymic;

    /**
     * Номер телефона пользователя
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * Email пользователя
     */
    @Column(name = "email")
    private String email;

    /**
     * Пароль пользователя
     */
    @Column(name = "password")
    private String password;

    /**
     * Баланс пользователя
     * <p>При создании на уровне БД устанавливается {@code 0.0}
     */
    @Column(name = "balance")
    private double balance;

    /**
     * Статус аккаунта пользователя:
     * <p>{@code false} - заблокирован
     * <p>{@code true} - активен
     */
    @Column(name = "enabled")
    private boolean enabled;

    /**
     * Набор ролей пользователя
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    /**
     * Список заказов пользователя
     */
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    /**
     * Корзина пользователя
     */
    @OneToMany
    @JoinTable(name = "cart",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "goods_id"))
    private List<Goods> goodsInCart;

    /**
     * Конфигурация аккаунта при регистрации пользователя
     */
    @PrePersist
    public void createUser() {
        enabled = true;
    }

    public User() {
    }

    private User(Builder builder) {
        setSurname(builder.surname);
        setFirstname(builder.firstname);
        setPatronymic(builder.patronymic);
        setPhoneNumber(builder.phoneNumber);
        setEmail(builder.email);
        setPassword(builder.password);
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles == null ? Collections.emptyList() : roles.stream()
                .flatMap(role -> role.getPrivileges().stream()
                        .map(Privilege::getName)
                        .map(SimpleGrantedAuthority::new))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getEmail();
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(firstname, user.firstname) &&
                Objects.equals(patronymic, user.patronymic) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Double.compare(balance, user.balance) == 0 &&
                enabled == user.enabled;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, firstname, patronymic, phoneNumber, email, password, balance, enabled);
    }

    @Override
    public String toString() {
        return "User{" +
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

        public User build() {
            if (surname == null || firstname == null || phoneNumber == null || email == null || password == null) {
                throw new CommonRuntimeException(
                        ErrorCode.INTERNAL_SERVER_ERROR,
                        "User: одно или несколько полей (surname, firstname, phoneNumber, email, password) " +
                                "ссылаются на null"
                );
            }
            return new User(this);
        }
    }

}


