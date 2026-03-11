package com.example.repository;

import com.example.model.Root;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//todo do we need the annotation?
public interface RootRepository extends JpaRepository<Root, String> {

}
