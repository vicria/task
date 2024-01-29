package com.example.service;

import com.example.entity.Root;
import com.example.repository.RootRepository;
import com.example.service.more.CustomAnnotation;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@CustomAnnotation
public class RootService {
    private final RootRepository rootRepository;
    private final ApplicationContext context;

    public RootService(RootRepository rootRepository, ApplicationContext context) {
        this.rootRepository = rootRepository;
        this.context = context;
        log.info("RootService конструктор");
    }

    @PostConstruct
    public void init(){
        log.info("PostConstruct");
    }

    public Root get(){
        Root byId = rootRepository.getById("1");
        return byId;
    }
}
