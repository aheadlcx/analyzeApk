package com.facebook.stetho.common;

import java.nio.charset.Charset;

public class Utf8Charset {
    public static final Charset INSTANCE = Charset.forName("UTF-8");
    public static final String NAME = "UTF-8";

    public static byte[] encodeUTF8(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static String decodeUTF8(byte[] bArr) {
        return new String(bArr, INSTANCE);
    }
}
