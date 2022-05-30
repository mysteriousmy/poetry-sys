package com.poetry.server.admin.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SysInitDto {
    @Size(max = 30,min = 2)
    @NotBlank(message = "用户名不能为空！")
    private String username;
    @Size(max = 25,min = 8)
    @NotBlank(message = "密码不可为空！")
    private String password;
    @Email(message = "邮箱格式有误！")
    private String email;
}
