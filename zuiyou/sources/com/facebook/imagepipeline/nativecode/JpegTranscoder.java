package com.facebook.imagepipeline.nativecode;

import com.facebook.common.internal.d;
import com.facebook.common.internal.g;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@d
public class JpegTranscoder {
    @d
    private static native void nativeTranscodeJpeg(InputStream inputStream, OutputStream outputStream, int i, int i2, int i3) throws IOException;

    static {
        a.a();
    }

    public static boolean a(int i) {
        return i >= 0 && i <= 270 && i % 90 == 0;
    }

    public static void a(InputStream inputStream, OutputStream outputStream, int i, int i2, int i3) throws IOException {
        boolean z;
        boolean z2 = false;
        g.a(i2 >= 1);
        if (i2 <= 16) {
            z = true;
        } else {
            z = false;
        }
        g.a(z);
        if (i3 >= 0) {
            z = true;
        } else {
            z = false;
        }
        g.a(z);
        if (i3 <= 100) {
            z = true;
        } else {
            z = false;
        }
        g.a(z);
        g.a(a(i));
        if (!(i2 == 8 && i == 0)) {
            z2 = true;
        }
        g.a(z2, (Object) "no transformation requested");
        nativeTranscodeJpeg((InputStream) g.a((Object) inputStream), (OutputStream) g.a((Object) outputStream), i, i2, i3);
    }
}
