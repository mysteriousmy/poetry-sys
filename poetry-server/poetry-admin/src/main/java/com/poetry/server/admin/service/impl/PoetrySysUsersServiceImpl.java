package com.poetry.server.admin.service.impl;

import com.poetry.common.consts.enums.AdminsType;
import com.poetry.common.consts.enums.ErrorCode;
import com.poetry.common.exception.CustomException;
import com.poetry.common.response.SuccessResponse;
import com.poetry.db.entity.PoetrySysAdmins;
import com.poetry.db.entity.PoetrySysUsers;
import com.poetry.db.repository.PoetrySysUsersRepository;
import com.poetry.server.admin.dto.PoetrySysUsersDto;
import com.poetry.server.admin.service.PoetrySysUsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Slf4j
@Transactional
public class PoetrySysUsersServiceImpl implements PoetrySysUsersService {
    @Autowired
    private PoetrySysUsersRepository repository;

    @Override
    public SuccessResponse getAllUsers() {
        List<PoetrySysUsers> users = repository.findAll();
        return new SuccessResponse(users);
    }

    @Override
    public SuccessResponse getUsersById(String id) {
        PoetrySysUsers user = repository.findByUserId(id);
        return new SuccessResponse(user);
    }
    //没有用户添加和编辑
    @Override
    public SuccessResponse saveUsers(PoetrySysUsersDto usersDto) {
        return null;
    }

    @Override
    public SuccessResponse updateUsers(PoetrySysUsersDto usersDto) {
        return null;
    }

    @Override
    public SuccessResponse deleteUsersById(String id) {
        if(id == null || id.length() == 0){
            throw new CustomException(ErrorCode.INVALID_PARAM.getCode(), ErrorCode.INVALID_PARAM.getSemantics());
        }
        boolean result = repository.deleteByUserId(id);
        return new SuccessResponse(result);
    }
}
