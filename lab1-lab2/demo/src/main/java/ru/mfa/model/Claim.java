package ru.mfa.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Claim {
    private Long id;

    @NotNull(message = "Необходимо указать полис.")
    private Policy policy;

    @NotNull(message = "Необходимо указать дату страхового случая.")
    private LocalDate incidentDate;

    private LocalDate claimDate;
    private String description;
    private BigDecimal claimAmount;
    private String status; // PENDING, APPROVED, REJECTED, PAID
    private String resolutionNotes;
}