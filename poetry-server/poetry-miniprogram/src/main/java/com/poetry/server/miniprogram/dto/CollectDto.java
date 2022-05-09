package com.poetry.server.miniprogram.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class CollectDto {
    @NotBlank
    private String id;
    @Max(value = 1)
    @Min(value = 0)
    private int type;
    @NotBlank
    private String openid;
    @Max(value = 1)
    @Min(value = 0)
    private int option;
}
