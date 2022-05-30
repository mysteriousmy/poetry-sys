package com.poetry.db.repository;

import com.poetry.db.entity.PoetrySysUsers;
import com.poetry.db.entity.PoetryUserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoetryUserHistoryRepository extends JpaRepository<PoetryUserHistory, String>, JpaSpecificationExecutor<PoetryUserHistory> {

    List<PoetryUserHistory> findByHisUsers(PoetrySysUsers users);
}
