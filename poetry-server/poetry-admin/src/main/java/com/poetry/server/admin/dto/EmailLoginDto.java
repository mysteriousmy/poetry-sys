package com.poetry.server.admin.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class EmailLoginDto {

    @Email(message = "邮箱格式错误")
    private String adminsEmail;

    @NotBlank
    private String verifyCode;

}
