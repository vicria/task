package com.example.config;

import com.example.repository.RootRepository;
import com.example.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class CustomConfig {

    @Bean
    public CounterService counterService() {
        return new CommonCounterService();
    }

}
