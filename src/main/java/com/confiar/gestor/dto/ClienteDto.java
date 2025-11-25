package com.confiar.gestor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public record ClienteDto(
        @NotBlank(message = "El NIT es obligatorio")
        String nit,

        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @NotNull(message = "La fecha de ingreso es obligatoria")
        @PastOrPresent(message = "La fecha no puede ser futura")
        LocalDate fechaIngreso
) {}