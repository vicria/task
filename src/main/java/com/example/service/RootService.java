package com.example.service;

import com.example.dto.RootDto;
import com.example.entity.Root;
import com.example.mapper.RootMapper;
import com.example.repository.RootRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RootService {
    private final RootRepository rootRepository;
    private final RootMapper mapper;

    public RootDto get(){
        Root byId = rootRepository.getById("1");
        return mapper.toDto(byId);
    }
}
