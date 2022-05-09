package com.poetry.server.admin.service;

import com.poetry.common.response.SuccessResponse;
import com.poetry.server.admin.dto.PoetrySysUsersDto;

public interface PoetrySysUsersService {
    SuccessResponse getAllUsers();

    SuccessResponse getUsersById(String id);

    SuccessResponse saveUsers(PoetrySysUsersDto usersDto);

    SuccessResponse updateUsers(PoetrySysUsersDto usersDto);

    SuccessResponse deleteUsersById(String id);
}
