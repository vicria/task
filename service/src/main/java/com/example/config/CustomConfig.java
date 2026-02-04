package com.example.config;

import com.example.repository.RootRepository;
import com.example.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Slf4j
@Configuration
public class CustomConfig {

    private final RootRepository rootRepository;

    public CustomConfig(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
    }

    @Bean
    public RootService factoryM() {
        return new RootService(notificationService(),
                rootRepository,
                List.of(new CommonCounterService(),
                        new CustomCounterService()));
    }

    @Bean
    public @Lazy NotificationService notificationService() {
        return new NotificationService(factoryM());
    }
}
