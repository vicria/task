package com.example.config;

import com.example.service.RootService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class CustomConfig {

    private final ApplicationContext context;

    public CustomConfig(ApplicationContext context) {
        this.context = context;
        log.info("Config добавлен");
    }

    @Bean
    public RootService factoryM(){
        return new RootService(context);
    }

}
