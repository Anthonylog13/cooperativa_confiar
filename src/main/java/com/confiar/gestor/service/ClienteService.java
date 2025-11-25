package com.confiar.gestor.service;

import com.confiar.gestor.dto.ClienteDto;
import com.confiar.gestor.entity.Clientes;
import com.confiar.gestor.exception.ExcepcionPrincipal;
import com.confiar.gestor.exception.RecursoNoEncontradoException;
import com.confiar.gestor.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    @Transactional
    public void crearCliente(ClienteDto dto) {
        if (clienteRepository.findByNit(dto.nit()).isPresent()) {
            throw new ExcepcionPrincipal("El cliente con este NIT ya existe", "CLIENTE_YA_EXISTE");
        }

        Clientes cliente = new Clientes();
        cliente.setNit(dto.nit());
        cliente.setNombre(dto.nombre());
        cliente.setFechaIngreso(dto.fechaIngreso());

        clienteRepository.save(cliente);
    }

    @Transactional(readOnly = true)
    public ClienteDto consultarCliente(String nit) {
        Clientes cliente = clienteRepository.findByNit(nit)
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado con NIT: " + nit));


        return new ClienteDto(cliente.getNit(), cliente.getNombre(), cliente.getFechaIngreso());
    }
}