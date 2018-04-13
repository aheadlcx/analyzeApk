package com.budejie.www.activity.label.enumeration;

public enum LabelUserEnum {
    ADMINISTRATOR(1),
    MODERATOR(2),
    DEPUTY_MODERATOR(3),
    APPLY_MODERATOR(1),
    APPLY_DEPUTY_MODERATOR(2),
    APPLY_DEPUTY_MODERATOR_SUCCESS(0);
    
    private int value;

    private LabelUserEnum(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
