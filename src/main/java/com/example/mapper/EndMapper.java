package com.example.mapper;

import com.example.dto.EndDto;
import com.example.entity.End2;
import org.mapstruct.Mapper;

/**
 * Mapper between {@link End2} and {@link EndDto} classes.
 */
@Mapper(config = MappingConfig.class)
public interface EndMapper extends BaseMapper<EndDto, End2> {

}