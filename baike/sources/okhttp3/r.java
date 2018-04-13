package okhttp3;

import javax.annotation.Nullable;
import okio.BufferedSource;

final class r extends ResponseBody {
    final /* synthetic */ MediaType a;
    final /* synthetic */ long b;
    final /* synthetic */ BufferedSource c;

    r(MediaType mediaType, long j, BufferedSource bufferedSource) {
        this.a = mediaType;
        this.b = j;
        this.c = bufferedSource;
    }

    @Nullable
    public MediaType contentType() {
        return this.a;
    }

    public long contentLength() {
        return this.b;
    }

    public BufferedSource source() {
        return this.c;
    }
}
