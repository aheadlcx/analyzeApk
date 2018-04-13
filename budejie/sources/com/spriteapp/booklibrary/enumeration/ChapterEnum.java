package com.spriteapp.booklibrary.enumeration;

public enum ChapterEnum {
    UN_SUBSCRIBER(12007),
    UN_LOGIN(12009),
    BALANCE_SHORT(12010),
    USER_UN_LOGIN(11002),
    CHAPTER_IS_VIP(1),
    DO_NOT_NEED_BUY(0),
    NEED_BUY(1),
    AUTO_BUY(0),
    TO_PAY_PAGE(1),
    NOT_READ(0),
    HAS_READ(1);
    
    private int code;

    private ChapterEnum(int i) {
        this.code = i;
    }

    public int getCode() {
        return this.code;
    }
}
