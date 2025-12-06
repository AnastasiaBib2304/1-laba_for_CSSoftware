package ru.mfa.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Coverage {
    private Long id;

    @NotBlank(message = "Название покрытия обязательно.")
    private String name;

    private String description;

    @NotNull(message = "Необходимо указать лимит покрытия.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Лимит должен быть больше нуля.")
    private BigDecimal coverageLimit;

    @NotNull(message = "Необходимо указать франшизу.")
    @DecimalMin(value = "0.0", message = "Франшиза не может быть отрицательной.")
    private BigDecimal deductible;

    private BigDecimal premium;
}