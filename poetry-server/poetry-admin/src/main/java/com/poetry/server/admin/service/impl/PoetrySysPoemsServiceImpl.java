package com.poetry.server.admin.service.impl;

import com.poetry.common.consts.enums.ErrorCode;
import com.poetry.common.exception.CustomException;
import com.poetry.common.response.SuccessResponse;
import com.poetry.db.entity.PoetrySysPoemLists;
import com.poetry.db.entity.PoetrySysPoems;
import com.poetry.db.entity.PoetrySysPoets;
import com.poetry.db.repository.PoetrySysPoemsRepository;
import com.poetry.db.repository.PoetrySysPoetsRepository;
import com.poetry.server.admin.dto.PoetrySysPoemsDto;
import com.poetry.server.admin.service.PoetrySysPoemsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Transactional
public class PoetrySysPoemsServiceImpl implements PoetrySysPoemsService {
    @Autowired
    private PoetrySysPoemsRepository poemsRepository;
    @Autowired
    private PoetrySysPoetsRepository poetsRepository;
    @Override
    public SuccessResponse getAllPoems() {
        List<PoetrySysPoems> poems = poemsRepository.findAll();
        return new SuccessResponse(poems);
    }

    @Override
    public SuccessResponse getPoemsById(String id) {
        PoetrySysPoems poem = poemsRepository.findByPoemId(id);
        return new SuccessResponse(poem);
    }

    @Override
    public SuccessResponse savePoems(PoetrySysPoemsDto poemsDto) {
        return getSuccessResponse(poemsDto,0);
    }

    @Override
    public SuccessResponse updatePoems(PoetrySysPoemsDto poemsDto) {
        return getSuccessResponse(poemsDto,1);
    }

    private SuccessResponse getSuccessResponse(PoetrySysPoemsDto poemsDto, int type) {
        PoetrySysPoems poems = new PoetrySysPoems();
        if(type == 1){
            poems = poemsRepository.findByPoemId(poemsDto.getPoemId());
        }
        poems.poemTitle = poemsDto.getPoemTitle();
        poems.poemContent = poemsDto.getPoemContent();
        poems.poemAnnotation = poemsDto.getPoemAnnotation();
        poems.poemAppreciation = poemsDto.getPoemAppreciation();
        poems.poemsType = poemsDto.getPoemsType();
        poems.poemCreateScene = poemsDto.getPoemCreateScene();
        poems.poemTranslates = poemsDto.getPoemTranslates();
        PoetrySysPoets poets = poetsRepository.findByPoetsId(poemsDto.getPoetsId());
        poems.poets = poets;
        poems.dynasty = poets.dynasty;
        poemsRepository.save(poems);
        return new SuccessResponse(poems);
    }

    @Override
    public SuccessResponse deletePoemsById(String id) {
        if(id == null || id.length() == 0){
            throw new CustomException(ErrorCode.INVALID_PARAM.getCode(), ErrorCode.INVALID_PARAM.getSemantics());
        }
        poemsRepository.deleteByPoemId(id);
        return new SuccessResponse(true);
    }

    @Override
    public SuccessResponse queryPoemByWords(Map<String, Object> queryMap) {
        List<PoetrySysPoems> poemLists = poemsRepository.findAll((Specification<PoetrySysPoems>) (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            queryMap.forEach((q,b) -> {
                list.add(criteriaBuilder.like(root.get(q).as(String.class), "%" + b.toString() + "%"));
            });
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(p);
        });
        return new SuccessResponse(poemLists);
    }
}
