package com.example.odontogenda.messaging.model;

import java.io.Serializable;

public class UsuarioMessage implements Serializable {

    private String usuario;
    private String role;

    public UsuarioMessage() { }

    public UsuarioMessage(String usuario, String role) {
        this.usuario = usuario;
        this.role = role;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
