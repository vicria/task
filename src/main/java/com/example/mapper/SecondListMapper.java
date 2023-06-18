package com.example.mapper;

import com.example.entity.Company;
import org.mapstruct.Mapper;

/**
 * Mapper between {@link Company} and {@link com.example.dto.Company} classes.
 */
@Mapper(config = MappingConfig.class,
        uses = EndMapper.class)
public interface SecondListMapper extends BaseMapper<com.example.dto.Company, Company> {

}