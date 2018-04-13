package cz.msebera.android.httpclient.impl.io;

import cz.msebera.android.httpclient.MessageConstraintException;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.config.MessageConstraints;
import cz.msebera.android.httpclient.io.BufferInfo;
import cz.msebera.android.httpclient.io.HttpTransportMetrics;
import cz.msebera.android.httpclient.io.SessionInputBuffer;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import cz.msebera.android.httpclient.util.ByteArrayBuffer;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

@NotThreadSafe
public class SessionInputBufferImpl implements BufferInfo, SessionInputBuffer {
    private final HttpTransportMetricsImpl a;
    private final byte[] b;
    private final ByteArrayBuffer c;
    private final int d;
    private final MessageConstraints e;
    private final CharsetDecoder f;
    private InputStream g;
    private int h;
    private int i;
    private CharBuffer j;

    public SessionInputBufferImpl(HttpTransportMetricsImpl httpTransportMetricsImpl, int i, int i2, MessageConstraints messageConstraints, CharsetDecoder charsetDecoder) {
        Args.notNull(httpTransportMetricsImpl, "HTTP transport metrcis");
        Args.positive(i, "Buffer size");
        this.a = httpTransportMetricsImpl;
        this.b = new byte[i];
        this.h = 0;
        this.i = 0;
        if (i2 < 0) {
            i2 = 512;
        }
        this.d = i2;
        if (messageConstraints == null) {
            messageConstraints = MessageConstraints.DEFAULT;
        }
        this.e = messageConstraints;
        this.c = new ByteArrayBuffer(i);
        this.f = charsetDecoder;
    }

    public SessionInputBufferImpl(HttpTransportMetricsImpl httpTransportMetricsImpl, int i) {
        this(httpTransportMetricsImpl, i, i, null, null);
    }

    public void bind(InputStream inputStream) {
        this.g = inputStream;
    }

    public boolean isBound() {
        return this.g != null;
    }

    public int capacity() {
        return this.b.length;
    }

    public int length() {
        return this.i - this.h;
    }

    public int available() {
        return capacity() - length();
    }

    private int a(byte[] bArr, int i, int i2) throws IOException {
        Asserts.notNull(this.g, "Input stream");
        return this.g.read(bArr, i, i2);
    }

    public int fillBuffer() throws IOException {
        int i;
        if (this.h > 0) {
            i = this.i - this.h;
            if (i > 0) {
                System.arraycopy(this.b, this.h, this.b, 0, i);
            }
            this.h = 0;
            this.i = i;
        }
        int i2 = this.i;
        i = a(this.b, i2, this.b.length - i2);
        if (i == -1) {
            return -1;
        }
        this.i = i2 + i;
        this.a.incrementBytesTransferred((long) i);
        return i;
    }

    public boolean hasBufferedData() {
        return this.h < this.i;
    }

    public void clear() {
        this.h = 0;
        this.i = 0;
    }

    public int read() throws IOException {
        while (!hasBufferedData()) {
            if (fillBuffer() == -1) {
                return -1;
            }
        }
        byte[] bArr = this.b;
        int i = this.h;
        this.h = i + 1;
        return bArr[i] & 255;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            return 0;
        }
        int min;
        if (hasBufferedData()) {
            min = Math.min(i2, this.i - this.h);
            System.arraycopy(this.b, this.h, bArr, i, min);
            this.h += min;
            return min;
        } else if (i2 > this.d) {
            min = a(bArr, i, i2);
            if (min <= 0) {
                return min;
            }
            this.a.incrementBytesTransferred((long) min);
            return min;
        } else {
            while (!hasBufferedData()) {
                if (fillBuffer() == -1) {
                    return -1;
                }
            }
            min = Math.min(i2, this.i - this.h);
            System.arraycopy(this.b, this.h, bArr, i, min);
            this.h += min;
            return min;
        }
    }

    public int read(byte[] bArr) throws IOException {
        if (bArr == null) {
            return 0;
        }
        return read(bArr, 0, bArr.length);
    }

    public int readLine(CharArrayBuffer charArrayBuffer) throws IOException {
        Args.notNull(charArrayBuffer, "Char array buffer");
        int maxLineLength = this.e.getMaxLineLength();
        Object obj = 1;
        int i = 0;
        while (obj != null) {
            int i2;
            int i3;
            Object obj2;
            for (i2 = this.h; i2 < this.i; i2++) {
                if (this.b[i2] == (byte) 10) {
                    i3 = i2;
                    break;
                }
            }
            i3 = -1;
            if (maxLineLength > 0) {
                if (((i3 > 0 ? i3 : this.i) + this.c.length()) - this.h >= maxLineLength) {
                    throw new MessageConstraintException("Maximum line length limit exceeded");
                }
            }
            if (i3 == -1) {
                if (hasBufferedData()) {
                    this.c.append(this.b, this.h, this.i - this.h);
                    this.h = this.i;
                }
                i2 = fillBuffer();
                if (i2 == -1) {
                    i3 = i2;
                    obj2 = null;
                } else {
                    i3 = i2;
                    obj2 = obj;
                }
            } else if (this.c.isEmpty()) {
                return a(charArrayBuffer, i3);
            } else {
                this.c.append(this.b, this.h, (i3 + 1) - this.h);
                this.h = i3 + 1;
                obj2 = null;
                i3 = i;
            }
            obj = obj2;
            i = i3;
        }
        if (i == -1 && this.c.isEmpty()) {
            return -1;
        }
        return a(charArrayBuffer);
    }

    private int a(CharArrayBuffer charArrayBuffer) throws IOException {
        int length = this.c.length();
        if (length > 0) {
            if (this.c.byteAt(length - 1) == 10) {
                length--;
            }
            if (length > 0 && this.c.byteAt(length - 1) == 13) {
                length--;
            }
        }
        if (this.f == null) {
            charArrayBuffer.append(this.c, 0, length);
        } else {
            length = a(charArrayBuffer, ByteBuffer.wrap(this.c.buffer(), 0, length));
        }
        this.c.clear();
        return length;
    }

    private int a(CharArrayBuffer charArrayBuffer, int i) throws IOException {
        int i2 = this.h;
        this.h = i + 1;
        if (i > i2 && this.b[i - 1] == (byte) 13) {
            i--;
        }
        int i3 = i - i2;
        if (this.f != null) {
            return a(charArrayBuffer, ByteBuffer.wrap(this.b, i2, i3));
        }
        charArrayBuffer.append(this.b, i2, i3);
        return i3;
    }

    private int a(CharArrayBuffer charArrayBuffer, ByteBuffer byteBuffer) throws IOException {
        int i = 0;
        if (!byteBuffer.hasRemaining()) {
            return 0;
        }
        if (this.j == null) {
            this.j = CharBuffer.allocate(1024);
        }
        this.f.reset();
        while (byteBuffer.hasRemaining()) {
            i += a(this.f.decode(byteBuffer, this.j, true), charArrayBuffer, byteBuffer);
        }
        i += a(this.f.flush(this.j), charArrayBuffer, byteBuffer);
        this.j.clear();
        return i;
    }

    private int a(CoderResult coderResult, CharArrayBuffer charArrayBuffer, ByteBuffer byteBuffer) throws IOException {
        if (coderResult.isError()) {
            coderResult.throwException();
        }
        this.j.flip();
        int remaining = this.j.remaining();
        while (this.j.hasRemaining()) {
            charArrayBuffer.append(this.j.get());
        }
        this.j.compact();
        return remaining;
    }

    public String readLine() throws IOException {
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(64);
        if (readLine(charArrayBuffer) != -1) {
            return charArrayBuffer.toString();
        }
        return null;
    }

    public boolean isDataAvailable(int i) throws IOException {
        return hasBufferedData();
    }

    public HttpTransportMetrics getMetrics() {
        return this.a;
    }
}
