package com.example.controller;

import com.example.entity.End;
import com.example.entity.Root;
import com.example.entity.SecondList;
import com.example.repository.RootRepository;
import com.example.service.RootService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
@Transactional
public class RootControllerTest {

    @Autowired
    private RootService service;
    @Autowired
    private RootRepository repository;


    @Test
    public void getAll() {
        SecondList secondList = new SecondList();
        secondList.setId("ob");

        int size = 100000;
        var moa = new ArrayList<End>();
        for (int i = 0; i < size; i++) {
            var end = new End();
            end.setId("1" + i);
            end.setName("1" + i);
            end.setText("1" + i);
            moa.add(end);
        }
        secondList.setMoa(moa);

        var mos = new ArrayList<End>();
        for (int i = 0; i < size; i++) {
            var end = new End();
            end.setId("2" + i);
            end.setName("2" + i);
            end.setText("2" + i);
            moa.add(end);
        }
        secondList.setMos(mos);

        var mp = new ArrayList<End>();
        for (int i = 0; i < size; i++) {
            var end = new End();
            end.setId("3" + i);
            end.setName("3" + i);
            end.setText("3" + i);
            moa.add(end);
        }
        secondList.setMp(mp);

        Root root = new Root();
        root.setId("1");
        root.setName("root");
        root.setOb(Collections.singletonList(secondList));
        repository.saveAndFlush(root);


        long startTime = System.currentTimeMillis();
        service.get();
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        log.warn("Время выполнения " + elapsedTime);
        assertEquals(21, elapsedTime);
    }
}