package com.example.config;

import com.example.repository.RootRepository;
import com.example.service.NotificationService;
import com.example.service.RootService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class CustomConfig {

    private final RootRepository rootRepository;

    public CustomConfig(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
    }

    @Bean
    public RootService factoryM() {
        return new RootService(new NotificationService(factoryM()), rootRepository);
    }

}
