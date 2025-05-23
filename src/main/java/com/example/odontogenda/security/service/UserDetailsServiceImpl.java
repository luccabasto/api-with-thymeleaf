package com.example.odontogenda.security.service;

import com.example.odontogenda.auth.UsuarioBase;
import com.example.odontogenda.models.Cliente;
import com.example.odontogenda.models.Dentista;
import com.example.odontogenda.repositories.ClienteRepository;
import com.example.odontogenda.repositories.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ClienteRepository clienteRepo;
    private final DentistaRepository dentistaRepo;

    @Autowired
    public UserDetailsServiceImpl(ClienteRepository clienteRepo,
                                  DentistaRepository dentistaRepo) {
        this.clienteRepo = clienteRepo;
        this.dentistaRepo = dentistaRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioBase usuario = clienteRepo.findByUsuario(username)
                .map(c -> (UsuarioBase) c)
                .or(() -> dentistaRepo.findByUsuario(username).map(d -> (UsuarioBase) d))
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuário não encontrado: " + username)
                );

        return User.builder()
                .username(usuario.getUsuario())
                .password(usuario.getSenha())
                .authorities(usuario.getRole())
                .build();
    }
}
