package com.example.config;

import com.example.service.CommonCounterService;
import com.example.service.CounterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfig {

    @Bean
    public CounterService commonCounterService() {
        return new CommonCounterService();
    }
}
