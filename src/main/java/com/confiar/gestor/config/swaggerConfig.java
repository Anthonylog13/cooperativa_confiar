package com.confiar.gestor.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API Gestión Cooperativa",
                version = "1.0",
                description = "Documentación para la gestión de clientes y cuentas de ahorros."
        )
)
public class swaggerConfig {
}
