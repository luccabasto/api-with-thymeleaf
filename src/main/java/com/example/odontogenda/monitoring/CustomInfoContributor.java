package com.example.odontogenda.monitoring;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomInfoContributor implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("app", Map.of(
                "name", "OdontoGenda",
                "description", "Sistema de agendamento odontológico",
                "version", "1.0.0"
        ));
    }
}
