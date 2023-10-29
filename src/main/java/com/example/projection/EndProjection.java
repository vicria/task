package com.example.projection;

import com.example.entity.End2;
import com.example.entity.Root;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = End2.class)
public interface EndProjection {

    String getId();
    String getName();
    String getText();
}
