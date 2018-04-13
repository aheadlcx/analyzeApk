package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.impl.cookie.RFC6265CookieSpecProvider.CompatibilityLevel;

/* synthetic */ class e {
    static final /* synthetic */ int[] a = new int[CompatibilityLevel.values().length];

    static {
        try {
            a[CompatibilityLevel.STRICT.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[CompatibilityLevel.IE_MEDIUM_SECURITY.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
    }
}
