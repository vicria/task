package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class CustomCounterService implements CounterService {

    private String text;

    @Override
    public void inc() {
        text += Math.random() * 10;
    }

    @Override
    public int result() {
        return Integer.parseInt(text);
    }
}
