package com.example.projection;

import com.example.entity.ActionSpecification;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(types = ActionSpecification.class)
public interface RootProjection {

    String getId();
    String getName();
    List<SecondListProjection> getOb();
}
