package okio;

import java.io.IOException;

public final class Pipe {
    final long a;
    final Buffer b = new Buffer();
    boolean c;
    boolean d;
    private final Sink e = new a(this);
    private final Source f = new b(this);

    final class a implements Sink {
        final Timeout a = new Timeout();
        final /* synthetic */ Pipe b;

        a(Pipe pipe) {
            this.b = pipe;
        }

        public void write(Buffer buffer, long j) throws IOException {
            synchronized (this.b.b) {
                if (this.b.c) {
                    throw new IllegalStateException("closed");
                }
                while (j > 0) {
                    if (this.b.d) {
                        throw new IOException("source is closed");
                    }
                    long size = this.b.a - this.b.b.size();
                    if (size == 0) {
                        this.a.waitUntilNotified(this.b.b);
                    } else {
                        size = Math.min(size, j);
                        this.b.b.write(buffer, size);
                        j -= size;
                        this.b.b.notifyAll();
                    }
                }
            }
        }

        public void flush() throws IOException {
            synchronized (this.b.b) {
                if (this.b.c) {
                    throw new IllegalStateException("closed");
                } else if (!this.b.d || this.b.b.size() <= 0) {
                } else {
                    throw new IOException("source is closed");
                }
            }
        }

        public void close() throws IOException {
            synchronized (this.b.b) {
                if (this.b.c) {
                } else if (!this.b.d || this.b.b.size() <= 0) {
                    this.b.c = true;
                    this.b.b.notifyAll();
                } else {
                    throw new IOException("source is closed");
                }
            }
        }

        public Timeout timeout() {
            return this.a;
        }
    }

    final class b implements Source {
        final Timeout a = new Timeout();
        final /* synthetic */ Pipe b;

        b(Pipe pipe) {
            this.b = pipe;
        }

        public long read(Buffer buffer, long j) throws IOException {
            long j2;
            synchronized (this.b.b) {
                if (this.b.d) {
                    throw new IllegalStateException("closed");
                }
                while (this.b.b.size() == 0) {
                    if (this.b.c) {
                        j2 = -1;
                        break;
                    }
                    this.a.waitUntilNotified(this.b.b);
                }
                j2 = this.b.b.read(buffer, j);
                this.b.b.notifyAll();
            }
            return j2;
        }

        public void close() throws IOException {
            synchronized (this.b.b) {
                this.b.d = true;
                this.b.b.notifyAll();
            }
        }

        public Timeout timeout() {
            return this.a;
        }
    }

    public Pipe(long j) {
        if (j < 1) {
            throw new IllegalArgumentException("maxBufferSize < 1: " + j);
        }
        this.a = j;
    }

    public Source source() {
        return this.f;
    }

    public Sink sink() {
        return this.e;
    }
}
