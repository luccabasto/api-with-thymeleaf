package com.example.odontogenda.repositories;

import com.example.odontogenda.models.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DentistaRepository extends JpaRepository<Dentista, Long> {
    Optional<Dentista> findByUsuario(String usuario);
}
