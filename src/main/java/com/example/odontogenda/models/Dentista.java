package com.example.odontogenda.models;

import com.example.odontogenda.auth.UsuarioBase;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "OG_DENTISTAS")
public class Dentista implements UsuarioBase {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "NUMERO_TELEFONE")
    private String numeroTelefone;

    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    @Column(name = "NOME_COMPLETO", nullable = false)
    private String nomeCompleto;

    @Column(name = "USUARIO", nullable = false)    // deve começar com 'D'
    private String usuario;

    @Column(name = "SENHA", nullable = false)
    private String senha;

    public Dentista() {}

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNumeroTelefone() { return numeroTelefone; }
    public void setNumeroTelefone(String numeroTelefone) { this.numeroTelefone = numeroTelefone; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }

    @Override
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    @Override
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    @Override
    public String getRole() { return "ROLE_DENTISTA"; }
}
