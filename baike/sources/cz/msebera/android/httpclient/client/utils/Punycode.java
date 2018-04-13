package cz.msebera.android.httpclient.client.utils;

import cz.msebera.android.httpclient.annotation.Immutable;

@Immutable
@Deprecated
public class Punycode {
    private static final Idn a;

    static {
        Idn jdkIdn;
        try {
            jdkIdn = new JdkIdn();
        } catch (Exception e) {
            jdkIdn = new Rfc3492Idn();
        }
        a = jdkIdn;
    }

    public static String toUnicode(String str) {
        return a.toUnicode(str);
    }
}
