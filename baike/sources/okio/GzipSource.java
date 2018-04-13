package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

public final class GzipSource implements Source {
    private int a = 0;
    private final BufferedSource b;
    private final Inflater c;
    private final InflaterSource d;
    private final CRC32 e = new CRC32();

    public GzipSource(Source source) {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        this.c = new Inflater(true);
        this.b = Okio.buffer(source);
        this.d = new InflaterSource(this.b, this.c);
    }

    public long read(Buffer buffer, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (j == 0) {
            return 0;
        } else {
            if (this.a == 0) {
                a();
                this.a = 1;
            }
            if (this.a == 1) {
                long j2 = buffer.b;
                long read = this.d.read(buffer, j);
                if (read != -1) {
                    a(buffer, j2, read);
                    return read;
                }
                this.a = 2;
            }
            if (this.a == 2) {
                b();
                this.a = 3;
                if (!this.b.exhausted()) {
                    throw new IOException("gzip finished without exhausting source");
                }
            }
            return -1;
        }
    }

    private void a() throws IOException {
        Object obj;
        long indexOf;
        this.b.require(10);
        byte b = this.b.buffer().getByte(3);
        if (((b >> 1) & 1) == 1) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            a(this.b.buffer(), 0, 10);
        }
        a("ID1ID2", 8075, this.b.readShort());
        this.b.skip(8);
        if (((b >> 2) & 1) == 1) {
            this.b.require(2);
            if (obj != null) {
                a(this.b.buffer(), 0, 2);
            }
            short readShortLe = this.b.buffer().readShortLe();
            this.b.require((long) readShortLe);
            if (obj != null) {
                a(this.b.buffer(), 0, (long) readShortLe);
            }
            this.b.skip((long) readShortLe);
        }
        if (((b >> 3) & 1) == 1) {
            indexOf = this.b.indexOf((byte) 0);
            if (indexOf == -1) {
                throw new EOFException();
            }
            if (obj != null) {
                a(this.b.buffer(), 0, 1 + indexOf);
            }
            this.b.skip(1 + indexOf);
        }
        if (((b >> 4) & 1) == 1) {
            indexOf = this.b.indexOf((byte) 0);
            if (indexOf == -1) {
                throw new EOFException();
            }
            if (obj != null) {
                a(this.b.buffer(), 0, 1 + indexOf);
            }
            this.b.skip(1 + indexOf);
        }
        if (obj != null) {
            a("FHCRC", this.b.readShortLe(), (short) ((int) this.e.getValue()));
            this.e.reset();
        }
    }

    private void b() throws IOException {
        a("CRC", this.b.readIntLe(), (int) this.e.getValue());
        a("ISIZE", this.b.readIntLe(), (int) this.c.getBytesWritten());
    }

    public Timeout timeout() {
        return this.b.timeout();
    }

    public void close() throws IOException {
        this.d.close();
    }

    private void a(Buffer buffer, long j, long j2) {
        n nVar = buffer.a;
        while (j >= ((long) (nVar.c - nVar.b))) {
            j -= (long) (nVar.c - nVar.b);
            nVar = nVar.f;
        }
        while (j2 > 0) {
            int i = (int) (((long) nVar.b) + j);
            int min = (int) Math.min((long) (nVar.c - i), j2);
            this.e.update(nVar.a, i, min);
            j2 -= (long) min;
            nVar = nVar.f;
            j = 0;
        }
    }

    private void a(String str, int i, int i2) throws IOException {
        if (i2 != i) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", new Object[]{str, Integer.valueOf(i2), Integer.valueOf(i)}));
        }
    }
}
