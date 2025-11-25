package com.confiar.gestor.service;


import com.confiar.gestor.dto.CreacionCuentaDto;
import com.confiar.gestor.dto.TransaccionDto;
import com.confiar.gestor.entity.Clientes;
import com.confiar.gestor.entity.CuentaAhorros;
import com.confiar.gestor.exception.ExcepcionPrincipal;
import com.confiar.gestor.exception.RecursoNoEncontradoException;
import com.confiar.gestor.repository.ClienteRepository;
import com.confiar.gestor.repository.CuentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CuentaService {

    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;

    @Transactional
    public void crearCuenta(CreacionCuentaDto dto) {

        Clientes cliente = clienteRepository.findByNit(dto.nitCliente())
                .orElseThrow(() -> new RecursoNoEncontradoException("El cliente con NIT " + dto.nitCliente() + " no existe"));


        if (cuentaRepository.existsByNumeroCuenta(dto.numeroCuenta())) {
            throw new ExcepcionPrincipal("El número de cuenta ya está registrado", "CUENTA_DUPLICADA");
        }

        CuentaAhorros cuenta = new CuentaAhorros();
        cuenta.setNumeroCuenta(dto.numeroCuenta());
        cuenta.setSaldo(dto.saldoInicial());
        cuenta.setCliente(cliente);

        cuentaRepository.save(cuenta);
    }

    @Transactional
    public void realizarMovimiento(TransaccionDto dto) {

        CuentaAhorros cuenta = cuentaRepository.findByNumeroCuenta(dto.numeroCuenta())
                .orElseThrow(() -> new RecursoNoEncontradoException("Cuenta no encontrada"));


        if ("DB".equalsIgnoreCase(dto.tipoMovimiento())) {

            if (cuenta.getSaldo().compareTo(dto.monto()) < 0) {
                throw new ExcepcionPrincipal("Saldo insuficiente para realizar el débito", "SALDO_INSUFICIENTE");
            }
            cuenta.setSaldo(cuenta.getSaldo().subtract(dto.monto()));

        } else if ("CR".equalsIgnoreCase(dto.tipoMovimiento())) {
            cuenta.setSaldo(cuenta.getSaldo().add(dto.monto()));
        } else {
            throw new ExcepcionPrincipal("Tipo de movimiento inválido. Use 'DB' o 'CR'", "MOVIMIENTO_INVALIDO");
        }

        cuentaRepository.save(cuenta);
    }
}
