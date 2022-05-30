package com.poetry.db.repository;

import com.poetry.db.entity.PoetrySysSearchHistory;
import com.poetry.db.entity.PoetrySysUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoetrySysSearchHistoryRepository extends JpaRepository<PoetrySysSearchHistory, String>, JpaSpecificationExecutor<PoetrySysSearchHistory> {
    List<PoetrySysSearchHistory> findBySysUsers(PoetrySysUsers users);
}