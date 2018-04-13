package okhttp3;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.Okio;

final class q extends RequestBody {
    final /* synthetic */ MediaType a;
    final /* synthetic */ File b;

    q(MediaType mediaType, File file) {
        this.a = mediaType;
        this.b = file;
    }

    @Nullable
    public MediaType contentType() {
        return this.a;
    }

    public long contentLength() {
        return this.b.length();
    }

    public void writeTo(BufferedSink bufferedSink) throws IOException {
        Closeable closeable = null;
        try {
            closeable = Okio.source(this.b);
            bufferedSink.writeAll(closeable);
        } finally {
            Util.closeQuietly(closeable);
        }
    }
}
