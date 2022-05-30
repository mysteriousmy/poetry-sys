package com.poetry.db.repository;

import com.poetry.db.entity.PoetrySysPoemLists;
import com.poetry.db.entity.PoetrySysRhesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoetrySysRhesisRepository extends JpaRepository<PoetrySysRhesis, String>, JpaSpecificationExecutor<PoetrySysRhesis> {
    PoetrySysRhesis findByRhesisId(String id);

    void deleteByRhesisId(String id);
    @Query(value = "select * from poetry_sys_poem_lists order by rand() limit 10", nativeQuery = true)
    List<PoetrySysPoemLists> randomSelect();
    @Query(value = "select * from poetry_sys_rhesis where rhesis_content like %?%", nativeQuery = true)
    List<Object[]> findByRhesisContentLike(String s);

}