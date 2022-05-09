package com.poetry.server.miniprogram.Service.impl;

import com.poetry.common.consts.enums.ErrorCode;
import com.poetry.common.exception.CustomException;
import com.poetry.common.response.SuccessResponse;
import com.poetry.db.entity.*;
import com.poetry.db.repository.*;
import com.poetry.server.miniprogram.Service.UserService;
import com.poetry.server.miniprogram.dto.CollectDto;
import com.poetry.server.miniprogram.dto.HistoryDto;
import com.poetry.server.miniprogram.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private PoetrySysUsersRepository repository;
    @Autowired
    private PoetrySysUserCollectRepository collectRepository;
    @Autowired
    private PoetrySysPoetsRepository poetsRepository;
    @Autowired
    private PoetrySysRhesisRepository rhesisRepository;
    @Autowired
    private PoetrySysPoemsRepository poemsRepository;
    @Autowired
    private PoetryUserHistoryRepository userHistoryRepository;
    @Override
    public SuccessResponse doLogin(UserDto userDto) {
        PoetrySysUsers users = repository.findByUserNameAndOpenId(userDto.getUserName(), userDto.getOpenId());
        if(users == null){
            throw new CustomException(ErrorCode.INVALID_PARAM.getCode(), "未找到该用户");
        }
        System.out.println(users.searchHistoryList);
        return new SuccessResponse(users);
    }

    @Override
    public SuccessResponse doCheck(UserDto userDto) {
        PoetrySysUsers users = repository.findByUserNameAndOpenId(userDto.getUserName(), userDto.getOpenId());
        if(users == null){
            PoetrySysUsers tempUser = new PoetrySysUsers();
            tempUser.openId = userDto.getOpenId();
            tempUser.userName = userDto.getUserName();
            tempUser.userPhoto = userDto.getUserPhoto();
            tempUser.userGender = userDto.getUserGender();
            repository.save(tempUser);
            return new SuccessResponse(false);
        }
        return new SuccessResponse(true);
    }

    @Override
    public SuccessResponse checkCollect(String id, String openid) {
        PoetrySysUsers users = repository.findByOpenId(openid);
        if(users == null){
            throw new CustomException(ErrorCode.INVALID_PARAM.getCode(), "用户不存在");
        }
        PoetrySysUserCollect collect = collectRepository.findByCollectIdAndSysUsers(id, users);
        if(collect == null){
            return new SuccessResponse(false);
        }
        return new SuccessResponse(true);
    }

    @Override
    public SuccessResponse collect(CollectDto dto) {
        PoetrySysUsers users = repository.findByOpenId(dto.getOpenid());
        Boolean b = false;
        if(users == null){
            throw new CustomException(ErrorCode.INVALID_PARAM.getCode(), "用户不存在");
        }
        PoetrySysUserCollect collect = new PoetrySysUserCollect();
        collect.collectId = dto.getId();
        collect.sysUsers = users;
        collect.collectType = dto.getType();
        if(dto.getOption() == 1){
            collectRepository.save(collect);
            b = true;
        }else if (dto.getOption() == 0){
            collectRepository.deleteBySysUsersAndCollectId(collect.sysUsers, collect.collectId);
            b = false;
        }
        return new SuccessResponse(b);
    }

    @Override
    public SuccessResponse getCollectByType(int type, String openid) {
        PoetrySysUsers users = repository.findByOpenId(openid);
        if(users == null){
            throw new CustomException(ErrorCode.INVALID_PARAM.getCode(),"用户不存在");
        }
        List<PoetrySysUserCollect> collectList = collectRepository.findByCollectTypeAndSysUsers(type, users);
        if(type == 0){
            List<PoetrySysPoems> poems = new ArrayList<>();
            collectList.forEach(c -> {
                poems.add(poemsRepository.findByPoemId(c.collectId));
            });
            return new SuccessResponse(poems);
        }else if(type == 1){
            List<PoetrySysRhesis> rheses = new ArrayList<>();
            collectList.forEach(c -> {
                rheses.add(rhesisRepository.findByRhesisId(c.collectId));
            });
            return new SuccessResponse(rheses);
        }
        return new SuccessResponse("");
    }

    @Override
    public SuccessResponse getHistoryByUser(String openid) {
        PoetrySysUsers users = repository.findByOpenId(openid);
        if(users == null){
            throw new CustomException(ErrorCode.INVALID_PARAM.getCode(),"用户不存在");
        }
        List<PoetryUserHistory> histories = userHistoryRepository.findByHisUsers(users);
        HistoryDto historyDto = new HistoryDto();
        List<PoetrySysPoems> poems = new ArrayList<>();
        List<PoetrySysPoets> poets = new ArrayList<>();
        List<PoetrySysRhesis> rheses = new ArrayList<>();
        histories.forEach(h -> {
            switch (h.historyType){
                case 0:
                    PoetrySysPoets tmp = poetsRepository.findByPoetsId(h.historyId);
                    poets.add(tmp);
                    break;
                case 1:
                    PoetrySysPoems tmp2 = poemsRepository.findByPoemId(h.historyId);
                    poems.add(tmp2);
                    break;
                case 2:
                    PoetrySysRhesis tmp3 = rhesisRepository.findByRhesisId(h.historyId);
                    rheses.add(tmp3);
                    break;
            }
        });
        historyDto.setPoems(poems);
        historyDto.setPoets(poets);
        historyDto.setRheses(rheses);
        return new SuccessResponse(historyDto);
    }
}
