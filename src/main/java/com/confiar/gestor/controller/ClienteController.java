package com.confiar.gestor.controller;

import com.confiar.gestor.dto.ClienteDto;
import com.confiar.gestor.dto.RespuestaDto;
import com.confiar.gestor.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @PostMapping
    public ResponseEntity<RespuestaDto> crearCliente(@RequestBody @Valid ClienteDto dto) {
        clienteService.crearCliente(dto);
        return ResponseEntity.ok(new RespuestaDto("Cliente creado exitosamente", "EXITO"));
    }

    @GetMapping("/{nit}")
    public ResponseEntity<ClienteDto> consultarCliente(@PathVariable String nit) {
        return ResponseEntity.ok(clienteService.consultarCliente(nit));
    }
}
