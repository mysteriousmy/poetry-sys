package com.poetry.server.admin.service;

import com.poetry.common.response.SuccessResponse;
import com.poetry.server.admin.dto.PoetrySysPoetsDto;

public interface PoetrySysPoetsService {
    SuccessResponse getAllPoets();

    SuccessResponse getPoetsById(String id);

    SuccessResponse savePoets(PoetrySysPoetsDto poetDto);

    SuccessResponse updatePoets(PoetrySysPoetsDto poetDto);

    SuccessResponse deletePoetsById(String id);
}
