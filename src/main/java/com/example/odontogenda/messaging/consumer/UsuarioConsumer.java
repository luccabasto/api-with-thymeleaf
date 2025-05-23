package com.example.odontogenda.messaging.consumer;

import com.example.odontogenda.messaging.config.RabbitConfig;
import com.example.odontogenda.messaging.model.UsuarioMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class UsuarioConsumer {

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void receiveMessage(UsuarioMessage message) {
        System.out.println(
                "🔔 Nova mensagem de usuário: " +
                        message.getUsuario() +
                        " com role " + message.getRole()
        );
    }
}
