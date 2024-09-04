package com.online.shop.dto.request;

import com.online.shop.entity.Customer;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO для создания сущности Покупатель {@link Customer}
 */
@Schema(description = "DTO для регистрации покупателя")
public class CustomerCreationDTO extends AbstractRequestDTO implements CreationDTO {

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
    @Schema(description = "Пароль")
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CustomerCreationDTO{" +
                "surname='" + surname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
