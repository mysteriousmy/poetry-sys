package com.poetry.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    public int errCode;
    public String errMsg;
    public String errContent;
}
