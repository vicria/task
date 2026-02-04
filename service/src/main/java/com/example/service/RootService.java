package com.example.service;

import com.example.repository.RootRepository;
import com.example.service.more.CustomAnnotation;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@CustomAnnotation
public class RootService {

    private final RootRepository rootRepository;
    private final List<CounterService> counters;
    private final ApplicationEventPublisher publisher;

    public RootService(
            RootRepository rootRepository,
            List<CounterService> counters, ApplicationEventPublisher publisher
    ) {
        this.rootRepository = rootRepository;
        this.publisher = publisher;
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
        try {
            rootRepository.deleteAll();
            publisher.publishEvent(new Object());
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        log.info("Root Service sent a notification");
    }

}
