package cz.msebera.android.httpclient.impl.io;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.io.BufferInfo;
import cz.msebera.android.httpclient.io.HttpTransportMetrics;
import cz.msebera.android.httpclient.io.SessionOutputBuffer;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import cz.msebera.android.httpclient.util.ByteArrayBuffer;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

@NotThreadSafe
public class SessionOutputBufferImpl implements BufferInfo, SessionOutputBuffer {
    private static final byte[] a = new byte[]{(byte) 13, (byte) 10};
    private final HttpTransportMetricsImpl b;
    private final ByteArrayBuffer c;
    private final int d;
    private final CharsetEncoder e;
    private OutputStream f;
    private ByteBuffer g;

    public SessionOutputBufferImpl(HttpTransportMetricsImpl httpTransportMetricsImpl, int i, int i2, CharsetEncoder charsetEncoder) {
        Args.positive(i, "Buffer size");
        Args.notNull(httpTransportMetricsImpl, "HTTP transport metrcis");
        this.b = httpTransportMetricsImpl;
        this.c = new ByteArrayBuffer(i);
        if (i2 < 0) {
            i2 = 0;
        }
        this.d = i2;
        this.e = charsetEncoder;
    }

    public SessionOutputBufferImpl(HttpTransportMetricsImpl httpTransportMetricsImpl, int i) {
        this(httpTransportMetricsImpl, i, i, null);
    }

    public void bind(OutputStream outputStream) {
        this.f = outputStream;
    }

    public boolean isBound() {
        return this.f != null;
    }

    public int capacity() {
        return this.c.capacity();
    }

    public int length() {
        return this.c.length();
    }

    public int available() {
        return capacity() - length();
    }

    private void a(byte[] bArr, int i, int i2) throws IOException {
        Asserts.notNull(this.f, "Output stream");
        this.f.write(bArr, i, i2);
    }

    private void a() throws IOException {
        if (this.f != null) {
            this.f.flush();
        }
    }

    private void b() throws IOException {
        int length = this.c.length();
        if (length > 0) {
            a(this.c.buffer(), 0, length);
            this.c.clear();
            this.b.incrementBytesTransferred((long) length);
        }
    }

    public void flush() throws IOException {
        b();
        a();
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (bArr != null) {
            if (i2 > this.d || i2 > this.c.capacity()) {
                b();
                a(bArr, i, i2);
                this.b.incrementBytesTransferred((long) i2);
                return;
            }
            if (i2 > this.c.capacity() - this.c.length()) {
                b();
            }
            this.c.append(bArr, i, i2);
        }
    }

    public void write(byte[] bArr) throws IOException {
        if (bArr != null) {
            write(bArr, 0, bArr.length);
        }
    }

    public void write(int i) throws IOException {
        if (this.d > 0) {
            if (this.c.isFull()) {
                b();
            }
            this.c.append(i);
            return;
        }
        b();
        this.f.write(i);
    }

    public void writeLine(String str) throws IOException {
        if (str != null) {
            if (str.length() > 0) {
                if (this.e == null) {
                    for (int i = 0; i < str.length(); i++) {
                        write(str.charAt(i));
                    }
                } else {
                    a(CharBuffer.wrap(str));
                }
            }
            write(a);
        }
    }

    public void writeLine(CharArrayBuffer charArrayBuffer) throws IOException {
        int i = 0;
        if (charArrayBuffer != null) {
            if (this.e == null) {
                int length = charArrayBuffer.length();
                while (length > 0) {
                    int min = Math.min(this.c.capacity() - this.c.length(), length);
                    if (min > 0) {
                        this.c.append(charArrayBuffer, i, min);
                    }
                    if (this.c.isFull()) {
                        b();
                    }
                    i += min;
                    length -= min;
                }
            } else {
                a(CharBuffer.wrap(charArrayBuffer.buffer(), 0, charArrayBuffer.length()));
            }
            write(a);
        }
    }

    private void a(CharBuffer charBuffer) throws IOException {
        if (charBuffer.hasRemaining()) {
            if (this.g == null) {
                this.g = ByteBuffer.allocate(1024);
            }
            this.e.reset();
            while (charBuffer.hasRemaining()) {
                a(this.e.encode(charBuffer, this.g, true));
            }
            a(this.e.flush(this.g));
            this.g.clear();
        }
    }

    private void a(CoderResult coderResult) throws IOException {
        if (coderResult.isError()) {
            coderResult.throwException();
        }
        this.g.flip();
        while (this.g.hasRemaining()) {
            write(this.g.get());
        }
        this.g.compact();
    }

    public HttpTransportMetrics getMetrics() {
        return this.b;
    }
}
