package cz.msebera.android.httpclient.client.protocol;

import cz.msebera.android.httpclient.auth.AuthProtocolState;

/* synthetic */ class c {
    static final /* synthetic */ int[] a = new int[AuthProtocolState.values().length];

    static {
        try {
            a[AuthProtocolState.CHALLENGED.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[AuthProtocolState.FAILURE.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
    }
}
