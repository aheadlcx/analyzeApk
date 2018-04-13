package com.budejie.www.activity.label.enumeration;

public enum LabelResponseCode {
    APPLY_MODERATOR_SUCCESS(10),
    APPLY_MODERATOR_FAILED(6);
    
    private int value;

    private LabelResponseCode(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
