package com.example.controller;

import com.example.entity.Root;
import com.example.service.RootService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller.
 */
@Slf4j
@RestController
public class RootController {

    private final RootService service;
    private final ApplicationContext context;

    public RootController(RootService service, ApplicationContext context) {
        this.service = service;
        this.context = context;
        log.info("Root Controller constructor");
    }

    /**
     * Get All.
     *
     * @return All
     */
    @GetMapping("/all")
    public Root getAll() {
        return service.get();
    }

}
