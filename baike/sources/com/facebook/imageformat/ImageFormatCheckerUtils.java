package com.facebook.imageformat;

import com.facebook.common.internal.Preconditions;
import cz.msebera.android.httpclient.protocol.HTTP;

public class ImageFormatCheckerUtils {
    public static byte[] asciiBytes(String str) {
        Preconditions.checkNotNull(str);
        try {
            return str.getBytes(HTTP.ASCII);
        } catch (Throwable e) {
            throw new RuntimeException("ASCII not found!", e);
        }
    }

    public static boolean startsWithPattern(byte[] bArr, byte[] bArr2) {
        Preconditions.checkNotNull(bArr);
        Preconditions.checkNotNull(bArr2);
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

    public static int indexOfPattern(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = 0;
        Preconditions.checkNotNull(bArr);
        Preconditions.checkNotNull(bArr2);
        if (i2 > i) {
            return -1;
        }
        byte b = bArr2[0];
        int i4 = i - i2;
        while (i3 <= i4) {
            if (bArr[i3] != b) {
                do {
                    i3++;
                    if (i3 > i4) {
                        break;
                    }
                } while (bArr[i3] != b);
            }
            if (i3 <= i4) {
                int i5 = i3 + 1;
                int i6 = (i5 + i2) - 1;
                int i7 = 1;
                while (i5 < i6 && bArr[i5] == bArr2[i7]) {
                    i5++;
                    i7++;
                }
                if (i5 == i6) {
                    return i3;
                }
            }
            i3++;
        }
        return -1;
    }

    private ImageFormatCheckerUtils() {
    }
}
