package com.example.odontogenda.messaging.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;

@Configuration
@EnableRabbit
public class RabbitConfig {

    public static final String QUEUE = "usuarios-queue";
    public static final String EXCHANGE = "usuarios-exchange";
    public static final String ROUTING_KEY = "usuarios.key";

    @Bean
    public Queue usuariosQueue() {
        return new Queue(QUEUE, true);
    }

    @Bean
    public DirectExchange usuariosExchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Binding binding(Queue usuariosQueue, DirectExchange usuariosExchange) {
        return BindingBuilder
                .bind(usuariosQueue)
                .to(usuariosExchange)
                .with(ROUTING_KEY);
    }
}
