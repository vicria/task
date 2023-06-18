package com.example.repository;

import com.example.entity.ActionSpecification;
import com.example.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * Репозиторий.
 */
public interface ActionSpecificationRepository extends JpaRepository<ActionSpecification, String> {

    @Query(value = "select asp.id, c from ActionSpecification asp join asp.companies c")
    List<List<Object>> getAllCompanies();



    @Query(value = "select asp.id as id, c from ActionSpecification asp join asp.companies c")
    Map<String, List<Company>> getAllCompanies2();


    //todo replace to company
    @Query(value = "select c from ActionSpecification asp join asp.companies c where asp.id in ?",
            nativeQuery = true)
    List<Company> getAllCompanies3(List<String> ids);
}
