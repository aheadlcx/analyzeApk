package com.ishumei.f;

import android.text.TextUtils;

public class d {
    public static boolean a(String str) {
        return str == null ? true : str.isEmpty();
    }

    public static boolean a(String str, String str2) {
        return TextUtils.equals(str, str2);
    }

    public static byte[] a(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = (byte) (bArr[i] ^ -1);
        }
        return bArr2;
    }

    public static boolean b(String str) {
        return !a(str);
    }

    public static String c(String str) {
        return str == null ? null : str.trim();
    }

    public static boolean d(String str) {
        return a(c(str));
    }

    public static String e(String str) {
        return str == null ? "" : str;
    }

    public static byte[] f(String str) {
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) Integer.parseInt(new String(bytes, i, 2), 16);
        }
        return bArr;
    }

    public static String g(String str) {
        return new String(a(f(str)));
    }
}
