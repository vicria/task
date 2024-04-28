package com.example.service;

import com.example.service.more.CustomAnnotation;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

@Slf4j
@CustomAnnotation
public class RootService {
    private final ApplicationContext context;

    public RootService(ApplicationContext context) {
        this.context = context;
        log.info("RootService конструктор");
    }

    @PostConstruct
    public void init(){
        log.info("PostConstruct");
    }

}
