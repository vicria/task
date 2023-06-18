package com.example.dto;

import lombok.Data;

import java.util.List;

@Data
public class ActionSpecificationDto {
    private String id;
    private String name;
    private List<Company> companies;
}
