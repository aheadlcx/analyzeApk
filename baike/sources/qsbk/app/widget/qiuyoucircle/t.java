package qsbk.app.widget.qiuyoucircle;

import qsbk.app.model.relationship.Relationship;

/* synthetic */ class t {
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
    }
}
