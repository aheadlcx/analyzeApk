package cz.msebera.android.httpclient.client.protocol;

import cz.msebera.android.httpclient.auth.AuthProtocolState;

/* synthetic */ class b {
    static final /* synthetic */ int[] a = new int[AuthProtocolState.values().length];

    static {
        try {
            a[AuthProtocolState.FAILURE.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[AuthProtocolState.SUCCESS.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[AuthProtocolState.CHALLENGED.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
    }
}
