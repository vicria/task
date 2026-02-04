package com.example.service;

import com.example.repository.RootRepository;
import com.example.service.more.CustomAnnotation;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.stream.IntStream;

@Slf4j
@Component
@CustomAnnotation
public class RootService {

    private final NotificationService notificationService;
    private final RootRepository rootRepository;
    private final HashMap<String, CounterService> counters;

    public RootService(
            NotificationService notificationService,
            RootRepository rootRepository,
            CounterService customCounter,
            CounterService commonCounter
    ) {
        this.notificationService = notificationService;
        this.rootRepository = rootRepository;
        log.info("RootService конструктор");

        counters = new HashMap<>();
        counters.put("customCounterService", customCounter);
        counters.put("counterService", commonCounter);
    }

    @PostConstruct
    public void init() {
        log.info("PostConstruct");
    }

    public void generalMethod() {
        IntStream.rangeClosed(0, 30)
                .forEach(i -> counters.values().stream()
                        .peek(CounterService::inc)
                        .map(CounterService::result)
                        .count());

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
