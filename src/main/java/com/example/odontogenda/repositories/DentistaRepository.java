package com.example.odontogenda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.odontogenda.models.Dentista;

public interface DentistaRepository extends JpaRepository<Dentista, Long> {
    // Caso precise de buscas específicas, adicione aqui
    Dentista findByUsuario(String usuario);
}
