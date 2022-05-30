package com.poetry.server.miniprogram.Service.impl;

import com.poetry.common.response.SuccessResponse;
import com.poetry.common.util.EntityUtil;
import com.poetry.db.entity.*;
import com.poetry.db.repository.*;
import com.poetry.server.miniprogram.Service.DetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class DetailServiceImpl implements DetailService {
    @Autowired
    private PoetrySysPoemListsRepository repository;
    @Autowired
    private PoetrySysPoetsRepository poetsRepository;
    @Autowired
    private PoetrySysRhesisRepository rhesisRepository;
    @Autowired
    private PoetrySysPoemsRepository poemsRepository;
    @Autowired
    private PoetryUserHistoryRepository userHistoryRepository;
    @Autowired
    private PoetrySysUsersRepository usersRepository;
    @Override
    @Transactional
    public SuccessResponse getDetail(String id) {
        PoetrySysPoemLists poemLists = repository.findByPoemListId(id);
        return new SuccessResponse(poemLists);
    }

    @Override
    public SuccessResponse getPoetsDetail(String id, String openid) {
        PoetrySysPoets poets = poetsRepository.findByPoetsId(id);
        saveHistory(id, 0, openid);
        return new SuccessResponse(poets);
    }

    @Override
    public SuccessResponse getRhesisDetail(String id, String openid) {
        PoetrySysRhesis rhesis = rhesisRepository.findByRhesisId(id);
        saveHistory(id, 2, openid);
        return new SuccessResponse(rhesis);
    }

    @Override
    public SuccessResponse getPoemDetail(String id, String openid) {
        PoetrySysPoems poems = poemsRepository.findByPoemId(id);
        saveHistory(id, 1, openid);
        return new SuccessResponse(poems);
    }
    private void saveHistory(String id, int type, String openid){
        PoetryUserHistory ps = new PoetryUserHistory();
        PoetrySysUsers users = usersRepository.findByOpenId(openid);
        ps.hisUsers = users;
        ps.historyId = id;
        ps.historyType = type;
        userHistoryRepository.save(ps);
    }
}
