package com.example.odontogenda.controllers;

import com.example.odontogenda.exceptions.InvalidUserPrefixException;
import com.example.odontogenda.models.Cliente;
import com.example.odontogenda.models.Dentista;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidUserPrefixException.class)
    public String handleInvalidUserPrefixException(InvalidUserPrefixException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        // Verifica se é de cliente ou dentista e retorna o template adequado
        if ("cliente".equalsIgnoreCase(ex.getTipo())) {
            model.addAttribute("cliente", new Cliente());
            return "cadastroCliente";
        } else if ("dentista".equalsIgnoreCase(ex.getTipo())) {
            model.addAttribute("dentista", new Dentista());
            return "cadastroDentista";
        }
        return "redirect:/";
    }
}
