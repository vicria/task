package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "root_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Root {

    @Id
    private String id;
    private String name;
}
