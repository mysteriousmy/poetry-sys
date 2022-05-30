package com.poetry.server.admin.service.impl;

import com.poetry.common.consts.enums.ErrorCode;
import com.poetry.common.exception.CustomException;
import com.poetry.common.response.SuccessResponse;
import com.poetry.db.entity.PoetrySysDynasty;
import com.poetry.db.entity.PoetrySysPoets;
import com.poetry.db.repository.PoetrySysDynastyRepository;
import com.poetry.db.repository.PoetrySysPoetsRepository;
import com.poetry.server.admin.dto.PoetrySysPoetsDto;
import com.poetry.server.admin.service.PoetrySysPoetsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
public class PoetrySysPoetsServiceImpl implements PoetrySysPoetsService {
    @Autowired
    private PoetrySysPoetsRepository repository;
    @Autowired
    private PoetrySysDynastyRepository dynastyRepository;
    @Override
    public SuccessResponse getAllPoets() {
        List<PoetrySysPoets> poets = repository.findAll();
        return new SuccessResponse(poets);
    }

    @Override
    public SuccessResponse getPoetsById(String id) {
        PoetrySysPoets poet = repository.findByPoetsId(id);
        return new SuccessResponse(poet);
    }

    @Override
    public SuccessResponse savePoets(PoetrySysPoetsDto poetDto) {
        return getSuccessResponse(poetDto,0);
    }

    @Override
    public SuccessResponse updatePoets(PoetrySysPoetsDto poetDto) {
        return getSuccessResponse(poetDto,1);
    }

    private SuccessResponse getSuccessResponse(PoetrySysPoetsDto poetDto, int type) {
        PoetrySysPoets poets = new PoetrySysPoets();
        if(type == 1){
            poets = repository.findByPoetsId(poetDto.getPoetsId());
        }
        poets.poetsName = poetDto.getPoetsName();
        poets.poetsDesc = poetDto.getPoetsDesc();
        poets.poetsPhoto = poetDto.getPoetsPhoto();
        PoetrySysDynasty dynasty = dynastyRepository.findByDynastyId(poetDto.getDynastyId());
        poets.dynasty = dynasty;
        repository.save(poets);
        return new SuccessResponse(true);
    }

    @Override
    public SuccessResponse deletePoetsById(String id) {
        if(id == null || id.length() == 0){
            throw new CustomException(ErrorCode.INVALID_PARAM.getCode(), ErrorCode.INVALID_PARAM.getSemantics());
        }
        repository.deleteByPoetsId(id);
        return new SuccessResponse(true);
    }
}
