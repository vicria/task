package com.example.service;

import com.example.event.CustomEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationService {

    public NotificationService(RootService rootService) {
        rootService.generalMethod();
    }

    @EventListener(CustomEvent.class)
    public void sendNotification(CustomEvent customEvent) {
        log.info("Custom Event received: {}", customEvent);
        log.info("The notification is delivered");
    }

}
