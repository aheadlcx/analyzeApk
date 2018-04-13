package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSink;

final class n implements Closeable {
    private static final Logger b = Logger.getLogger(Http2.class.getName());
    final b a = new b(this.e);
    private final BufferedSink c;
    private final boolean d;
    private final Buffer e = new Buffer();
    private int f = 16384;
    private boolean g;

    n(BufferedSink bufferedSink, boolean z) {
        this.c = bufferedSink;
        this.d = z;
    }

    public synchronized void connectionPreface() throws IOException {
        if (this.g) {
            throw new IOException("closed");
        } else if (this.d) {
            if (b.isLoggable(Level.FINE)) {
                b.fine(Util.format(">> CONNECTION %s", new Object[]{Http2.a.hex()}));
            }
            this.c.write(Http2.a.toByteArray());
            this.c.flush();
        }
    }

    public synchronized void applyAndAckSettings(Settings settings) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        this.f = settings.d(this.f);
        if (settings.c() != -1) {
            this.a.a(settings.c());
        }
        frameHeader(0, 0, (byte) 4, (byte) 1);
        this.c.flush();
    }

    public synchronized void pushPromise(int i, int i2, List<Header> list) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        this.a.a((List) list);
        long size = this.e.size();
        int min = (int) Math.min((long) (this.f - 4), size);
        frameHeader(i, min + 4, (byte) 5, size == ((long) min) ? (byte) 4 : (byte) 0);
        this.c.writeInt(Integer.MAX_VALUE & i2);
        this.c.write(this.e, (long) min);
        if (size > ((long) min)) {
            a(i, size - ((long) min));
        }
    }

    public synchronized void flush() throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        this.c.flush();
    }

    public synchronized void synStream(boolean z, int i, int i2, List<Header> list) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        a(z, i, list);
    }

    public synchronized void synReply(boolean z, int i, List<Header> list) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        a(z, i, list);
    }

    public synchronized void headers(int i, List<Header> list) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        a(false, i, list);
    }

    public synchronized void rstStream(int i, ErrorCode errorCode) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        } else if (errorCode.httpCode == -1) {
            throw new IllegalArgumentException();
        } else {
            frameHeader(i, 4, (byte) 3, (byte) 0);
            this.c.writeInt(errorCode.httpCode);
            this.c.flush();
        }
    }

    public int maxDataLength() {
        return this.f;
    }

    public synchronized void data(boolean z, int i, Buffer buffer, int i2) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        byte b = (byte) 0;
        if (z) {
            b = (byte) 1;
        }
        a(i, b, buffer, i2);
    }

    void a(int i, byte b, Buffer buffer, int i2) throws IOException {
        frameHeader(i, i2, (byte) 0, b);
        if (i2 > 0) {
            this.c.write(buffer, (long) i2);
        }
    }

    public synchronized void settings(Settings settings) throws IOException {
        synchronized (this) {
            if (this.g) {
                throw new IOException("closed");
            }
            frameHeader(0, settings.b() * 6, (byte) 4, (byte) 0);
            for (int i = 0; i < 10; i++) {
                if (settings.a(i)) {
                    int i2;
                    if (i == 4) {
                        i2 = 3;
                    } else if (i == 7) {
                        i2 = 4;
                    } else {
                        i2 = i;
                    }
                    this.c.writeShort(i2);
                    this.c.writeInt(settings.b(i));
                }
            }
            this.c.flush();
        }
    }

    public synchronized void ping(boolean z, int i, int i2) throws IOException {
        byte b = (byte) 0;
        synchronized (this) {
            if (this.g) {
                throw new IOException("closed");
            }
            if (z) {
                b = (byte) 1;
            }
            frameHeader(0, 8, (byte) 6, b);
            this.c.writeInt(i);
            this.c.writeInt(i2);
            this.c.flush();
        }
    }

    public synchronized void goAway(int i, ErrorCode errorCode, byte[] bArr) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        } else if (errorCode.httpCode == -1) {
            throw Http2.a("errorCode.httpCode == -1", new Object[0]);
        } else {
            frameHeader(0, bArr.length + 8, (byte) 7, (byte) 0);
            this.c.writeInt(i);
            this.c.writeInt(errorCode.httpCode);
            if (bArr.length > 0) {
                this.c.write(bArr);
            }
            this.c.flush();
        }
    }

    public synchronized void windowUpdate(int i, long j) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        } else if (j == 0 || j > 2147483647L) {
            throw Http2.a("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", Long.valueOf(j));
        } else {
            frameHeader(i, 4, (byte) 8, (byte) 0);
            this.c.writeInt((int) j);
            this.c.flush();
        }
    }

    public void frameHeader(int i, int i2, byte b, byte b2) throws IOException {
        if (b.isLoggable(Level.FINE)) {
            b.fine(Http2.a(false, i, i2, b, b2));
        }
        if (i2 > this.f) {
            throw Http2.a("FRAME_SIZE_ERROR length > %d: %d", Integer.valueOf(this.f), Integer.valueOf(i2));
        } else if ((Integer.MIN_VALUE & i) != 0) {
            throw Http2.a("reserved bit set: %s", Integer.valueOf(i));
        } else {
            a(this.c, i2);
            this.c.writeByte(b & 255);
            this.c.writeByte(b2 & 255);
            this.c.writeInt(Integer.MAX_VALUE & i);
        }
    }

    public synchronized void close() throws IOException {
        this.g = true;
        this.c.close();
    }

    private static void a(BufferedSink bufferedSink, int i) throws IOException {
        bufferedSink.writeByte((i >>> 16) & 255);
        bufferedSink.writeByte((i >>> 8) & 255);
        bufferedSink.writeByte(i & 255);
    }

    private void a(int i, long j) throws IOException {
        while (j > 0) {
            int min = (int) Math.min((long) this.f, j);
            j -= (long) min;
            frameHeader(i, min, (byte) 9, j == 0 ? (byte) 4 : (byte) 0);
            this.c.write(this.e, (long) min);
        }
    }

    void a(boolean z, int i, List<Header> list) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        this.a.a((List) list);
        long size = this.e.size();
        int min = (int) Math.min((long) this.f, size);
        byte b = size == ((long) min) ? (byte) 4 : (byte) 0;
        if (z) {
            b = (byte) (b | 1);
        }
        frameHeader(i, min, (byte) 1, b);
        this.c.write(this.e, (long) min);
        if (size > ((long) min)) {
            a(i, size - ((long) min));
        }
    }
}
