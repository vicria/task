package com.example.mapper;

import com.example.dto.RootDto;
import com.example.entity.Root;
import org.mapstruct.Mapper;

/**
 * Mapper between {@link Root} and {@link RootDto} classes.
 */
@Mapper(config = MappingConfig.class,
        uses = SecondListMapper.class)
public interface RootMapper extends BaseMapper<RootDto, Root> {

}