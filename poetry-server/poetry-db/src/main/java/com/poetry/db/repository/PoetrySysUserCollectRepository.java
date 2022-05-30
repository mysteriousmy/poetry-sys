package com.poetry.db.repository;

import com.poetry.db.entity.PoetrySysUserCollect;
import com.poetry.db.entity.PoetrySysUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoetrySysUserCollectRepository extends JpaRepository<PoetrySysUserCollect, String>, JpaSpecificationExecutor<PoetrySysUserCollect> {

    PoetrySysUserCollect findByCollectIdAndSysUsers(String id, PoetrySysUsers users);

    void deleteBySysUsersAndCollectId(PoetrySysUsers sysUsers, String collectId);

    List<PoetrySysUserCollect> findByCollectTypeAndSysUsers(int type, PoetrySysUsers users);
}