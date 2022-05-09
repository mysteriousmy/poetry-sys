package com.poetry.server.admin.service.impl;

import com.poetry.common.consts.enums.ErrorCode;
import com.poetry.common.exception.CustomException;
import com.poetry.common.response.SuccessResponse;
import com.poetry.db.entity.PoetrySysPoems;
import com.poetry.db.entity.PoetrySysRhesis;
import com.poetry.db.repository.PoetrySysPoemsRepository;
import com.poetry.db.repository.PoetrySysRhesisRepository;
import com.poetry.server.admin.dto.PoetrySysRhesisDto;
import com.poetry.server.admin.service.PoetrySysRhesisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Slf4j
@Transactional
public class PoetrySysRhesisServiceImpl implements PoetrySysRhesisService {
    @Autowired
    private PoetrySysRhesisRepository repository;
    @Autowired
    private PoetrySysPoemsRepository poemsRepository;
    @Override
    public SuccessResponse getAllRhesis() {
        List<PoetrySysRhesis> rheses = repository.findAll();
        return new SuccessResponse(rheses);
    }

    @Override
    public SuccessResponse getRhesisById(String id) {
        PoetrySysRhesis rhesis = repository.findByRhesisId(id);
        return new SuccessResponse(rhesis);
    }
    @Override
    public SuccessResponse saveRhesis(PoetrySysRhesisDto rhesisDto) {
        PoetrySysRhesis rhesis = new PoetrySysRhesis();
        rhesis.rhesisContent = rhesisDto.getRhesisContent();
        rhesis.rhesisBackground = rhesisDto.getRhesisBackground();
        PoetrySysPoems poems = poemsRepository.findByPoemId(rhesisDto.getPoemId());
        rhesis.poems = poems;
        repository.saveAndFlush(rhesis);
        return new SuccessResponse(true);
    }

    @Override
    public SuccessResponse updateRhesis(PoetrySysRhesisDto rhesisDto) {
        PoetrySysRhesis rhesis = repository.findByRhesisId(rhesisDto.getRhesisId());
        rhesis.rhesisContent = rhesisDto.getRhesisContent();
        rhesis.rhesisBackground = rhesisDto.getRhesisBackground();
        PoetrySysPoems poems = poemsRepository.findByPoemId(rhesisDto.getPoemId());
        rhesis.poems = poems;
        repository.saveAndFlush(rhesis);
        return new SuccessResponse(true);
    }

    @Override
    public SuccessResponse deleteRhesisById(String id) {
        if(id == null || id.length() == 0){
            throw new CustomException(ErrorCode.INVALID_PARAM.getCode(), ErrorCode.INVALID_PARAM.getSemantics());
        }
        repository.deleteByRhesisId(id);
        return new SuccessResponse(true);
    }
}
