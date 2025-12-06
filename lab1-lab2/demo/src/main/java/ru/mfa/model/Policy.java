package ru.mfa.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class Policy {
    private Long id;

    @NotNull(message = "Необходимо указать ID клиента.")
    @Positive(message = "ID клиента должен быть положительным числом.")
    private Long customerId;

    @NotBlank(message = "Необходимо указать номер полиса.")
    private String policyNumber;

    private LocalDate startDate;
    private LocalDate endDate;

    @NotNull(message = "Сумма премии обязательна.")
    @Positive(message = "Сумма премии должна быть положительной.")
    private BigDecimal premiumAmount;

    private String status; // ACTIVE, EXPIRED, CANCELLED

    @NotNull(message = "Полис должен содержать хотя бы одно покрытие.")
    private Set<Long> coverageIds = new HashSet<>();
}