package okhttp3.internal.cache;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Source;
import okio.Timeout;

class a implements Source {
    boolean a;
    final /* synthetic */ BufferedSource b;
    final /* synthetic */ CacheRequest c;
    final /* synthetic */ BufferedSink d;
    final /* synthetic */ CacheInterceptor e;

    a(CacheInterceptor cacheInterceptor, BufferedSource bufferedSource, CacheRequest cacheRequest, BufferedSink bufferedSink) {
        this.e = cacheInterceptor;
        this.b = bufferedSource;
        this.c = cacheRequest;
        this.d = bufferedSink;
    }

    public long read(Buffer buffer, long j) throws IOException {
        try {
            long read = this.b.read(buffer, j);
            if (read == -1) {
                if (!this.a) {
                    this.a = true;
                    this.d.close();
                }
                return -1;
            }
            buffer.copyTo(this.d.buffer(), buffer.size() - read, read);
            this.d.emitCompleteSegments();
            return read;
        } catch (IOException e) {
            if (!this.a) {
                this.a = true;
                this.c.abort();
            }
            throw e;
        }
    }

    public Timeout timeout() {
        return this.b.timeout();
    }

    public void close() throws IOException {
        if (!(this.a || Util.discard(this, 100, TimeUnit.MILLISECONDS))) {
            this.a = true;
            this.c.abort();
        }
        this.b.close();
    }
}
