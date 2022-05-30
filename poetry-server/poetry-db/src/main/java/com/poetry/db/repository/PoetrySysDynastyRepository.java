package com.poetry.db.repository;

import com.poetry.db.entity.PoetrySysDynasty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PoetrySysDynastyRepository extends JpaRepository<PoetrySysDynasty, String>, JpaSpecificationExecutor<PoetrySysDynasty> {
    PoetrySysDynasty findByDynastyId(String id);
    void deleteByDynastyId(String id);

    PoetrySysDynasty findByDynastyName(String dynastyName);
}