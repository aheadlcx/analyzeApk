package cz.msebera.android.httpclient.impl.auth;

import cz.msebera.android.httpclient.auth.AuthProtocolState;

/* synthetic */ class a {
    static final /* synthetic */ int[] a = new int[AuthProtocolState.values().length];

    static {
        try {
            a[AuthProtocolState.CHALLENGED.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[AuthProtocolState.HANDSHAKE.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[AuthProtocolState.SUCCESS.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[AuthProtocolState.FAILURE.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            a[AuthProtocolState.UNCHALLENGED.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
    }
}
