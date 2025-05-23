package com.example.odontogenda.controllers;

import com.example.odontogenda.models.Cliente;
import com.example.odontogenda.models.Dentista;
import com.example.odontogenda.services.ClienteService;
import com.example.odontogenda.services.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/listagem")
public class ListagemController {

    private final ClienteService clienteService;
    private final DentistaService dentistaService;

    @Autowired
    public ListagemController(ClienteService clienteService,
                              DentistaService dentistaService) {
        this.clienteService = clienteService;
        this.dentistaService = dentistaService;
    }

    @GetMapping
    public String listar(@RequestParam("tipo") String tipo, Model model) {
        if ("cliente".equalsIgnoreCase(tipo)) {
            List<Cliente> clientes = clienteService.findAll();
            model.addAttribute("usuarios", clientes);
        } else if ("dentista".equalsIgnoreCase(tipo)) {
            List<Dentista> dentistas = dentistaService.findAll();
            model.addAttribute("usuarios", dentistas);
        } else {
            throw new IllegalArgumentException("Tipo inválido: " + tipo);
        }
        model.addAttribute("tipo", tipo);
        return "listagem";
    }
}
