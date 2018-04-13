package com.facebook.d;

import java.io.IOException;
import java.io.InputStream;

class c {
    public static int a(InputStream inputStream, int i, boolean z) throws IOException {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            int read = inputStream.read();
            if (read == -1) {
                throw new IOException("no more bytes");
            }
            if (z) {
                i2 |= (read & 255) << (i3 * 8);
            } else {
                i2 = (i2 << 8) | (read & 255);
            }
        }
        return i2;
    }
}
