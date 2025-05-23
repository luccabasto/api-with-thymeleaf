package com.example.odontogenda.controllers;

import com.example.odontogenda.models.Cliente;
import com.example.odontogenda.models.Dentista;
import com.example.odontogenda.services.ClienteService;
import com.example.odontogenda.services.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class LoginController {

    private final ClienteService clienteService;
    private final DentistaService dentistaService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginController(ClienteService clienteService,
                           DentistaService dentistaService,
                           PasswordEncoder passwordEncoder) {
        this.clienteService    = clienteService;
        this.dentistaService   = dentistaService;
        this.passwordEncoder   = passwordEncoder;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("usuario") String usuario,
                               @RequestParam("senha") String senha,
                               Model model) {
        // Validação de prefixo U ou D
        if (usuario.startsWith("U")) {
            Optional<Cliente> optCli = clienteService.findByUsuario(usuario);
            if (optCli.isPresent() &&
                    passwordEncoder.matches(senha, optCli.get().getSenha())) {
                return "redirect:/listagem?tipo=cliente";
            } else {
                model.addAttribute("erro", "Usuário ou senha inválidos (Cliente).");
                return "login";
            }

        } else if (usuario.startsWith("D")) {
            Optional<Dentista> optDen = dentistaService.findByUsuario(usuario);
            if (optDen.isPresent() &&
                    passwordEncoder.matches(senha, optDen.get().getSenha())) {
                return "redirect:/listagem?tipo=dentista";
            } else {
                model.addAttribute("erro", "Usuário ou senha inválidos (Dentista).");
                return "login";
            }

        } else {
            model.addAttribute("erro", "Prefixo de usuário inválido. Use 'U' ou 'D'.");
            return "login";
        }
    }
}
