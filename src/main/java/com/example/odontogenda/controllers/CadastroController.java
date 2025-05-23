package com.example.odontogenda.controllers;

import com.example.odontogenda.models.Cliente;
import com.example.odontogenda.models.Dentista;
import com.example.odontogenda.messaging.producer.UsuarioProducer;
import com.example.odontogenda.services.ClienteService;
import com.example.odontogenda.services.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cadastro")
public class CadastroController {

    @Autowired private ClienteService clienteService;
    @Autowired private DentistaService dentistaService;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private UsuarioProducer producer;

    @PostMapping("/cliente")
    public String cadastrarCliente(@ModelAttribute Cliente cliente) {
        cliente.setSenha(passwordEncoder.encode(cliente.getSenha()));
        Cliente salva = clienteService.save(cliente);
        producer.sendUsuarioMessage(salva);
        return "redirect:/login";
    }

    @PostMapping("/dentista")
    public String cadastrarDentista(@ModelAttribute Dentista dentista) {
        dentista.setSenha(passwordEncoder.encode(dentista.getSenha()));
        Dentista salva = dentistaService.save(dentista);
        producer.sendUsuarioMessage(salva);
        return "redirect:/login";
    }
}
