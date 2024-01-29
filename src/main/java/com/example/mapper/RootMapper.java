package com.example.mapper;

import com.example.dto.RootDto;
import com.example.entity.Root;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.springframework.stereotype.Component;

@Component("mapper")
@Mapper
@MapperConfig(injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        disableSubMappingMethodsGeneration = true,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        componentModel = "spring")
public interface RootMapper {

    RootDto toDto(Root root);

}