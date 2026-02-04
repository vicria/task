package com.example.service;

import com.example.repository.RootRepository;
import com.example.service.more.CustomAnnotation;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@CustomAnnotation
public class RootService {

    private final NotificationService notificationService;
    private final RootRepository rootRepository;

    public RootService(NotificationService notificationService, RootRepository rootRepository) {
        this.notificationService = notificationService;
        this.rootRepository = rootRepository;
        log.info("RootService конструктор");
    }

    @PostConstruct
    public void init(){
        log.info("PostConstruct");
    }

    public void generalMethod() {
        log.info("Root Service do smth");
    }

    public void optionalMethod() {
        method();
    }

    @Transactional
    private void method() {
        notificationService.sendNotification();
        try {
            rootRepository.deleteAll();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        log.info("Root Service sent a notification");
    }

}
