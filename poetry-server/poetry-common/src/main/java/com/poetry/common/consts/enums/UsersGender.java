package com.poetry.common.consts.enums;

public enum UsersGender {
    FEMALE(0), //女
    MALE(1); //男


    private int value;
    UsersGender(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
