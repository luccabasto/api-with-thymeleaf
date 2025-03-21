package com.example.odontogenda.controllers;

import com.example.odontogenda.models.Cliente;
import com.example.odontogenda.models.Dentista;
import com.example.odontogenda.services.ClienteService;
import com.example.odontogenda.services.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ListagemController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private DentistaService dentistaService;

    @GetMapping("/listagem")
    public String listagem(@RequestParam("tipo") String tipo, Model model) {
        if (tipo.equalsIgnoreCase("cliente")) {
            List<Cliente> clientes = clienteService.listarTodos();
            model.addAttribute("listaClientes", clientes);
            return "listaClientes";
        } else if (tipo.equalsIgnoreCase("dentista")) {
            List<Dentista> dentistas = dentistaService.listarTodos();
            model.addAttribute("listaDentistas", dentistas);
            return "listaDentistas";
        } else {
            return "redirect:/";
        }
    }
}
