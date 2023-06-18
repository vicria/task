package com.example.repository;

import com.example.entity.ActionSpecification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Репозиторий.
 */
@Component
public class ActionSpecificationDynamicRepository implements EmRepository {

    @PersistenceContext
    private EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }


    public List<String> selectIdsBySpecification(@Nullable Specification<ActionSpecification> spec) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<String> query = builder.createQuery(String.class);
        Root<ActionSpecification> root = query.from(ActionSpecification.class);
        query.select(root.get("id"));
        //todo лучше использовать статик метомодели BaseEntity_ генерируются в таргете
        // query.select(root.get(BaseEntity_.id));
        if (spec != null) {
            query.where(spec.toPredicate(root, query, builder));
        }
        return getEntityManager().createQuery(query).getResultList();
    }

}
