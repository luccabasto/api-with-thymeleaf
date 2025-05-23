package com.example.odontogenda.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // liberar endpoints do Actuator
                        .requestMatchers("/actuator/**").permitAll()
                        // rotas públicas
                        .requestMatchers("/", "/login", "/cadastro/**", "/css/**", "/js/**").permitAll()
                        // rotas protegidas por perfil
                        .requestMatchers("/listagem?tipo=cliente", "/cliente/**").hasRole("CLIENTE")
                        .requestMatchers("/listagem?tipo=dentista", "/dentista/**").hasRole("DENTISTA")
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
