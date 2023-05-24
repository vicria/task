package com.example.mapper;

import com.example.dto.SecondListDto;
import com.example.entity.SecondList;
import org.mapstruct.Mapper;

/**
 * Mapper between {@link SecondList} and {@link SecondListDto} classes.
 */
@Mapper(config = MappingConfig.class,
        uses = EndMapper.class)
public interface SecondListMapper extends BaseMapper<SecondListDto, SecondList> {

}