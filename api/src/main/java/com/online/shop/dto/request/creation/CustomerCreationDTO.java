package com.online.shop.dto.request.creation;

import com.online.shop.dto.request.AbstractRequestDTO;
import com.online.shop.entity.Customer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import static com.online.shop.dto.request.ValidationConstants.MIN_CUSTOMER_PASSWORD_LENGTH;
import static com.online.shop.dto.request.ValidationConstants.PHONE_NUMBER_REGEXP;

/**
 * DTO для создания сущности Покупатель {@link Customer}
 */
@Schema(description = "DTO для регистрации покупателя")
public class CustomerCreationDTO extends AbstractRequestDTO implements CreationDTO {

    @NotBlank(message = "Не введена фамилия")
    @Schema(description = "Фамилия")
    private String surname;

    @NotBlank(message = "Не введено имя")
    @Schema(description = "Имя")
    private String firstname;

    @Schema(description = "Отчество")
    private String patronymic;

    @NotNull(message = "Не введён номер телефона")
    @Pattern(regexp = PHONE_NUMBER_REGEXP, message = "Некорректный номер телефона")
    @Schema(description = "Номер телефона (без спецсимволов)")
    private String phoneNumber;

    @NotNull(message = "Не введён e-mail")
    @Email(message = "Некорректный e-mail")
    @Schema(description = "E-mail")
    private String email;

    @NotNull(message = "Не введён пароль")
    @Size(min = MIN_CUSTOMER_PASSWORD_LENGTH,
            message = "Длина пароля должна составлять минимум " + MIN_CUSTOMER_PASSWORD_LENGTH + " символов")
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
