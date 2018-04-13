package com.budejie.www.type;

public enum SearchItem$TypeEnum {
    USER(0),
    ADD_MODERATOR(1);
    
    int value;

    private SearchItem$TypeEnum(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
