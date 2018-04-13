package qsbk.app.activity;

import qsbk.app.model.relationship.Relationship;

/* synthetic */ class zd {
    static final /* synthetic */ int[] a = new int[Relationship.values().length];

    static {
        try {
            a[Relationship.FRIEND.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[Relationship.FOLLOW_REPLIED.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[Relationship.FOLLOW_UNREPLIED.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[Relationship.BLACK.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            a[Relationship.FAN.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            a[Relationship.NO_REL_CHATED.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
        try {
            a[Relationship.NO_REL.ordinal()] = 7;
        } catch (NoSuchFieldError e7) {
        }
    }
}
