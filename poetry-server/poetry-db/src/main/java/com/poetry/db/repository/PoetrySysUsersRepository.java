package com.poetry.db.repository;

import com.poetry.common.consts.enums.AdminsType;
import com.poetry.db.entity.PoetrySysUserCollect;
import com.poetry.db.entity.PoetrySysUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoetrySysUsersRepository extends JpaRepository<PoetrySysUsers, String>, JpaSpecificationExecutor<PoetrySysUsers> {
    PoetrySysUsers findByUserId(String id);

    boolean deleteByUserId(String id);

    PoetrySysUsers findByUserNameAndOpenId(String userName, String openId);

    PoetrySysUsers findByOpenId(String openid);


}