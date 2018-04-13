package com.alipay.android.phone.mrpc.core;

import org.apache.http.protocol.HTTP;
import org.apache.http.util.CharArrayBuffer;

class e {
    private static char a(char c) {
        return (c < 'A' || c > 'Z') ? c : (char) (c + 32);
    }

    static int a(CharArrayBuffer charArrayBuffer, int i) {
        int length = charArrayBuffer.length();
        char[] buffer = charArrayBuffer.buffer();
        for (int i2 = 0; i2 < length; i2++) {
            char c = buffer[i2];
            if (c == i) {
                return i2;
            }
            if (c >= 'A' && c <= 'Z') {
                buffer[i2] = (char) (c + 32);
            }
        }
        return -1;
    }

    static boolean a(CharArrayBuffer charArrayBuffer, int i, String str) {
        int length = charArrayBuffer.length();
        char[] buffer = charArrayBuffer.buffer();
        while (i < length && HTTP.isWhitespace(buffer[i])) {
            i++;
        }
        int length2 = str.length();
        boolean z = length >= i + length2;
        int i2 = 0;
        while (z && i2 < length2) {
            char c = buffer[i + i2];
            char charAt = str.charAt(i2);
            if (c != charAt) {
                z = a(c) == a(charAt);
            }
            i2++;
        }
        return z;
    }
}
