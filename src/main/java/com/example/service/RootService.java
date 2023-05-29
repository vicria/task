package com.example.service;

import com.example.dto.RootDto;
import com.example.entity.Root;
import com.example.mapper.RootMapper;
import com.example.projection.RootProjection;
import com.example.repository.RootRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RootService {
    private final RootRepository rootRepository;
    private final RootMapper mapper;
    private final ObjectMapper objectMapper;

    public RootDto get() {
        Root byId = rootRepository.getById("1");
        return mapper.toDto(byId);
    }

    public RootProjection getProjections() {
        Optional<Root> byId = rootRepository.findById("1");

        return byId.map(this::createProjection)
                .orElseThrow(() -> new EntityNotFoundException("Rood with id 1 doesn't exist"));
    }

    protected RootProjection createProjection(Root entity) {
        ProjectionFactory projectionFactory = new SpelAwareProxyProjectionFactory();
        return projectionFactory.createProjection(RootProjection.class, entity);
    }

    public RootDto getObjectMapper() {
        Root byId = rootRepository.getById("1");
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            String rootJson = objectMapper.writeValueAsString(byId);
            return objectMapper.readValue(rootJson, RootDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
