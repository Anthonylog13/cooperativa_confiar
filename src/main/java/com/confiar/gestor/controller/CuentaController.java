package com.confiar.gestor.controller;

import com.confiar.gestor.dto.CreacionCuentaDto;
import com.confiar.gestor.dto.RespuestaDto;
import com.confiar.gestor.dto.TransaccionDto;
import com.confiar.gestor.service.CuentaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }


    @PostMapping
    public ResponseEntity<RespuestaDto> crearCuenta(@RequestBody @Valid CreacionCuentaDto dto) {
        cuentaService.crearCuenta(dto);
        return ResponseEntity.ok(new RespuestaDto("Cuenta creada exitosamente", "EXITO"));
    }

    @PostMapping("/movimientos")
    public ResponseEntity<RespuestaDto> realizarMovimiento(@RequestBody @Valid TransaccionDto dto) {
        cuentaService.realizarMovimiento(dto);
        return ResponseEntity.ok(new RespuestaDto("Movimiento realizado exitosamente", "EXITO"));
    }
}
