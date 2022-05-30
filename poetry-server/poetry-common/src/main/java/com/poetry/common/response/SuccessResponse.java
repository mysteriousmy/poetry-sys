package com.poetry.common.response;


public class SuccessResponse {
    public int code;
    public String msg;
    public Object data;
    public SuccessResponse(Object data){
        this.code = 200;
        this.msg = "成功取得数据";
        this.data = data;
    }
    public SuccessResponse(String msg, Object data){
        this.code = 200;
        this.msg = msg;
        this.data = data;
    }
}
