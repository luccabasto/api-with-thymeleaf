package com.example.odontogenda.controllers;

import com.example.odontogenda.models.Cliente;
import com.example.odontogenda.models.Dentista;
import com.example.odontogenda.services.ClienteService;
import com.example.odontogenda.services.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private DentistaService dentistaService;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("usuario") String usuario,
                               @RequestParam("senha") String senha,
                               Model model) {
        // Verifica prefixo
        if (usuario.startsWith("U")) {
            Cliente cliente = clienteService.buscarPorUsuario(usuario);
            if (cliente != null && cliente.getSenha().equals(senha)) {
                // Se login válido, redireciona para tela de listagem de clientes/dentistas
                return "redirect:/listagem?tipo=cliente";
            } else {
                model.addAttribute("erro", "Usuário ou senha inválidos (Cliente).");
                return "login";
            }
        } else if (usuario.startsWith("D")) {
            Dentista dentista = dentistaService.buscarPorUsuario(usuario);
            if (dentista != null && dentista.getSenha().equals(senha)) {
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
