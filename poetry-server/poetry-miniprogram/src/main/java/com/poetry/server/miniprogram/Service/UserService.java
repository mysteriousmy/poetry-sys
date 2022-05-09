package com.poetry.server.miniprogram.Service;

import com.poetry.common.response.SuccessResponse;
import com.poetry.server.miniprogram.dto.CollectDto;
import com.poetry.server.miniprogram.dto.UserDto;

public interface UserService {
    SuccessResponse doLogin(UserDto userDto);

    SuccessResponse doCheck(UserDto userDto);

    SuccessResponse checkCollect(String id, String openid);

    SuccessResponse collect(CollectDto dto);

    SuccessResponse getCollectByType(int type, String openid);

    SuccessResponse getHistoryByUser(String openid);
}
