package com.facebook.cache.common;

import com.facebook.common.internal.ByteStreams;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class WriterCallbacks {

    /* renamed from: com.facebook.cache.common.WriterCallbacks$1 */
    final class AnonymousClass1 implements WriterCallback {
        final /* synthetic */ InputStream val$is;

        AnonymousClass1(InputStream inputStream) {
            this.val$is = inputStream;
        }

        public void write(OutputStream outputStream) throws IOException {
            ByteStreams.copy(this.val$is, outputStream);
        }
    }

    /* renamed from: com.facebook.cache.common.WriterCallbacks$2 */
    final class AnonymousClass2 implements WriterCallback {
        final /* synthetic */ byte[] val$data;

        AnonymousClass2(byte[] bArr) {
            this.val$data = bArr;
        }

        public void write(OutputStream outputStream) throws IOException {
            outputStream.write(this.val$data);
        }
    }

    public static WriterCallback from(InputStream inputStream) {
        return new AnonymousClass1(inputStream);
    }

    public static WriterCallback from(byte[] bArr) {
        return new AnonymousClass2(bArr);
    }
}
