package com.poetry.server.miniprogram.Service.impl;

import com.poetry.common.consts.enums.ErrorCode;
import com.poetry.common.consts.enums.PoemsType;
import com.poetry.common.exception.CustomException;
import com.poetry.common.response.SuccessResponse;
import com.poetry.common.util.RandomUtil;
import com.poetry.db.entity.*;
import com.poetry.db.repository.*;
import com.poetry.server.miniprogram.Service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
public class IndexServiceImpl implements IndexService {
    @Autowired
    private PoetrySysRhesisRepository repository;
    @Autowired
    private PoetrySysPoemListsRepository poemListsRepository;
    @Autowired
    private PoetrySysDynastyRepository dynastyRepository;
    @Autowired
    private PoetrySysPoemsRepository poemsRepository;
    @Autowired
    private PoetrySysPoetsRepository poetsRepo;
    @Autowired
    private PoetrySysRhesisRepository rhesisRepository;
    @Override
    public SuccessResponse getAllRhesisToBanner() {
        List<PoetrySysRhesis> rheses = repository.findAll();
        int count = rheses.size();
        if(rheses.size() >= 4){
            count = 4;
        }
        rheses = RandomUtil.getRandomThreeInfoList(rheses, count);
        return new SuccessResponse(rheses);
    }

    @Override
    public SuccessResponse getRandomPoemList() {
        List<PoetrySysPoemLists> poemLists = poemListsRepository.findAll();
        int count = poemLists.size();
        if(poemLists.size() >= 10){
            count = 10;
        }
        poemLists = RandomUtil.getRandomThreeInfoList(poemLists, count);
        return new SuccessResponse(poemLists);
    }

    @Override
    public SuccessResponse getAllDynasty() {
        List<PoetrySysDynasty> dynasties = dynastyRepository.findAll();
        return new SuccessResponse((dynasties));
    }

    @Override
    public SuccessResponse getPoemsList(String id, int type) {
        List<PoetrySysPoems> poems;
        if(id == null){
            throw new CustomException(ErrorCode.INVALID_PARAM.getCode(), "id或类型不存在！");
        }
        if(id.equals("-1") && type == 4){
            poems = poemsRepository.findAll();
            return new SuccessResponse(poems);
        }
        if(id.equals("-1")){
            System.out.println(PoemsType.values()[type]);
            poems = poemsRepository.findByPoemsType(PoemsType.values()[type]);
        }else{
            PoetrySysDynasty dynasty = dynastyRepository.findByDynastyId(id);
            if(dynasty == null){
                throw new CustomException(ErrorCode.INVALID_PARAM.getCode(), "该朝代不存在！");
            }
            if(type == 4){
                poems = poemsRepository.findByDynasty(dynasty);
            }else{
                poems = poemsRepository.findByPoemsTypeAndDynasty(PoemsType.values()[type], dynasty);
            }
        }
        return new SuccessResponse(poems);
    }

    @Override
    public SuccessResponse getPoetsList(String id) {
        List<PoetrySysPoets> poets;
        if(id == null){
            throw new CustomException(ErrorCode.INVALID_PARAM.getCode(), "id或类型不存在！");
        }
        if(id.equals("-1")){
            poets = poetsRepo.findAll();
            return new SuccessResponse(poets);
        }
        PoetrySysDynasty dynasty = dynastyRepository.findByDynastyId(id);
        if(dynasty == null){
            throw new CustomException(ErrorCode.INVALID_PARAM.getCode(), "该朝代不存在！");
        }
        poets = poetsRepo.findByDynasty(dynasty);
        return new SuccessResponse(poets);
    }

    @Override
    public SuccessResponse getAllRhesis() {
        List<PoetrySysRhesis> rheses = rhesisRepository.findAll();
        return new SuccessResponse(rheses);
    }
}
