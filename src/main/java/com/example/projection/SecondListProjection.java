package com.example.projection;

import com.example.entity.SecondList;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(types = SecondList.class)
public interface SecondListProjection {

    String getId();
    List<EndProjection> getMos();
    List<EndProjection> getMoa();
    List<EndProjection> getMp();
}
