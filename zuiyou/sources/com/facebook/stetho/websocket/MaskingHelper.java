package com.facebook.stetho.websocket;

class MaskingHelper {
    MaskingHelper() {
    }

    public static void unmask(byte[] bArr, byte[] bArr2, int i, int i2) {
        int i3 = 0;
        while (true) {
            int i4 = i2 - 1;
            if (i2 > 0) {
                int i5 = i + 1;
                int i6 = i3 + 1;
                bArr2[i] = (byte) (bArr[i3 % bArr.length] ^ bArr2[i]);
                i3 = i6;
                i2 = i4;
                i = i5;
            } else {
                return;
            }
        }
    }
}
