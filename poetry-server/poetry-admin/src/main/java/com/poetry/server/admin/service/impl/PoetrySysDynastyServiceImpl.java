package com.poetry.server.admin.service.impl;

import com.poetry.common.response.SuccessResponse;
import com.poetry.db.entity.PoetrySysDynasty;
import com.poetry.db.repository.PoetrySysDynastyRepository;
import com.poetry.server.admin.dto.PoetrySysDynastyDto;
import com.poetry.server.admin.service.PoetrySysDynastyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Transactional
public class PoetrySysDynastyServiceImpl implements PoetrySysDynastyService {
    @Autowired
    private PoetrySysDynastyRepository repository;
    @Override
    public SuccessResponse getAllDynasty() {
        List<PoetrySysDynasty> dynastys = repository.findAll();
        return new SuccessResponse(dynastys);
    }

    @Override
    public SuccessResponse getDynastyById(String id) {
        PoetrySysDynasty dynasty = repository.findByDynastyId(id);
        return new SuccessResponse(dynasty);
    }

    @Override
    public SuccessResponse saveDynasty(PoetrySysDynastyDto dynastyDto) {
        if(repository.findByDynastyName(dynastyDto.getDynastyName()) != null){
            return new SuccessResponse("该朝代已经存在！");
        }
        PoetrySysDynasty dynasty = new PoetrySysDynasty();
        dynasty.dynastyName = dynastyDto.getDynastyName();
        dynasty.dynastyDesc = dynastyDto.getDynastyDesc();
        repository.save(dynasty);
        return new SuccessResponse(true);
    }

    @Override
    public SuccessResponse updateDynasty(PoetrySysDynastyDto dynastyDto) {
        PoetrySysDynasty dynasty = new PoetrySysDynasty();
        dynasty.dynastyId = dynastyDto.getDynastyId();
        dynasty.dynastyName = dynastyDto.getDynastyName();
        dynasty.dynastyDesc = dynastyDto.getDynastyDesc();
        repository.saveAndFlush(dynasty);
        return new SuccessResponse(true);
    }

    @Override
    public SuccessResponse deleteDynastyById(String id) {
        repository.deleteByDynastyId(id);
        return new SuccessResponse(true);
    }

    @Override
    public SuccessResponse queryDynastyByWords(Map<String, Object> queryMap) {
        List<PoetrySysDynasty> dynasties = repository.findAll((Specification<PoetrySysDynasty>) (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            queryMap.forEach((q,b) -> {
                list.add(criteriaBuilder.like(root.get(q).as(String.class), "%" + b.toString() + "%"));
            });
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(p);
        });
        return new SuccessResponse(dynasties);
    }
}
