package com.example.projection;

import com.example.entity.Root;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(types = Root.class)
public interface RootProjection {

    String getId();
    String getName();
    List<SecondListProjection> getOb();
}
