package com.transaccion.transaccion.repository;

import com.transaccion.transaccion.entity.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Integer> {
    Optional<Registro> findByDescripcion (String descripcion);
    boolean existsByDescripcion (String descripcion);

}
