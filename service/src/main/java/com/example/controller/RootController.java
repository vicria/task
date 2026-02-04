package com.example.controller;

import com.example.service.RootService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller.
 */
@Slf4j
@RestController
public class RootController {

    private final RootService service;

    public RootController(RootService service) {
        this.service = service;
        log.info("Root Controller constructor");
    }

    @GetMapping("/general")
    public void general() {
        service.generalMethod();
    }

    @GetMapping("/optional")
    public void optional() {
        service.optionalMethod();
    }

}
