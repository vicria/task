package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Root {

    @Id
    String id;

    String name;
}
