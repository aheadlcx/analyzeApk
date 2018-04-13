package com.spriteapp.booklibrary.enumeration;

public enum BookEnum {
    NOT_ADD_SHELF(0),
    ADD_SHELF(1),
    MY_SHELF_DATA(10000),
    RECOMMEND_DATA(12011),
    BOOK_FINISH_TAG(1),
    MY_BOOK(0),
    RECOMMEND_BOOK(1);
    
    private int value;

    private BookEnum(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
