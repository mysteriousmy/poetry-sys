package com.poetry.common.consts.enums;

//管理员类型
public enum AdminsType {
    SUPER_ADMIN(0), //超管
    NORMAL_ADMIN(1); //普通管理员

    private int value;
    AdminsType(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
