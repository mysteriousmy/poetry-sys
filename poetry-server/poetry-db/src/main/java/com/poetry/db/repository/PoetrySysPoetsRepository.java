package com.poetry.db.repository;

import com.poetry.db.entity.PoetrySysDynasty;
import com.poetry.db.entity.PoetrySysPoets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoetrySysPoetsRepository extends JpaRepository<PoetrySysPoets, String>, JpaSpecificationExecutor<PoetrySysPoets> {
    PoetrySysPoets findByPoetsId(String id);

    void deleteByPoetsId(String id);

    List<PoetrySysPoets> findByDynasty(PoetrySysDynasty dynasty);
    @Query(value = "select * from poetry_sys_poets where poets_name like %?%", nativeQuery = true)
    List<Object[]> findByPoetsNameLike(String word);
}