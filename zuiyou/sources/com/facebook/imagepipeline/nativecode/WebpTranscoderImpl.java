package com.facebook.imagepipeline.nativecode;

import android.os.Build.VERSION;
import com.facebook.c.b;
import com.facebook.c.c;
import com.facebook.common.internal.d;
import com.facebook.common.internal.g;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@d
public class WebpTranscoderImpl implements c {
    @d
    private static native void nativeTranscodeWebpToJpeg(InputStream inputStream, OutputStream outputStream, int i) throws IOException;

    @d
    private static native void nativeTranscodeWebpToPng(InputStream inputStream, OutputStream outputStream) throws IOException;

    public boolean a(c cVar) {
        if (cVar == b.e) {
            if (VERSION.SDK_INT >= 14) {
                return true;
            }
            return false;
        } else if (cVar == b.f || cVar == b.g || cVar == b.h) {
            return com.facebook.common.g.c.c;
        } else {
            if (cVar == b.i) {
                return false;
            }
            throw new IllegalArgumentException("Image format is not a WebP.");
        }
    }

    public void a(InputStream inputStream, OutputStream outputStream, int i) throws IOException {
        b.a();
        nativeTranscodeWebpToJpeg((InputStream) g.a((Object) inputStream), (OutputStream) g.a((Object) outputStream), i);
    }

    public void a(InputStream inputStream, OutputStream outputStream) throws IOException {
        b.a();
        nativeTranscodeWebpToPng((InputStream) g.a((Object) inputStream), (OutputStream) g.a((Object) outputStream));
    }
}
