package com.example.controller;

import com.example.dto.EndDto;
import com.example.dto.RootDto;
import com.example.dto.SecondListDto;
import com.example.entity.Root;
import com.example.entity.SecondList;
import com.example.projection.RootProjection;
import com.example.repository.RootRepository;
import com.example.service.RootService;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.googlecode.jmapper.JMapper;
import com.googlecode.jmapper.api.JMapperAPI;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;

import static com.googlecode.jmapper.api.JMapperAPI.attribute;
import static com.googlecode.jmapper.api.JMapperAPI.mappedClass;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
@Transactional
public class RootControllerTest {

    @Autowired
    private RootService service;
    @Autowired
    private RootRepository repository;

    private static Root root;
    private static boolean isSaved = false;

    @BeforeAll
    public static void init() {
        SecondList secondList = new SecondList();
        secondList.setId("ob");


//        var moa = new ArrayList<End>();
//        int size = 10000;
//        for (int i = 0; i < size; i++) {
//            var end = new End();
//            end.setId("1" + i);
//            end.setName("1" + i);
//            end.setText("1" + i);
//            moa.add(end);
//        }
//        secondList.setMoa(moa);
//
//        var mos = new ArrayList<End>();
//        for (int i = 0; i < size; i++) {
//            var end = new End();
//            end.setId("2" + i);
//            end.setName("2" + i);
//            end.setText("2" + i);
//            mos.add(end);
//        }
//        secondList.setMos(mos);
//
//        var mp = new ArrayList<End>();
//        for (int i = 0; i < size; i++) {
//            var end = new End();
//            end.setId("3" + i);
//            end.setName("3" + i);
//            end.setText("3" + i);
//            mp.add(end);
//        }
//        secondList.setMp(mp);

        root = new Root();
        root.setId("1");
        root.setName("root");
        root.setOb(Collections.singletonList(secondList));
    }

    public void saveRoot() {
        if (!isSaved) {
            saveRoot(root);
            isSaved = true;
        }
    }

    private void saveRoot(Root root) {
        repository.saveAndFlush(root);
    }

    @Test
    public void getObjectMapper() {
        saveRoot();
        long startTime = System.currentTimeMillis();
        service.getObjectMapper();
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        log.warn("Время выполнения " + elapsedTime);
        assertTrue(elapsedTime > 250);
        assertTrue(elapsedTime < 320);
    }

    @Test
    public void getAllMapstructParts() {

    }

    @Test
    public void getProjections() {
        saveRoot();
        long startTime = System.currentTimeMillis();
//        RootProjection projections = service.getProjections();
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        log.warn("Время выполнения " + elapsedTime);
//        assertEquals("1", projections.getId());
//        assertEquals("root", projections.getName());
        assertTrue(elapsedTime > 5);
        assertTrue(elapsedTime < 12);
    }

    @Test
    public void getAllMapstruct() {
        saveRoot();
        long startTime = System.currentTimeMillis();
        service.get();
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        log.warn("Время выполнения " + elapsedTime);
        assertTrue(elapsedTime > 21);
        assertTrue(elapsedTime < 30);
    }

    @Test
    public void getDozer() {
        saveRoot();
        long startTime = System.currentTimeMillis();
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        Root entity = repository.getById("1");
        RootDto rootDto = new RootDto();
        mapper.map(entity, rootDto);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        log.warn("Время выполнения " + elapsedTime);
        assertTrue(elapsedTime > 900);
        assertTrue(elapsedTime < 1000);
    }

    @Test
    @Disabled
    public void getJMapper() {
        saveRoot();
        JMapperAPI jmapperApi = new JMapperAPI()
                .add(mappedClass(RootDto.class)
                        .add(attribute("id").value("id"))
                        .add(attribute("name").value("name"))
                        .add(attribute("ob").value("ob"))
                );

        jmapperApi.add(mappedClass(SecondListDto.class)
                .add(attribute("id").value("id"))
                .add(attribute("mos").value("mos"))
                .add(attribute("moa").value("moa"))
                .add(attribute("mp").value("mp")));

        jmapperApi.add(mappedClass(EndDto.class)
                .add(attribute("id").value("id"))
                .add(attribute("name").value("name"))
                .add(attribute("text").value("text")));

        JMapper<RootDto, Root> userMapper = new JMapper<>(RootDto.class, Root.class, jmapperApi);

        long startTime = System.currentTimeMillis();
        Root entity = repository.getById("1");
        RootDto result = userMapper.getDestination(entity);

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        assertEquals(entity.getId(), result.getId());
        assertEquals(entity.getName(), result.getName());
        assertTrue(elapsedTime > 10);
        assertTrue(elapsedTime < 35);
    }
}