package com.spriteapp.booklibrary.enumeration;

public enum PageStyleEnum {
    DEFAULT_STYLE(0),
    FLIP_STYLE(1);
    
    private int value;

    private PageStyleEnum(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
