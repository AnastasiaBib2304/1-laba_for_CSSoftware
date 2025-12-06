package ru.mfa.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Customer {
    private Long id;

    @NotBlank(message = "Имя клиента обязательно.")
    private String firstName;

    @NotBlank(message = "Фамилия обязательна.")
    private String lastName;

    @Email(message = "Некорректный адрес электронной почты.")
    private String email;

    private String phone;
    private String address;
    private LocalDateTime registrationDate;
}