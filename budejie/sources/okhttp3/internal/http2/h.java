package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okio.c;
import okio.d;

final class h implements Closeable {
    private static final Logger b = Logger.getLogger(c.class.getName());
    final b a = new b(this.e);
    private final d c;
    private final boolean d;
    private final c e = new c();
    private int f = 16384;
    private boolean g;

    public h(d dVar, boolean z) {
        this.c = dVar;
        this.d = z;
    }

    public synchronized void a() throws IOException {
        if (this.g) {
            throw new IOException("closed");
        } else if (this.d) {
            if (b.isLoggable(Level.FINE)) {
                b.fine(okhttp3.internal.c.a(">> CONNECTION %s", c.a.hex()));
            }
            this.c.c(c.a.toByteArray());
            this.c.flush();
        }
    }

    public synchronized void a(l lVar) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        this.f = lVar.d(this.f);
        if (lVar.c() != -1) {
            this.a.a(lVar.c());
        }
        a(0, 0, (byte) 4, (byte) 1);
        this.c.flush();
    }

    public synchronized void a(int i, int i2, List<a> list) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        this.a.a((List) list);
        long b = this.e.b();
        int min = (int) Math.min((long) (this.f - 4), b);
        a(i, min + 4, (byte) 5, b == ((long) min) ? (byte) 4 : (byte) 0);
        this.c.g(Integer.MAX_VALUE & i2);
        this.c.a_(this.e, (long) min);
        if (b > ((long) min)) {
            b(i, b - ((long) min));
        }
    }

    public synchronized void b() throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        this.c.flush();
    }

    public synchronized void a(boolean z, int i, int i2, List<a> list) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        a(z, i, (List) list);
    }

    public synchronized void a(int i, ErrorCode errorCode) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        } else if (errorCode.httpCode == -1) {
            throw new IllegalArgumentException();
        } else {
            a(i, 4, (byte) 3, (byte) 0);
            this.c.g(errorCode.httpCode);
            this.c.flush();
        }
    }

    public int c() {
        return this.f;
    }

    public synchronized void a(boolean z, int i, c cVar, int i2) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        byte b = (byte) 0;
        if (z) {
            b = (byte) 1;
        }
        a(i, b, cVar, i2);
    }

    void a(int i, byte b, c cVar, int i2) throws IOException {
        a(i, i2, (byte) 0, b);
        if (i2 > 0) {
            this.c.a_(cVar, (long) i2);
        }
    }

    public synchronized void b(l lVar) throws IOException {
        synchronized (this) {
            if (this.g) {
                throw new IOException("closed");
            }
            a(0, lVar.b() * 6, (byte) 4, (byte) 0);
            for (int i = 0; i < 10; i++) {
                if (lVar.a(i)) {
                    int i2;
                    if (i == 4) {
                        i2 = 3;
                    } else if (i == 7) {
                        i2 = 4;
                    } else {
                        i2 = i;
                    }
                    this.c.h(i2);
                    this.c.g(lVar.b(i));
                }
            }
            this.c.flush();
        }
    }

    public synchronized void a(boolean z, int i, int i2) throws IOException {
        byte b = (byte) 0;
        synchronized (this) {
            if (this.g) {
                throw new IOException("closed");
            }
            if (z) {
                b = (byte) 1;
            }
            a(0, 8, (byte) 6, b);
            this.c.g(i);
            this.c.g(i2);
            this.c.flush();
        }
    }

    public synchronized void a(int i, ErrorCode errorCode, byte[] bArr) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        } else if (errorCode.httpCode == -1) {
            throw c.a("errorCode.httpCode == -1", new Object[0]);
        } else {
            a(0, bArr.length + 8, (byte) 7, (byte) 0);
            this.c.g(i);
            this.c.g(errorCode.httpCode);
            if (bArr.length > 0) {
                this.c.c(bArr);
            }
            this.c.flush();
        }
    }

    public synchronized void a(int i, long j) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        } else if (j == 0 || j > 2147483647L) {
            throw c.a("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", Long.valueOf(j));
        } else {
            a(i, 4, (byte) 8, (byte) 0);
            this.c.g((int) j);
            this.c.flush();
        }
    }

    public void a(int i, int i2, byte b, byte b2) throws IOException {
        if (b.isLoggable(Level.FINE)) {
            b.fine(c.a(false, i, i2, b, b2));
        }
        if (i2 > this.f) {
            throw c.a("FRAME_SIZE_ERROR length > %d: %d", Integer.valueOf(this.f), Integer.valueOf(i2));
        } else if ((Integer.MIN_VALUE & i) != 0) {
            throw c.a("reserved bit set: %s", Integer.valueOf(i));
        } else {
            a(this.c, i2);
            this.c.i(b & 255);
            this.c.i(b2 & 255);
            this.c.g(Integer.MAX_VALUE & i);
        }
    }

    public synchronized void close() throws IOException {
        this.g = true;
        this.c.close();
    }

    private static void a(d dVar, int i) throws IOException {
        dVar.i((i >>> 16) & 255);
        dVar.i((i >>> 8) & 255);
        dVar.i(i & 255);
    }

    private void b(int i, long j) throws IOException {
        while (j > 0) {
            int min = (int) Math.min((long) this.f, j);
            j -= (long) min;
            a(i, min, (byte) 9, j == 0 ? (byte) 4 : (byte) 0);
            this.c.a_(this.e, (long) min);
        }
    }

    void a(boolean z, int i, List<a> list) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        this.a.a((List) list);
        long b = this.e.b();
        int min = (int) Math.min((long) this.f, b);
        byte b2 = b == ((long) min) ? (byte) 4 : (byte) 0;
        if (z) {
            b2 = (byte) (b2 | 1);
        }
        a(i, min, (byte) 1, b2);
        this.c.a_(this.e, (long) min);
        if (b > ((long) min)) {
            b(i, b - ((long) min));
        }
    }
}
