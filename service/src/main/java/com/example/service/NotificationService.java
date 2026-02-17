package com.example.service;

import com.example.dto.RootServiceEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationService {

    public NotificationService(@Lazy RootService rootService) {
        rootService.generalMethod();
    }

    @EventListener
    public void sendNotification(RootServiceEvent event) {
        log.info("The notification {} is delivered", event.name());
    }

}
