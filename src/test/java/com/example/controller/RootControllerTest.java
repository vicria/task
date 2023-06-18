package com.example.controller;

import com.example.dto.ActionSpecificationDto;
import com.example.dto.EndDto;
import com.example.entity.ActionSpecification;
import com.example.entity.End;
import com.example.entity.Company;
import com.example.projection.RootProjection;
import com.example.repository.ActionSpecificationRepository;
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
import java.util.List;
import java.util.Map;

import static com.googlecode.jmapper.api.JMapperAPI.attribute;
import static com.googlecode.jmapper.api.JMapperAPI.mappedClass;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
@Transactional
public class RootControllerTest {

    @Autowired
    private RootService service;
    @Autowired
    private ActionSpecificationRepository repository;

    private static ActionSpecification root;
    private static boolean isSaved = false;

    @BeforeAll
    public static void init() {
        Company secondList = new Company();
        secondList.setId("ob");


        var moa = new ArrayList<End>();
        int size = 10000;
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
            mos.add(end);
        }
        secondList.setMos(mos);

        var mp = new ArrayList<End>();
        for (int i = 0; i < size; i++) {
            var end = new End();
            end.setId("3" + i);
            end.setName("3" + i);
            end.setText("3" + i);
            mp.add(end);
        }
        secondList.setMp(mp);

        root = new ActionSpecification();
        root.setId("1");
        root.setName("root");
        root.setCompanies(Collections.singletonList(secondList));
    }

    public void saveRoot() {
        if (!isSaved) {
            saveRoot(root);
            isSaved = true;
        }
    }

    private void saveRoot(ActionSpecification root) {
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
        var companies = new ArrayList<Company>();
        for (int i = 0; i < 2500; i++) {
            var end = new Company();
            end.setId("3" + i);
            companies.add(end);
        }
        root = new ActionSpecification();
        root.setId("1");
        root.setName("root");
        root.setCompanies(companies);
        repository.saveAndFlush(root);


        List<List<Object>> allCompanies = repository.getAllCompanies();
        Map<String, List<Company>> allCompanies2 = repository.getAllCompanies2();
        assertFalse(allCompanies2.isEmpty());
    }

    @Test
    public void getProjections() {
        saveRoot();
        long startTime = System.currentTimeMillis();
        RootProjection projections = service.getProjections();
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        log.warn("Время выполнения " + elapsedTime);
        assertEquals("1", projections.getId());
        assertEquals("root", projections.getName());
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
        ActionSpecification entity = repository.getById("1");
        ActionSpecificationDto rootDto = new ActionSpecificationDto();
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
                .add(mappedClass(ActionSpecificationDto.class)
                        .add(attribute("id").value("id"))
                        .add(attribute("name").value("name"))
                        .add(attribute("ob").value("ob"))
                );

        jmapperApi.add(mappedClass(com.example.dto.Company.class)
                .add(attribute("id").value("id"))
                .add(attribute("mos").value("mos"))
                .add(attribute("moa").value("moa"))
                .add(attribute("mp").value("mp")));

        jmapperApi.add(mappedClass(EndDto.class)
                .add(attribute("id").value("id"))
                .add(attribute("name").value("name"))
                .add(attribute("text").value("text")));

        JMapper<ActionSpecificationDto, ActionSpecification> userMapper = new JMapper<>(ActionSpecificationDto.class, ActionSpecification.class, jmapperApi);

        long startTime = System.currentTimeMillis();
        ActionSpecification entity = repository.getById("1");
        ActionSpecificationDto result = userMapper.getDestination(entity);

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        assertEquals(entity.getId(), result.getId());
        assertEquals(entity.getName(), result.getName());
        assertTrue(elapsedTime > 10);
        assertTrue(elapsedTime < 35);
    }
}