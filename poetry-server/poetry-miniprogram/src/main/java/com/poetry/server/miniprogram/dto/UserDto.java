package com.poetry.server.miniprogram.dto;

import com.poetry.common.consts.enums.UsersGender;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserDto {
    @NotBlank(message = "openid不能为空")
    private String openId;
    @NotBlank(message = "用户名不可为空")
    private String userName;

    private String userPhoto;
    private UsersGender userGender;
}
