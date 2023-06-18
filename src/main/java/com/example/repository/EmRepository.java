package com.example.repository;


import jakarta.persistence.EntityManager;


public interface EmRepository {

    EntityManager getEntityManager();
}
