package com.poetry.server.admin.service;

import com.poetry.common.response.SuccessResponse;
import com.poetry.server.admin.dto.PoetrySysPoemsDto;

import java.util.Map;

public interface PoetrySysPoemsService {
    SuccessResponse getAllPoems();

    SuccessResponse getPoemsById(String id);

    SuccessResponse savePoems(PoetrySysPoemsDto poemsDto);

    SuccessResponse updatePoems(PoetrySysPoemsDto poemsDto);

    SuccessResponse deletePoemsById(String id);

    SuccessResponse queryPoemByWords(Map<String, Object> queryMap);
}
