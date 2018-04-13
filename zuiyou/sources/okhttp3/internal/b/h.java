package okhttp3.internal.b;

import okhttp3.ab;
import okhttp3.s;
import okhttp3.u;
import okio.BufferedSource;

public final class h extends ab {
    private final s a;
    private final BufferedSource b;

    public h(s sVar, BufferedSource bufferedSource) {
        this.a = sVar;
        this.b = bufferedSource;
    }

    public u contentType() {
        String a = this.a.a("Content-Type");
        return a != null ? u.a(a) : null;
    }

    public long contentLength() {
        return e.a(this.a);
    }

    public BufferedSource source() {
        return this.b;
    }
}
