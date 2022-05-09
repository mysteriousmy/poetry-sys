package com.poetry.db.repository;

import com.poetry.common.consts.enums.PoemsType;
import com.poetry.db.entity.PoetrySysDynasty;
import com.poetry.db.entity.PoetrySysPoems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoetrySysPoemsRepository extends JpaRepository<PoetrySysPoems, String>, JpaSpecificationExecutor<PoetrySysPoems> {
    PoetrySysPoems findByPoemId(String id);

    void deleteByPoemId(String id);

    List<PoetrySysPoems> findByPoemsTypeAndDynasty(PoemsType type, PoetrySysDynasty dynasty);

    List<PoetrySysPoems> findByPoemsType(PoemsType type);

    List<PoetrySysPoems> findByDynasty(PoetrySysDynasty dynasty);

    @Query(value = "select * from poetry_sys_poems where poem_title like %?%", nativeQuery = true)
    List<Object[]> findByPoemTitleLike(String s);
}