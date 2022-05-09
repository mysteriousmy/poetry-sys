package com.poetry.common.consts.enums;

public enum ErrorCode {
    NORMARL(200, "正常"),
    /**
     * 错误的客户端请求，包括缺少参数、参数格式错误
     */
    INVALID_PARAM(400, "参数无效"),
    DATA_NOT_FOUND(401, "没找到相关数据"),
    INVALID_PARAM_VALUE(400, "输入内容不可为空！"),

    INVALID_PARAM_NULL(400, "参数为空"),

    SESSION_NOT_EXIST(540,"登录已过期!"),
    SERVER_ERROR(500, "服务器内部错误"),
    SERVER_TOKEN_DECODE_ERROR(500, "解码token失败！"),
    SERVER_TOKEN_EXPIRE(508, "TOKEN过期");
    private final int code;
    private final String semantics;

    ErrorCode(int id, String semantics) {
        code = id;
        this.semantics = semantics;
    }

    public int getCode() {
        return code;
    }

    public String getSemantics() {
        return semantics;
    }


}
