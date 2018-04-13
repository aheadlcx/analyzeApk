package com.facebook.common.util;

import com.facebook.infer.annotation.Functional;

public enum TriState {
    YES,
    NO,
    UNSET;

    @Functional
    public boolean isSet() {
        return this != UNSET;
    }

    @Functional
    public static TriState valueOf(boolean z) {
        return z ? YES : NO;
    }

    @Functional
    public static TriState valueOf(Boolean bool) {
        return bool != null ? valueOf(bool.booleanValue()) : UNSET;
    }

    @Functional
    public boolean asBoolean() {
        switch (this) {
            case YES:
                return true;
            case NO:
                return false;
            case UNSET:
                throw new IllegalStateException("No boolean equivalent for UNSET");
            default:
                throw new IllegalStateException("Unrecognized TriState value: " + this);
        }
    }

    @Functional
    public boolean asBoolean(boolean z) {
        switch (this) {
            case YES:
                return true;
            case NO:
                return false;
            case UNSET:
                return z;
            default:
                throw new IllegalStateException("Unrecognized TriState value: " + this);
        }
    }

    @Functional
    public Boolean asBooleanObject() {
        switch (this) {
            case YES:
                return Boolean.TRUE;
            case NO:
                return Boolean.FALSE;
            case UNSET:
                return null;
            default:
                throw new IllegalStateException("Unrecognized TriState value: " + this);
        }
    }

    @Functional
    public int getDbValue() {
        switch (this) {
            case YES:
                return 1;
            case NO:
                return 2;
            default:
                return 3;
        }
    }

    @Functional
    public static TriState fromDbValue(int i) {
        switch (i) {
            case 1:
                return YES;
            case 2:
                return NO;
            default:
                return UNSET;
        }
    }
}
