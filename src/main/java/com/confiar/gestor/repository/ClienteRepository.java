package com.confiar.gestor.repository;
import com.confiar.gestor.entity.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Clientes, Long> {
    Optional<Clientes> findByNit(String nit);
}