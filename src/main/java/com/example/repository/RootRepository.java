package com.example.repository;

import com.example.entity.Root;
import jakarta.persistence.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Репозиторий.
 */
public interface RootRepository extends JpaRepository<Root, String> {

    Optional<Root> findByName(String name);

}
