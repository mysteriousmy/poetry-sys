package com.poetry.server.admin.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PoetrySysAdminLoginDto {
    @Size(max = 30)
    @NotBlank
    private String adminsName;

    @NotBlank
    private String adminsPassword;



}

