package com.spriteapp.booklibrary.enumeration;

public enum ApiCodeEnum {
    SUCCESS(10000);
    
    private int value;

    private ApiCodeEnum(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
