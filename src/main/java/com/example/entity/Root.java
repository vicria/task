package com.example.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class Root {

    @Id
    private String id;
    private String name;

    @OneToMany(targetEntity = SecondList.class,
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY)
    private List<SecondList> ob;
//
    @OneToOne(targetEntity = SecondList.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private SecondList kkk;
}
