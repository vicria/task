package com.example.projection;

import com.example.entity.Company;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(types = Company.class)
public interface SecondListProjection {

    String getId();
    List<EndProjection> getMos();
    List<EndProjection> getMoa();
    List<EndProjection> getMp();
}
