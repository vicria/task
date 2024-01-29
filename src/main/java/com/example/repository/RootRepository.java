package com.example.repository;

import com.example.entity.Root;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий.
 */
public interface RootRepository extends JpaRepository<Root, String> {

}
