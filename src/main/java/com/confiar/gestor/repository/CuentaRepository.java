package com.confiar.gestor.repository;
import com.confiar.gestor.entity.CuentaAhorros;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CuentaRepository extends JpaRepository<CuentaAhorros, Long> {
    boolean existsByNumeroCuenta(String numeroCuenta);
    Optional<CuentaAhorros> findByNumeroCuenta(String numeroCuenta);
}