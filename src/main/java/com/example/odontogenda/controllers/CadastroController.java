package com.example.odontogenda.controllers;

import com.example.odontogenda.exceptions.InvalidUserPrefixException;
import com.example.odontogenda.models.Cliente;
import com.example.odontogenda.models.Dentista;
import com.example.odontogenda.services.ClienteService;
import com.example.odontogenda.services.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cadastro")
public class CadastroController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private DentistaService dentistaService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/cliente")
    public String formCliente(Model model, @RequestParam(value="error", required = false) String error) {
        model.addAttribute("cliente", new Cliente());
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "cadastroCliente";
    }

    @PostMapping("/cliente")
    public String cadastrarCliente(@ModelAttribute Cliente cliente) {
        cliente.setSenha(passwordEncoder.encode(cliente.getSenha()));
        clienteService.save(cliente);
        return "redirect:/login";
    }

    @GetMapping("/dentista")
    public String formDentista(Model model, @RequestParam(value="error", required = false) String error) {
        model.addAttribute("dentista", new Dentista());
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "cadastroDentista";
    }

    @PostMapping("/dentista")
    public String cadastrarDentista(@ModelAttribute Dentista dentista) {
        dentista.setSenha(passwordEncoder.encode(dentista.getSenha()));
        dentistaService.save(dentista);
        return "redirect:/login";
    }
}
