package com.example.service;

import com.example.dto.ActionSpecificationDto;
import com.example.entity.ActionSpecification;
import com.example.entity.Company;
import com.example.mapper.RootMapper;
import com.example.projection.RootProjection;
import com.example.repository.ActionSpecificationDynamicRepository;
import com.example.repository.ActionSpecificationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RootService {
    private final ActionSpecificationRepository actionSpecificationRepository;
    private final ActionSpecificationDynamicRepository actionSpecificationDynamicRepository;
    private final RootMapper mapper;
    private final ObjectMapper objectMapper;

    //твой вариант
    public List<ActionSpecificationDto> getByCriteria() {
        List<ActionSpecification> all = actionSpecificationRepository.findAll();

        List<List<Object>> allCompanies = actionSpecificationRepository.getAllCompanies();
        Map<String, List<Company>> convert = convert(allCompanies);

        return all.stream()
                .peek(asp -> {
                    List<Company> companies = convert.containsKey(asp.getId()) ? convert.get(asp.getId()) : new ArrayList<>();
                    asp.setCompanies(companies);
                })
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    //через id
    public List<ActionSpecificationDto> getByCriteria2(Specification<ActionSpecification> spec) {
        List<String> all = actionSpecificationDynamicRepository.selectIdsBySpecification(spec);

        List<List<Object>> allCompanies = actionSpecificationRepository.getAllCompanies();
        Map<String, List<Company>> convert = convert(allCompanies);

        return all.stream()
                .map(id -> {
                    var asp = new ActionSpecification();
                    asp.setId(id);
                    List<Company> companies = convert.containsKey(id) ? convert.get(id) : new ArrayList<>();
                    asp.setCompanies(companies);
                    return asp;
                }).map(mapper::toDto)
                .collect(Collectors.toList());
    }

    private Map<String, List<Company>> convert(List<List<Object>> data) {
        Map<String, List<Company>> out = new HashMap<>();

        for (List<Object> one : data) {
            String id = (String) one.get(0);
            Company obj = (Company) one.get(1);
            if (out.containsKey(id)) {
                out.get(id).add(obj);
            } else {
                out.put(id, new ArrayList<>(List.of(obj)));
            }
        }
        return out;
    }

    public ActionSpecificationDto get() {
        ActionSpecification byId = actionSpecificationRepository.getById("1");
        return mapper.toDto(byId);
    }

    public RootProjection getProjections() {
        Optional<ActionSpecification> byId = actionSpecificationRepository.findById("1");

        return byId.map(this::createProjection)
                .orElseThrow(() -> new EntityNotFoundException("Rood with id 1 doesn't exist"));
    }

    protected RootProjection createProjection(ActionSpecification entity) {
        ProjectionFactory projectionFactory = new SpelAwareProxyProjectionFactory();
        return projectionFactory.createProjection(RootProjection.class, entity);
    }

    public ActionSpecificationDto getObjectMapper() {
        ActionSpecification byId = actionSpecificationRepository.getById("1");
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            String rootJson = objectMapper.writeValueAsString(byId);
            return objectMapper.readValue(rootJson, ActionSpecificationDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
