package com.poetry.db.repository;

import com.poetry.db.entity.PoetrySysPoemLists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PoetrySysPoemListsRepository extends JpaRepository<PoetrySysPoemLists, String>, JpaSpecificationExecutor<PoetrySysPoemLists> {

    PoetrySysPoemLists findByPoemListId(String id);

    void deleteByPoemListId(String id);
    @Query(value = "select * from poetry_sys_poems " +
            "where poem_id in " +
            "(select poetry_sys_poems_poem_id " +
            "from poetry_sys_poem_lists_poetry_sys_poems " +
            "where poetry_sys_poem_lists_poem_list_id = ?)",nativeQuery = true)
    List<Object[]> findPoemsOfPoemList(String id);
    @Query(value = "select * from poetry_sys_poem_lists where poem_list_title like %?%", nativeQuery = true)
    List<Object[]> findByPoemListTitleLike(String word);
}