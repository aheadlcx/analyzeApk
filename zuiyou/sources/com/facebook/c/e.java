package com.facebook.c;

import com.facebook.common.internal.g;

public class e {
    public static byte[] a(String str) {
        g.a((Object) str);
        try {
            return str.getBytes("ASCII");
        } catch (Throwable e) {
            throw new RuntimeException("ASCII not found!", e);
        }
    }

    public static boolean a(byte[] bArr, byte[] bArr2) {
        g.a((Object) bArr);
        g.a((Object) bArr2);
        if (bArr2.length > bArr.length) {
            return false;
        }
        for (int i = 0; i < bArr2.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }
}
