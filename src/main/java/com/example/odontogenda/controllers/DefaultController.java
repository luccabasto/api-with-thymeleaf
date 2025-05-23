package com.example.odontogenda.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping("/default")
    public String defaultAfterLogin(Authentication auth) {
        if (auth.getAuthorities().contains(
                new SimpleGrantedAuthority("ROLE_DENTISTA"))) {
            return "redirect:/listagem?tipo=dentista";
        }
        return "redirect:/listagem?tipo=cliente";
    }
}
