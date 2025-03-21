package com.example.odontogenda.repositories;

import com.example.odontogenda.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Caso precise de buscas específicas, adicione aqui
    Cliente findByUsuario(String usuario);
}
