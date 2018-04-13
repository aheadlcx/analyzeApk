package qsbk.app.utils;

import qsbk.app.utils.TileBackground.BgImageType;

/* synthetic */ class bd {
    static final /* synthetic */ int[] a = new int[BgImageType.values().length];

    static {
        try {
            a[BgImageType.ARTICLE.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[BgImageType.SHARE.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[BgImageType.AD.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
    }
}
