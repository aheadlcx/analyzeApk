package okhttp3.internal.ws;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.util.Random;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.Sink;
import okio.Timeout;

final class c {
    final boolean a;
    final Random b;
    final BufferedSink c;
    boolean d;
    final Buffer e = new Buffer();
    final a f = new a(this);
    boolean g;
    final byte[] h;
    final byte[] i;

    final class a implements Sink {
        int a;
        long b;
        boolean c;
        boolean d;
        final /* synthetic */ c e;

        a(c cVar) {
            this.e = cVar;
        }

        public void write(Buffer buffer, long j) throws IOException {
            if (this.d) {
                throw new IOException("closed");
            }
            this.e.e.write(buffer, j);
            boolean z = this.c && this.b != -1 && this.e.e.size() > this.b - PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            long completeSegmentByteCount = this.e.e.completeSegmentByteCount();
            if (completeSegmentByteCount > 0 && !z) {
                this.e.a(this.a, completeSegmentByteCount, this.c, false);
                this.c = false;
            }
        }

        public void flush() throws IOException {
            if (this.d) {
                throw new IOException("closed");
            }
            this.e.a(this.a, this.e.e.size(), this.c, false);
            this.c = false;
        }

        public Timeout timeout() {
            return this.e.c.timeout();
        }

        public void close() throws IOException {
            if (this.d) {
                throw new IOException("closed");
            }
            this.e.a(this.a, this.e.e.size(), this.c, true);
            this.d = true;
            this.e.g = false;
        }
    }

    c(boolean z, BufferedSink bufferedSink, Random random) {
        byte[] bArr = null;
        if (bufferedSink == null) {
            throw new NullPointerException("sink == null");
        } else if (random == null) {
            throw new NullPointerException("random == null");
        } else {
            byte[] bArr2;
            this.a = z;
            this.c = bufferedSink;
            this.b = random;
            if (z) {
                bArr2 = new byte[4];
            } else {
                bArr2 = null;
            }
            this.h = bArr2;
            if (z) {
                bArr = new byte[8192];
            }
            this.i = bArr;
        }
    }

    void a(ByteString byteString) throws IOException {
        b(9, byteString);
    }

    void b(ByteString byteString) throws IOException {
        b(10, byteString);
    }

    void a(int i, ByteString byteString) throws IOException {
        ByteString byteString2 = ByteString.EMPTY;
        if (!(i == 0 && byteString == null)) {
            if (i != 0) {
                WebSocketProtocol.b(i);
            }
            Buffer buffer = new Buffer();
            buffer.writeShort(i);
            if (byteString != null) {
                buffer.write(byteString);
            }
            byteString2 = buffer.readByteString();
        }
        try {
            b(8, byteString2);
        } finally {
            this.d = true;
        }
    }

    private void b(int i, ByteString byteString) throws IOException {
        if (this.d) {
            throw new IOException("closed");
        }
        int size = byteString.size();
        if (((long) size) > 125) {
            throw new IllegalArgumentException("Payload size must be less than or equal to 125");
        }
        this.c.writeByte(i | 128);
        if (this.a) {
            this.c.writeByte(size | 128);
            this.b.nextBytes(this.h);
            this.c.write(this.h);
            byte[] toByteArray = byteString.toByteArray();
            WebSocketProtocol.a(toByteArray, (long) toByteArray.length, this.h, 0);
            this.c.write(toByteArray);
        } else {
            this.c.writeByte(size);
            this.c.write(byteString);
        }
        this.c.flush();
    }

    Sink a(int i, long j) {
        if (this.g) {
            throw new IllegalStateException("Another message writer is active. Did you call close()?");
        }
        this.g = true;
        this.f.a = i;
        this.f.b = j;
        this.f.c = true;
        this.f.d = false;
        return this.f;
    }

    void a(int i, long j, boolean z, boolean z2) throws IOException {
        if (this.d) {
            throw new IOException("closed");
        }
        int i2 = z ? i : 0;
        if (z2) {
            i2 |= 128;
        }
        this.c.writeByte(i2);
        if (this.a) {
            i2 = 128;
        } else {
            i2 = 0;
        }
        if (j <= 125) {
            this.c.writeByte(i2 | ((int) j));
        } else if (j <= 65535) {
            this.c.writeByte(i2 | 126);
            this.c.writeShort((int) j);
        } else {
            this.c.writeByte(i2 | 127);
            this.c.writeLong(j);
        }
        if (this.a) {
            this.b.nextBytes(this.h);
            this.c.write(this.h);
            long j2 = 0;
            while (j2 < j) {
                int read = this.e.read(this.i, 0, (int) Math.min(j, (long) this.i.length));
                if (read == -1) {
                    throw new AssertionError();
                }
                WebSocketProtocol.a(this.i, (long) read, this.h, j2);
                this.c.write(this.i, 0, read);
                j2 += (long) read;
            }
        } else {
            this.c.write(this.e, j);
        }
        this.c.emit();
    }
}
