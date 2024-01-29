package com.example.config;

import com.example.repository.RootRepository;
import com.example.service.RootService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class CustomConfig {

    private final ApplicationContext context;
    private final RootRepository repository;

    public CustomConfig(ApplicationContext context,
                        RootRepository repository) {
        this.context = context;
        this.repository = repository;
        log.info("Config добавлен");
    }

    @Bean
    public RootService factoryM(){
        return new RootService(repository, context);
    }

}
