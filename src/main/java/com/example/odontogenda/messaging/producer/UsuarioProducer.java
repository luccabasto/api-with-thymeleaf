package com.example.odontogenda.messaging.producer;

import com.example.odontogenda.messaging.config.RabbitConfig;
import com.example.odontogenda.messaging.model.UsuarioMessage;
import com.example.odontogenda.auth.UsuarioBase;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioProducer {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public UsuarioProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendUsuarioMessage(UsuarioBase user) {
        UsuarioMessage msg = new UsuarioMessage(user.getUsuario(), user.getRole());
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                RabbitConfig.ROUTING_KEY,
                msg
        );
    }
}
