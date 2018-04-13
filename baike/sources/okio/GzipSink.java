package okio;

import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Deflater;

public final class GzipSink implements Sink {
    private final BufferedSink a;
    private final Deflater b;
    private final DeflaterSink c;
    private boolean d;
    private final CRC32 e = new CRC32();

    public GzipSink(Sink sink) {
        if (sink == null) {
            throw new IllegalArgumentException("sink == null");
        }
        this.b = new Deflater(-1, true);
        this.a = Okio.buffer(sink);
        this.c = new DeflaterSink(this.a, this.b);
        a();
    }

    public void write(Buffer buffer, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (j != 0) {
            a(buffer, j);
            this.c.write(buffer, j);
        }
    }

    public void flush() throws IOException {
        this.c.flush();
    }

    public Timeout timeout() {
        return this.a.timeout();
    }

    public void close() throws IOException {
        Throwable th;
        if (!this.d) {
            Throwable th2 = null;
            try {
                this.c.a();
                b();
            } catch (Throwable th3) {
                th2 = th3;
            }
            try {
                this.b.end();
                th3 = th2;
            } catch (Throwable th4) {
                th3 = th4;
                if (th2 != null) {
                    th3 = th2;
                }
            }
            try {
                this.a.close();
            } catch (Throwable th22) {
                if (th3 == null) {
                    th3 = th22;
                }
            }
            this.d = true;
            if (th3 != null) {
                r.sneakyRethrow(th3);
            }
        }
    }

    public Deflater deflater() {
        return this.b;
    }

    private void a() {
        Buffer buffer = this.a.buffer();
        buffer.writeShort(8075);
        buffer.writeByte(8);
        buffer.writeByte(0);
        buffer.writeInt(0);
        buffer.writeByte(0);
        buffer.writeByte(0);
    }

    private void b() throws IOException {
        this.a.writeIntLe((int) this.e.getValue());
        this.a.writeIntLe((int) this.b.getBytesRead());
    }

    private void a(Buffer buffer, long j) {
        n nVar = buffer.a;
        while (j > 0) {
            int min = (int) Math.min(j, (long) (nVar.c - nVar.b));
            this.e.update(nVar.a, nVar.b, min);
            j -= (long) min;
            nVar = nVar.f;
        }
    }
}
