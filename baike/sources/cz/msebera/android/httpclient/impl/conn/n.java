package cz.msebera.android.httpclient.impl.conn;

import java.net.Proxy.Type;

/* synthetic */ class n {
    static final /* synthetic */ int[] a = new int[Type.values().length];

    static {
        try {
            a[Type.DIRECT.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[Type.HTTP.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[Type.SOCKS.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
    }
}
