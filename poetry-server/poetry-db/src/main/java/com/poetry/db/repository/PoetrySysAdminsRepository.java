package com.poetry.db.repository;

import com.poetry.common.consts.enums.AdminsType;
import com.poetry.db.entity.PoetrySysAdmins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoetrySysAdminsRepository extends JpaRepository<PoetrySysAdmins, String>, JpaSpecificationExecutor<PoetrySysAdmins> {
    public PoetrySysAdmins findByAdminsNameAndAdminsPassword(String adminName, String adminPassword);
    PoetrySysAdmins findByAdminsEmail(String email);
    List<PoetrySysAdmins> findByAdminsType(AdminsType adminsType);

    PoetrySysAdmins findByAdminsId(String id);

    PoetrySysAdmins findByAdminsNameOrAdminsEmail(String adminsName,String adminsEmail);

    int deleteByAdminsIdAndAdminsTypeNot(String id, AdminsType adminsType);
}