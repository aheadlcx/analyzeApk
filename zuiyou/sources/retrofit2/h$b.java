package retrofit2;

import okhttp3.ab;
import okhttp3.u;
import okio.BufferedSource;

final class h$b extends ab {
    private final u a;
    private final long b;

    h$b(u uVar, long j) {
        this.a = uVar;
        this.b = j;
    }

    public u contentType() {
        return this.a;
    }

    public long contentLength() {
        return this.b;
    }

    public BufferedSource source() {
        throw new IllegalStateException("Cannot read raw response body of a converted body.");
    }
}
