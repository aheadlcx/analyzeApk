package okhttp3;

import java.io.IOException;
import javax.annotation.Nullable;
import okio.BufferedSink;

final class p extends RequestBody {
    final /* synthetic */ MediaType a;
    final /* synthetic */ int b;
    final /* synthetic */ byte[] c;
    final /* synthetic */ int d;

    p(MediaType mediaType, int i, byte[] bArr, int i2) {
        this.a = mediaType;
        this.b = i;
        this.c = bArr;
        this.d = i2;
    }

    @Nullable
    public MediaType contentType() {
        return this.a;
    }

    public long contentLength() {
        return (long) this.b;
    }

    public void writeTo(BufferedSink bufferedSink) throws IOException {
        bufferedSink.write(this.c, this.d, this.b);
    }
}
