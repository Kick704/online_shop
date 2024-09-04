package com.online.shop.dto.response;

import com.online.shop.entity.Customer;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO Покупатель на основе сущности {@link Customer}
 */
@Schema(description = "DTO Покупатель")
public class CustomerResponseDTO extends AbstractResponseDTO {

    @Schema(description = "Фамилия")
    private String surname;
    @Schema(description = "Имя")
    private String firstname;
    @Schema(description = "Отчество")
    private String patronymic;
    @Schema(description = "Номер телефона")
    private String phoneNumber;
    @Schema(description = "E-mail")
    private String email;
    @Schema(description = "Баланс")
    private double balance;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "CustomerResponseDTO{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                '}';
    }

}

