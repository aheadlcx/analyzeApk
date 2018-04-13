package retrofit2;

import java.io.IOException;
import okhttp3.ab;
import okhttp3.u;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;

final class h$a extends ab {
    IOException a;
    private final ab b;

    h$a(ab abVar) {
        this.b = abVar;
    }

    public u contentType() {
        return this.b.contentType();
    }

    public long contentLength() {
        return this.b.contentLength();
    }

    public BufferedSource source() {
        return Okio.buffer(new ForwardingSource(this, this.b.source()) {
            final /* synthetic */ h$a a;

            public long read(Buffer buffer, long j) throws IOException {
                try {
                    return super.read(buffer, j);
                } catch (IOException e) {
                    this.a.a = e;
                    throw e;
                }
            }
        });
    }

    public void close() {
        this.b.close();
    }

    void a() throws IOException {
        if (this.a != null) {
            throw this.a;
        }
    }
}
