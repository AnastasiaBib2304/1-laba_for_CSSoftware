package ru.mfa.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Claim {
    private Long id;

    @NotNull(message = "Необходимо указать ID полиса.")
    @Positive(message = "ID полиса должен быть положительным числом.")
    private Long policyId;

    @NotNull(message = "Необходимо указать дату страхового случая.")
    private LocalDate incidentDate;

    private LocalDate claimDate;
    private String description;

    @NotNull(message = "Сумма претензии обязательна.")
    @Positive(message = "Сумма претензии должна быть положительной.")
    private BigDecimal claimAmount;

    private String status; // PENDING, APPROVED, REJECTED, PAID
    private String resolutionNotes;
}