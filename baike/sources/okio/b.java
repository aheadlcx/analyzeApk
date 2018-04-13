package okio;

import java.io.IOException;

class b implements Source {
    final /* synthetic */ Source a;
    final /* synthetic */ AsyncTimeout b;

    b(AsyncTimeout asyncTimeout, Source source) {
        this.b = asyncTimeout;
        this.a = source;
    }

    public long read(Buffer buffer, long j) throws IOException {
        this.b.enter();
        try {
            long read = this.a.read(buffer, j);
            this.b.a(true);
            return read;
        } catch (IOException e) {
            throw this.b.b(e);
        } catch (Throwable th) {
            this.b.a(false);
        }
    }

    public void close() throws IOException {
        try {
            this.a.close();
            this.b.a(true);
        } catch (IOException e) {
            throw this.b.b(e);
        } catch (Throwable th) {
            this.b.a(false);
        }
    }

    public Timeout timeout() {
        return this.b;
    }

    public String toString() {
        return "AsyncTimeout.source(" + this.a + ")";
    }
}
