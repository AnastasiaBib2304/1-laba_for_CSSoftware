package ru.mfa.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Payment {
    private Long id;

    @NotNull(message = "Необходимо указать полис.")
    private Policy policy;

    @NotNull(message = "Сумма платежа обязательна.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Сумма платежа должна быть больше нуля.")
    private BigDecimal amount;

    @NotNull(message = "Дата платежа обязательна.")
    private LocalDate paymentDate;

    private String paymentMethod; // CARD, BANK_TRANSFER, CASH
    private String transactionId;
    private String status; // PENDING, COMPLETED, FAILED
}