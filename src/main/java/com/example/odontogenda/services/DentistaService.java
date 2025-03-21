package com.example.odontogenda.services;

import com.example.odontogenda.models.Dentista;
import com.example.odontogenda.repositories.DentistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistaService {

    private final DentistaRepository dentistaRepository;

    public DentistaService(DentistaRepository dentistaRepository) {
        this.dentistaRepository = dentistaRepository;
    }

    public Dentista salvar(Dentista dentista) {
        return dentistaRepository.save(dentista);
    }

    public List<Dentista> listarTodos() {
        return dentistaRepository.findAll();
    }

    public Dentista buscarPorId(Long id) {
        return dentistaRepository.findById(id).orElse(null);
    }

    public void deletar(Long id) {
        dentistaRepository.deleteById(id);
    }

    public Dentista buscarPorUsuario(String usuario) {
        return dentistaRepository.findByUsuario(usuario);
    }
}
