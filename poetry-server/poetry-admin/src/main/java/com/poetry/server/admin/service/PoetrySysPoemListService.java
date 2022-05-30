package com.poetry.server.admin.service;

import com.poetry.common.response.SuccessResponse;
import com.poetry.db.entity.PoetrySysPoems;
import com.poetry.server.admin.dto.PoetrySysPoemListsDto;

import java.util.Map;
import java.util.Set;

public interface PoetrySysPoemListService {
    SuccessResponse getAllPoemList();

    SuccessResponse getPoemListById(String id);

    SuccessResponse savePoemListPoems(String id, Set<PoetrySysPoems> poems);

    SuccessResponse savePoemList(PoetrySysPoemListsDto poemListDto);

    SuccessResponse updatePoemList(PoetrySysPoemListsDto poemListDto);

    SuccessResponse deletePoemListById(String id);

    SuccessResponse queryPoemListByWords(Map<String, Object> queryMap);

}
