package com.poetry.common.exception;

import com.poetry.common.consts.enums.ErrorCode;

import java.util.function.Supplier;

public class CustomException extends RuntimeException {
    private int code;
    private String errMsg;

    public CustomException(ErrorCode code){
        this.code = code.getCode();
        this.errMsg = code.getSemantics();
    }
    public CustomException(int code, String errMsg){
        this.code = code;
        this.errMsg = errMsg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
