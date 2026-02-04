package com.example.service;

import java.util.concurrent.atomic.AtomicInteger;

public class CommonCounterService implements CounterService {

    private AtomicInteger value;

    @Override
    public void inc() {
        value.incrementAndGet();
    }

    @Override
    public int result() {
        return value.get();
    }
}
