package com.poetry.server.miniprogram.Service;

import com.poetry.common.response.SuccessResponse;

public interface SearchService {
    SuccessResponse getSearchHistory(String openid);

    SuccessResponse getSearchData(String word, int type, String openid);
}
