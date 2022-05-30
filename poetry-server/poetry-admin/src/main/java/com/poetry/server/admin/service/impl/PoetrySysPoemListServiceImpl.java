package com.poetry.server.admin.service.impl;

import com.poetry.common.consts.enums.ErrorCode;
import com.poetry.common.exception.CustomException;
import com.poetry.common.response.SuccessResponse;
import com.poetry.db.entity.PoetrySysPoemLists;
import com.poetry.db.entity.PoetrySysPoems;
import com.poetry.db.repository.PoetrySysPoemListsRepository;
import com.poetry.server.admin.dto.PoetrySysPoemListsDto;
import com.poetry.server.admin.service.PoetrySysPoemListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
@Service
@Slf4j
@Transactional
public class PoetrySysPoemListServiceImpl implements PoetrySysPoemListService {
    @Autowired
    private PoetrySysPoemListsRepository repository;
    @Override
    public SuccessResponse getAllPoemList() {
        List<PoetrySysPoemLists> poemLists = repository.findAll();
        return new SuccessResponse(poemLists);
    }

    @Override
    public SuccessResponse getPoemListById(String id) {
        PoetrySysPoemLists poemLists = repository.findByPoemListId(id);
        return new SuccessResponse(poemLists);
    }

    @Override
    public SuccessResponse savePoemList(PoetrySysPoemListsDto poemListDto) {
        PoetrySysPoemLists poemLists = new PoetrySysPoemLists();
        poemLists.poemListTitle = poemListDto.getPoemListTitle();
        poemLists.poemListDesc = poemListDto.getPoemListDesc();
        poemLists.poemListImage = poemListDto.getPoemListImage();
        if(poemListDto.getPoems() != null){
            poemLists.poems = poemListDto.getPoems();
        }

        System.out.println(poemListDto.getPoems());
        repository.saveAndFlush(poemLists);
        return new SuccessResponse(true);
    }

    @Override
    public SuccessResponse updatePoemList(PoetrySysPoemListsDto poemListDto) {
        String queryId = poemListDto.getPoemListId();
        PoetrySysPoemLists poemLists = repository.findByPoemListId(queryId);
        poemLists.poemListTitle = poemListDto.getPoemListTitle();
        poemLists.poemListDesc = poemListDto.getPoemListDesc();
        poemLists.poemListImage = poemListDto.getPoemListImage();
        poemLists.poems = poemListDto.getPoems();
        repository.saveAndFlush(poemLists);
        return new SuccessResponse(true);
    }

    @Override
    public SuccessResponse savePoemListPoems(String id, Set<PoetrySysPoems> poems){
        PoetrySysPoemLists poemLists = repository.findByPoemListId(id);
//        poemLists.poetrySysPoems = poems;
        repository.saveAndFlush(poemLists);
        return new SuccessResponse(true);
    }

    @Override
    public SuccessResponse deletePoemListById(String id) {
        if(id == null || id.length() == 0){
            throw new CustomException(ErrorCode.INVALID_PARAM.getCode(), ErrorCode.INVALID_PARAM.getSemantics());
        }
        repository.deleteByPoemListId(id);
        return new SuccessResponse(true);
    }

    @Override
    public SuccessResponse queryPoemListByWords(Map<String, Object> queryMap) {
        List<PoetrySysPoemLists> poemLists = repository.findAll((Specification<PoetrySysPoemLists>) (root, query, criteriaBuilder) -> {
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
