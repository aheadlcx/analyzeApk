package com.tencent.mm.sdk.b;

import java.util.TimeZone;

public final class c {
    private static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    private static final long[] q = new long[]{300, 200, 300, 200};
    private static final char[] r = new char[]{'<', '>', '\"', '\'', '&'};
    private static final String[] s = new String[]{"&lt;", "&gt;", "&quot;", "&apos;", "&amp;"};

    public static boolean a(String str) {
        return str == null || str.length() <= 0;
    }
}
