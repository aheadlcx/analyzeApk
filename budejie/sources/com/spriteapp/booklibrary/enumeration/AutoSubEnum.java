package com.spriteapp.booklibrary.enumeration;

public enum AutoSubEnum {
    NOT_AUTO_SUB(0),
    AUTO_SUB(1);
    
    private int value;

    private AutoSubEnum(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
