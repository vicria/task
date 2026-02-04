package com.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
public class NotificationService {

    public NotificationService(@Lazy RootService rootService) {
        rootService.generalMethod();
    }

    @EventListener
    public void sendNotification(Object object) {
        log.info("The notification is delivered");
    }

}
