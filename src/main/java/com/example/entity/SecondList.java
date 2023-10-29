package com.example.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SecondList {

    @Id
    private String id;
    private String name;

    @OneToMany(targetEntity = End2.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<End2> mos;


    @OneToMany(targetEntity = End2.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<End2> moa;

    @OneToMany(targetEntity = End2.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<End2> mp;
}
