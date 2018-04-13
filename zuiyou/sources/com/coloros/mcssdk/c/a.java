package com.coloros.mcssdk.c;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public final class a {
    private static String a() {
        String str = "";
        byte[] b = b("com.nearme.mcs");
        int length = b.length % 2 == 0 ? b.length : b.length - 1;
        for (int i = 0; i < length; i += 2) {
            byte b2 = b[i];
            b[i] = b[i + 1];
            b[i + 1] = b2;
        }
        return b != null ? new String(b, Charset.forName("UTF-8")) : str;
    }

    public static String a(String str) {
        String str2 = "";
        if (!TextUtils.isEmpty(str)) {
            try {
                str2 = b.a(str, a());
            } catch (Exception e) {
                c.b("desDecrypt-" + e.getMessage());
            }
        }
        return str2;
    }

    private static byte[] b(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            return new byte[0];
        }
    }
}
