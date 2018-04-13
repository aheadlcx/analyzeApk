package okhttp3.internal.http;

import javax.annotation.Nullable;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;

public final class RealResponseBody extends ResponseBody {
    @Nullable
    private final String a;
    private final long b;
    private final BufferedSource c;

    public RealResponseBody(@Nullable String str, long j, BufferedSource bufferedSource) {
        this.a = str;
        this.b = j;
        this.c = bufferedSource;
    }

    public MediaType contentType() {
        return this.a != null ? MediaType.parse(this.a) : null;
    }

    public long contentLength() {
        return this.b;
    }

    public BufferedSource source() {
        return this.c;
    }
}
