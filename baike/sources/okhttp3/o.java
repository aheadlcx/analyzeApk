package okhttp3;

import java.io.IOException;
import javax.annotation.Nullable;
import okio.BufferedSink;
import okio.ByteString;

final class o extends RequestBody {
    final /* synthetic */ MediaType a;
    final /* synthetic */ ByteString b;

    o(MediaType mediaType, ByteString byteString) {
        this.a = mediaType;
        this.b = byteString;
    }

    @Nullable
    public MediaType contentType() {
        return this.a;
    }

    public long contentLength() throws IOException {
        return (long) this.b.size();
    }

    public void writeTo(BufferedSink bufferedSink) throws IOException {
        bufferedSink.write(this.b);
    }
}
