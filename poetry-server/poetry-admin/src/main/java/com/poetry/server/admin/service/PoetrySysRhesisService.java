package com.poetry.server.admin.service;

import com.poetry.common.response.SuccessResponse;
import com.poetry.server.admin.dto.PoetrySysRhesisDto;

public interface PoetrySysRhesisService {
    SuccessResponse getAllRhesis();

    SuccessResponse getRhesisById(String id);

    SuccessResponse saveRhesis(PoetrySysRhesisDto rhesisDto);

    SuccessResponse updateRhesis(PoetrySysRhesisDto rhesisDto);

    SuccessResponse deleteRhesisById(String id);
}
