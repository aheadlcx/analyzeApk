package com.facebook.d;

import java.io.IOException;
import java.io.InputStream;

class d {
    private static final Class<?> a = d.class;

    private static class a {
        boolean a;
        int b;
        int c;

        private a() {
        }
    }

    d() {
    }

    public static int a(int i) {
        switch (i) {
            case 0:
            case 1:
                return 0;
            case 3:
                return 180;
            case 6:
                return 90;
            case 8:
                return 270;
            default:
                com.facebook.common.c.a.c(a, "Unsupported orientation");
                return 0;
        }
    }

    public static int a(InputStream inputStream, int i) throws IOException {
        a aVar = new a();
        int a = a(inputStream, i, aVar);
        int i2 = aVar.c - 8;
        if (a == 0 || i2 > a) {
            return 0;
        }
        inputStream.skip((long) i2);
        return a(inputStream, a(inputStream, a - i2, aVar.a, 274), aVar.a);
    }

    private static int a(InputStream inputStream, int i, a aVar) throws IOException {
        if (i <= 8) {
            return 0;
        }
        aVar.b = c.a(inputStream, 4, false);
        int i2 = i - 4;
        if (aVar.b == 1229531648 || aVar.b == 1296891946) {
            aVar.a = aVar.b == 1229531648;
            aVar.c = c.a(inputStream, 4, aVar.a);
            int i3 = i2 - 4;
            if (aVar.c >= 8 && aVar.c - 8 <= i3) {
                return i3;
            }
            com.facebook.common.c.a.e(a, "Invalid offset");
            return 0;
        }
        com.facebook.common.c.a.e(a, "Invalid TIFF header");
        return 0;
    }

    private static int a(InputStream inputStream, int i, boolean z, int i2) throws IOException {
        if (i < 14) {
            return 0;
        }
        int a = c.a(inputStream, 2, z);
        int i3 = i - 2;
        while (true) {
            int i4 = a - 1;
            if (a <= 0 || i3 < 12) {
                return 0;
            }
            a = i3 - 2;
            if (c.a(inputStream, 2, z) == i2) {
                return a;
            }
            inputStream.skip(10);
            i3 = a - 10;
            a = i4;
        }
    }

    private static int a(InputStream inputStream, int i, boolean z) throws IOException {
        if (i < 10 || c.a(inputStream, 2, z) != 3 || c.a(inputStream, 4, z) != 1) {
            return 0;
        }
        int a = c.a(inputStream, 2, z);
        c.a(inputStream, 2, z);
        return a;
    }
}
