package com.example.dto;

import lombok.Data;

import java.util.List;

@Data
public class SecondListDto {
    private String id;
    private List<EndDto> mos;
    private List<EndDto> moa;
    private List<EndDto> mp;
}
