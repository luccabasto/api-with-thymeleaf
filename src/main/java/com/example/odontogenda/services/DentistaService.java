// src/main/java/com/example/odontogenda/services/DentistaService.java
package com.example.odontogenda.services;

import com.example.odontogenda.models.Dentista;
import com.example.odontogenda.repositories.DentistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {

    private final DentistaRepository dentistaRepository;

    public DentistaService(DentistaRepository dentistaRepository) {
        this.dentistaRepository = dentistaRepository;
    }

    public Dentista save(Dentista dentista) {
        return dentistaRepository.save(dentista);
    }

    public Optional<Dentista> findById(Long id) {
        return dentistaRepository.findById(id);
    }

    public Optional<Dentista> findByUsuario(String usuario) {
        return dentistaRepository.findByUsuario(usuario);
    }

    public List<Dentista> findAll() {
        return dentistaRepository.findAll();
    }

    public void deleteById(Long id) {
        dentistaRepository.deleteById(id);
    }
}
