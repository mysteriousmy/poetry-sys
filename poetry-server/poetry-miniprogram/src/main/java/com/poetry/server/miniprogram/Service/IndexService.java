package com.poetry.server.miniprogram.Service;

import com.poetry.common.consts.enums.PoemsType;
import com.poetry.common.response.SuccessResponse;

public interface IndexService {
    SuccessResponse getAllRhesisToBanner();

    SuccessResponse getRandomPoemList();

    SuccessResponse getAllDynasty();

    SuccessResponse getPoemsList(String id, int type);

    SuccessResponse getPoetsList(String id);

    SuccessResponse getAllRhesis();
}
