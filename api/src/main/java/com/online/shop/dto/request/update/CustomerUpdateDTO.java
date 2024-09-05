package com.online.shop.dto.request.update;

import com.online.shop.dto.request.AbstractRequestDTO;
import com.online.shop.entity.Customer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import static com.online.shop.dto.request.ValidationConstants.MIN_CUSTOMER_PASSWORD_LENGTH;
import static com.online.shop.dto.request.ValidationConstants.PHONE_NUMBER_REGEXP;

/**
 * DTO для изменения сущности Покупатель {@link Customer}
 */
@Schema(description = "DTO для обновления покупателя")
public class CustomerUpdateDTO extends AbstractRequestDTO implements UpdateDTO {

    @Schema(description = "Фамилия")
    private String surname;

    @Schema(description = "Имя")
    private String firstname;

    @Schema(description = "Отчество")
    private String patronymic;

    @Pattern(regexp = PHONE_NUMBER_REGEXP, message = "Некорректный номер телефона")
    @Schema(description = "Номер телефона")
    private String phoneNumber;

    @Email(message = "Некорректный e-mail")
    @Schema(description = "E-mail")
    private String email;

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
        return "CustomerUpdateDTO{" +
                "surname='" + surname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
