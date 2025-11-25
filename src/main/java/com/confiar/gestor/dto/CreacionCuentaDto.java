package com.confiar.gestor.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record CreacionCuentaDto(
        @NotBlank(message = "El NIT del cliente es obligatorio")
        String nitCliente,

        @NotBlank(message = "El número de cuenta es obligatorio")
        String numeroCuenta,

        @NotNull(message = "El saldo inicial es obligatorio")
        @Min(value = 0, message = "El saldo inicial no puede ser negativo")
        BigDecimal saldoInicial
) {}