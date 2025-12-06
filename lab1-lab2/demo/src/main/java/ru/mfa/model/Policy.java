package ru.mfa.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class Policy {
    private Long id;

    @NotNull(message = "Необходимо указать клиента.")
    private Customer customer;

    @NotNull(message = "Необходимо указать номер полиса.")
    private String policyNumber;

    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal premiumAmount;
    private String status; // ACTIVE, EXPIRED, CANCELLED

    @NotNull(message = "Полис должен содержать хотя бы одно покрытие.")
    private Set<Coverage> coverages = new HashSet<>();
}