package com.confiar.gestor.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record TransaccionDto(
        @NotNull(message = "El número de cuenta es obligatorio")
        String numeroCuenta,

        @NotNull(message = "El monto es obligatorio")
        @Positive(message = "El monto debe ser mayor a cero")
        BigDecimal monto,

        @NotNull(message = "El tipo de movimiento es obligatorio")
        @Pattern(regexp = "DB|CR", message = "El tipo de movimiento debe ser 'DB' (Débito) o 'CR' (Crédito)")
        String tipoMovimiento
) {}