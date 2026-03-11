package com.example.service;

import com.example.event.CustomEventPublisher;
import com.example.repository.RootRepository;
import com.example.service.more.CustomAnnotation;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Component
@CustomAnnotation
public class RootService {

    private final CustomEventPublisher customEventPublisher;
    private final RootRepository rootRepository;
    private final List<CounterService> counters;

    public RootService(
            CustomEventPublisher customEventPublisher,
            RootRepository rootRepository,
            List<CounterService> counters
    ) {
        this.customEventPublisher = customEventPublisher;
        this.rootRepository = rootRepository;
        log.info("RootService конструктор");

        this.counters = counters;
    }

    @PostConstruct
    public void init() {
        log.info("PostConstruct");
    }

    public void generalMethod() {
        IntStream.rangeClosed(0, 30)
                .forEach(i -> counters.stream()
                        .peek(CounterService::inc)
                        .map(CounterService::result)
                        .count());

        log.info("Root Service do smth");
    }

    @Transactional
    public void optionalMethod() {
        customEventPublisher.publishCustomEvent("Transaction");
        rootRepository.deleteAll();
        log.info("Root Service sent a notification");
    }
}
