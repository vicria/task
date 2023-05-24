package com.example.mapper;

import com.example.dto.EndDto;
import com.example.entity.End;
import org.mapstruct.Mapper;

/**
 * Mapper between {@link End} and {@link EndDto} classes.
 */
@Mapper(config = MappingConfig.class)
public interface EndMapper extends BaseMapper<EndDto, End> {

}