package com.example;

import com.example.service.RootService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TestEnvConfig.class)
class RootServiceTest {

    @Autowired
    private RootService service;

    @Test
    void sendHi() {
//        service.sendHi();
    }
}