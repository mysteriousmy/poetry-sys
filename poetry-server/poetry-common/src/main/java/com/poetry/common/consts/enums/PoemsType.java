package com.poetry.common.consts.enums;

public enum PoemsType {
    POEM(0), //诗
    WORD(1), //词
    SONG(2), //曲
    CLASSICAL(3), //文言文
    ALL(4); // 全部
    private int value;
    PoemsType(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
