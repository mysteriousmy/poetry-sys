package com.poetry.server.admin.service;

import com.poetry.common.response.SuccessResponse;
import com.poetry.server.admin.dto.PoetrySysDynastyDto;

import java.util.Map;

public interface PoetrySysDynastyService {
    SuccessResponse getAllDynasty();

    SuccessResponse getDynastyById(String id);

    SuccessResponse saveDynasty(PoetrySysDynastyDto dynastyDto);

    SuccessResponse updateDynasty(PoetrySysDynastyDto dynastyDto);

    SuccessResponse deleteDynastyById(String id);

    SuccessResponse queryDynastyByWords(Map<String, Object> queryMap);
}
