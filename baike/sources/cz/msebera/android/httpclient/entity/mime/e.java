package cz.msebera.android.httpclient.entity.mime;

/* synthetic */ class e {
    static final /* synthetic */ int[] a = new int[HttpMultipartMode.values().length];

    static {
        try {
            a[HttpMultipartMode.BROWSER_COMPATIBLE.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[HttpMultipartMode.RFC6532.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
    }
}
