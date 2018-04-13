package com.facebook.common.g;

import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import android.util.Base64;

public class c {
    public static final boolean a = (VERSION.SDK_INT <= 17);
    public static final boolean b;
    public static final boolean c = b();
    public static b d = null;
    private static boolean e = false;
    private static final byte[] f = a("RIFF");
    private static final byte[] g = a("WEBP");
    private static final byte[] h = a("VP8 ");
    private static final byte[] i = a("VP8L");
    private static final byte[] j = a("VP8X");

    static {
        boolean z = true;
        if (VERSION.SDK_INT < 14) {
            z = false;
        }
        b = z;
    }

    public static b a() {
        if (e) {
            return d;
        }
        b bVar;
        try {
            bVar = (b) Class.forName("com.facebook.webpsupport.WebpBitmapFactoryImpl").newInstance();
        } catch (Throwable th) {
            bVar = null;
        }
        e = true;
        return bVar;
    }

    private static byte[] a(String str) {
        try {
            return str.getBytes("ASCII");
        } catch (Throwable e) {
            throw new RuntimeException("ASCII not found!", e);
        }
    }

    private static boolean b() {
        if (VERSION.SDK_INT < 17) {
            return false;
        }
        if (VERSION.SDK_INT == 17) {
            byte[] decode = Base64.decode("UklGRkoAAABXRUJQVlA4WAoAAAAQAAAAAAAAAAAAQUxQSAwAAAARBxAR/Q9ERP8DAABWUDggGAAAABQBAJ0BKgEAAQAAAP4AAA3AAP7mtQAAAA==", 0);
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(decode, 0, decode.length, options);
            if (!(options.outHeight == 1 && options.outWidth == 1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(byte[] bArr, int i) {
        boolean a = a(bArr, i + 12, j);
        boolean z;
        if ((bArr[i + 20] & 2) == 2) {
            z = true;
        } else {
            z = false;
        }
        if (a && r2) {
            return true;
        }
        return false;
    }

    public static boolean b(byte[] bArr, int i) {
        return a(bArr, i + 12, h);
    }

    public static boolean c(byte[] bArr, int i) {
        return a(bArr, i + 12, i);
    }

    public static boolean a(byte[] bArr, int i, int i2) {
        return i2 >= 21 && a(bArr, i + 12, j);
    }

    public static boolean d(byte[] bArr, int i) {
        boolean a = a(bArr, i + 12, j);
        boolean z;
        if ((bArr[i + 20] & 16) == 16) {
            z = true;
        } else {
            z = false;
        }
        if (a && r2) {
            return true;
        }
        return false;
    }

    public static boolean b(byte[] bArr, int i, int i2) {
        return i2 >= 20 && a(bArr, i, f) && a(bArr, i + 8, g);
    }

    private static boolean a(byte[] bArr, int i, byte[] bArr2) {
        if (bArr2 == null || bArr == null || bArr2.length + i > bArr.length) {
            return false;
        }
        for (int i2 = 0; i2 < bArr2.length; i2++) {
            if (bArr[i2 + i] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }
}
