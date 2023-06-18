package com.example.mapper;

import com.example.dto.ActionSpecificationDto;
import com.example.entity.ActionSpecification;
import org.mapstruct.Mapper;

/**
 * Mapper between {@link ActionSpecification} and {@link ActionSpecificationDto} classes.
 */
@Mapper(config = MappingConfig.class,
        uses = SecondListMapper.class)
public interface RootMapper extends BaseMapper<ActionSpecificationDto, ActionSpecification> {

}