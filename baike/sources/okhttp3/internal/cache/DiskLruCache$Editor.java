package okhttp3.internal.cache;

import java.io.FileNotFoundException;
import java.io.IOException;
import okio.Okio;
import okio.Sink;
import okio.Source;

public final class DiskLruCache$Editor {
    final DiskLruCache$a a;
    final boolean[] b;
    final /* synthetic */ DiskLruCache c;
    private boolean d;

    DiskLruCache$Editor(DiskLruCache diskLruCache, DiskLruCache$a diskLruCache$a) {
        this.c = diskLruCache;
        this.a = diskLruCache$a;
        this.b = diskLruCache$a.e ? null : new boolean[diskLruCache.d];
    }

    void a() {
        if (this.a.f == this) {
            for (int i = 0; i < this.c.d; i++) {
                try {
                    this.c.b.delete(this.a.d[i]);
                } catch (IOException e) {
                }
            }
            this.a.f = null;
        }
    }

    public Source newSource(int i) {
        Source source = null;
        synchronized (this.c) {
            if (this.d) {
                throw new IllegalStateException();
            } else if (this.a.e && this.a.f == this) {
                try {
                    source = this.c.b.source(this.a.c[i]);
                } catch (FileNotFoundException e) {
                }
            }
        }
        return source;
    }

    public Sink newSink(int i) {
        Sink blackhole;
        synchronized (this.c) {
            if (this.d) {
                throw new IllegalStateException();
            } else if (this.a.f != this) {
                blackhole = Okio.blackhole();
            } else {
                if (!this.a.e) {
                    this.b[i] = true;
                }
                try {
                    blackhole = new e(this, this.c.b.sink(this.a.d[i]));
                } catch (FileNotFoundException e) {
                    blackhole = Okio.blackhole();
                }
            }
        }
        return blackhole;
    }

    public void commit() throws IOException {
        synchronized (this.c) {
            if (this.d) {
                throw new IllegalStateException();
            }
            if (this.a.f == this) {
                this.c.a(this, true);
            }
            this.d = true;
        }
    }

    public void abort() throws IOException {
        synchronized (this.c) {
            if (this.d) {
                throw new IllegalStateException();
            }
            if (this.a.f == this) {
                this.c.a(this, false);
            }
            this.d = true;
        }
    }

    public void abortUnlessCommitted() {
        synchronized (this.c) {
            if (!this.d && this.a.f == this) {
                try {
                    this.c.a(this, false);
                } catch (IOException e) {
                }
            }
        }
    }
}
