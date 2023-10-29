package com.example.controller;

import com.example.dto.RootDto;
import com.example.service.RootService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller.
 */
@RestController
@RequiredArgsConstructor
public class RootController {

    private final RootService service;

    /**
     * Get All.
     *
     * @return All
     */
    @GetMapping("/all")
    public RootDto getAll() {
        return service.get();
    }

    @PostMapping("/save")
    public RootDto getAll(@RequestBody RootDto dto) {
        return service.save(dto);
    }

    @GetMapping("/hi")
    public void hi() {
        service.sendHi();
    }

}
