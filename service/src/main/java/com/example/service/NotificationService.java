package com.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationService {

    public NotificationService(@Lazy RootService rootService) {
        rootService.generalMethod();
    }

    public void sendNotification() {
        log.info("The notification is delivered");
    }

}
