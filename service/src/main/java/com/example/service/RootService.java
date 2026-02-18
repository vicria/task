package com.example.service;

import com.example.dto.RootServiceEvent;
import com.example.repository.RootRepository;
import com.example.service.more.CustomAnnotation;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Component
@CustomAnnotation
public class RootService {

    private final RootRepository rootRepository;
    private final List<CounterService> counters;
    private final ApplicationEventPublisher applicationEventPublisher;

    public RootService(
            RootRepository rootRepository,
            List<CounterService> counters,
            ApplicationEventPublisher applicationEventPublisher
    ) {
        this.rootRepository = rootRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.counters = counters;

        log.info("RootService конструктор");
        log.info("Number of CounterService: " + counters.size());
        counters.forEach(counterService -> log.info(counterService.getClass().getName()));
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
        rootRepository.deleteAll();
        applicationEventPublisher.publishEvent(new RootServiceEvent("root event"));
        log.info("Root Service sent a notification");
    }

}
