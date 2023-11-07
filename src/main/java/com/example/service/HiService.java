package com.example.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HiService {

    @PostConstruct
    private void init(){
        log.info("Run Hi Service");
    }


    public void sendHi() {
        log.info("Say hi");
    }
}
