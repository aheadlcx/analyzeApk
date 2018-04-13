package com.a.b.a;

import java.util.List;
import org.ahocorasick.a.c;

public final class b {
    static c a = null;
    static h b = null;
    static List<g> c = null;

    public static String a(String str, String str2) {
        return a.a(str, a, c, str2, b);
    }

    public static String a(char c) {
        if (!b(c)) {
            return String.valueOf(c);
        }
        if (c == '〇') {
            return "LING";
        }
        return f.b[c(c)];
    }

    public static boolean b(char c) {
        return ('一' <= c && c <= '龥' && c(c) > 0) || '〇' == c;
    }

    private static int c(char c) {
        int i = c - 19968;
        if (i >= 0 && i < 7000) {
            return a(c.a, c.b, i);
        }
        if (7000 > i || i >= 14000) {
            return a(e.a, e.b, i - 14000);
        }
        return a(d.a, d.b, i - 7000);
    }

    private static short a(byte[] bArr, byte[] bArr2, int i) {
        short s = (short) (bArr2[i] & 255);
        if ((bArr[i / 8] & f.a[i % 8]) != 0) {
            return (short) (s | 256);
        }
        return s;
    }
}
