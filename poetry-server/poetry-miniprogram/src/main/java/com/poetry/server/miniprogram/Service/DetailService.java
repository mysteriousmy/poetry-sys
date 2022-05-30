package com.poetry.server.miniprogram.Service;

import com.poetry.common.response.SuccessResponse;

public interface DetailService {
    SuccessResponse getDetail(String id);

    SuccessResponse getPoetsDetail(String id, String openid);

    SuccessResponse getRhesisDetail(String id, String openid);

    SuccessResponse getPoemDetail(String id, String openid);
}
