package com.poetry.server.miniprogram.Service.impl;

import com.poetry.common.response.SuccessResponse;
import com.poetry.db.entity.*;
import com.poetry.db.repository.*;
import com.poetry.server.miniprogram.Service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SearchServiceImpl implements SearchService {
    @Autowired
    private PoetrySysSearchHistoryRepository searchHistoryRepository;
    @Autowired
    private PoetrySysUsersRepository usersRepository;
    @Autowired
    private PoetrySysPoetsRepository poetsRepository;
    @Autowired
    private PoetrySysPoemsRepository poemsRepository;
    @Autowired
    private PoetrySysPoemListsRepository poemListsRepository;
    @Autowired
    private PoetrySysRhesisRepository poetrySysRhesisRepository;

    @Override
    public SuccessResponse getSearchHistory(String openid) {
        PoetrySysUsers users = usersRepository.findByOpenId(openid);
        List<PoetrySysSearchHistory> historyList = searchHistoryRepository.findBySysUsers(users);
        return new SuccessResponse(historyList);
    }

    @Override
    public SuccessResponse getSearchData(String word, int type, String openid) {
        List<Object[]> result = new ArrayList<>();
        String words = word;
        System.out.println(words);
        if(type == 0){
            result = poetsRepository.findByPoetsNameLike(words);
        }else if(type == 1){
            result = poemsRepository.findByPoemTitleLike(words);
        }else if(type == 2){
            result = poemListsRepository.findByPoemListTitleLike(words);
        }else if(type == 3){
            result = poetrySysRhesisRepository.findByRhesisContentLike(words);
        }
        PoetrySysSearchHistory ps = new PoetrySysSearchHistory();
        PoetrySysUsers users = usersRepository.findByOpenId(openid);
        ps.searchWord = word;
        ps.sysUsers = users;
        searchHistoryRepository.save(ps);
        return new SuccessResponse(result);
    }
}
