package cz.msebera.android.httpclient.impl.io;

import cz.msebera.android.httpclient.Consts;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.io.BufferInfo;
import cz.msebera.android.httpclient.io.HttpTransportMetrics;
import cz.msebera.android.httpclient.io.SessionInputBuffer;
import cz.msebera.android.httpclient.params.CoreConnectionPNames;
import cz.msebera.android.httpclient.params.CoreProtocolPNames;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.ByteArrayBuffer;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;

@NotThreadSafe
@Deprecated
public abstract class AbstractSessionInputBuffer implements BufferInfo, SessionInputBuffer {
    private InputStream a;
    private byte[] b;
    private ByteArrayBuffer c;
    private Charset d;
    private boolean e;
    private int f;
    private int g;
    private HttpTransportMetricsImpl h;
    private CodingErrorAction i;
    private CodingErrorAction j;
    private int k;
    private int l;
    private CharsetDecoder m;
    private CharBuffer n;

    protected void a(InputStream inputStream, int i, HttpParams httpParams) {
        Args.notNull(inputStream, "Input stream");
        Args.notNegative(i, "Buffer size");
        Args.notNull(httpParams, "HTTP parameters");
        this.a = inputStream;
        this.b = new byte[i];
        this.k = 0;
        this.l = 0;
        this.c = new ByteArrayBuffer(i);
        String str = (String) httpParams.getParameter(CoreProtocolPNames.HTTP_ELEMENT_CHARSET);
        this.d = str != null ? Charset.forName(str) : Consts.ASCII;
        this.e = this.d.equals(Consts.ASCII);
        this.m = null;
        this.f = httpParams.getIntParameter(CoreConnectionPNames.MAX_LINE_LENGTH, -1);
        this.g = httpParams.getIntParameter(CoreConnectionPNames.MIN_CHUNK_LIMIT, 512);
        this.h = a();
        CodingErrorAction codingErrorAction = (CodingErrorAction) httpParams.getParameter(CoreProtocolPNames.HTTP_MALFORMED_INPUT_ACTION);
        if (codingErrorAction == null) {
            codingErrorAction = CodingErrorAction.REPORT;
        }
        this.i = codingErrorAction;
        codingErrorAction = (CodingErrorAction) httpParams.getParameter(CoreProtocolPNames.HTTP_UNMAPPABLE_INPUT_ACTION);
        if (codingErrorAction == null) {
            codingErrorAction = CodingErrorAction.REPORT;
        }
        this.j = codingErrorAction;
    }

    protected HttpTransportMetricsImpl a() {
        return new HttpTransportMetricsImpl();
    }

    public int capacity() {
        return this.b.length;
    }

    public int length() {
        return this.l - this.k;
    }

    public int available() {
        return capacity() - length();
    }

    protected int b() throws IOException {
        int i;
        if (this.k > 0) {
            i = this.l - this.k;
            if (i > 0) {
                System.arraycopy(this.b, this.k, this.b, 0, i);
            }
            this.k = 0;
            this.l = i;
        }
        int i2 = this.l;
        i = this.a.read(this.b, i2, this.b.length - i2);
        if (i == -1) {
            return -1;
        }
        this.l = i2 + i;
        this.h.incrementBytesTransferred((long) i);
        return i;
    }

    protected boolean c() {
        return this.k < this.l;
    }

    public int read() throws IOException {
        while (!c()) {
            if (b() == -1) {
                return -1;
            }
        }
        byte[] bArr = this.b;
        int i = this.k;
        this.k = i + 1;
        return bArr[i] & 255;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            return 0;
        }
        int min;
        if (c()) {
            min = Math.min(i2, this.l - this.k);
            System.arraycopy(this.b, this.k, bArr, i, min);
            this.k += min;
            return min;
        } else if (i2 > this.g) {
            min = this.a.read(bArr, i, i2);
            if (min <= 0) {
                return min;
            }
            this.h.incrementBytesTransferred((long) min);
            return min;
        } else {
            while (!c()) {
                if (b() == -1) {
                    return -1;
                }
            }
            min = Math.min(i2, this.l - this.k);
            System.arraycopy(this.b, this.k, bArr, i, min);
            this.k += min;
            return min;
        }
    }

    public int read(byte[] bArr) throws IOException {
        if (bArr == null) {
            return 0;
        }
        return read(bArr, 0, bArr.length);
    }

    private int d() {
        for (int i = this.k; i < this.l; i++) {
            if (this.b[i] == (byte) 10) {
                return i;
            }
        }
        return -1;
    }

    public int readLine(CharArrayBuffer charArrayBuffer) throws IOException {
        Args.notNull(charArrayBuffer, "Char array buffer");
        Object obj = 1;
        int i = 0;
        while (obj != null) {
            int d = d();
            if (d == -1) {
                if (c()) {
                    this.c.append(this.b, this.k, this.l - this.k);
                    this.k = this.l;
                }
                i = b();
                if (i == -1) {
                    obj = null;
                }
            } else if (this.c.isEmpty()) {
                return a(charArrayBuffer, d);
            } else {
                this.c.append(this.b, this.k, (d + 1) - this.k);
                this.k = d + 1;
                obj = null;
            }
            if (this.f > 0 && this.c.length() >= this.f) {
                throw new IOException("Maximum line length limit exceeded");
            }
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
        if (this.e) {
            charArrayBuffer.append(this.c, 0, length);
        } else {
            length = a(charArrayBuffer, ByteBuffer.wrap(this.c.buffer(), 0, length));
        }
        this.c.clear();
        return length;
    }

    private int a(CharArrayBuffer charArrayBuffer, int i) throws IOException {
        int i2 = this.k;
        this.k = i + 1;
        if (i > i2 && this.b[i - 1] == (byte) 13) {
            i--;
        }
        int i3 = i - i2;
        if (!this.e) {
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
        if (this.m == null) {
            this.m = this.d.newDecoder();
            this.m.onMalformedInput(this.i);
            this.m.onUnmappableCharacter(this.j);
        }
        if (this.n == null) {
            this.n = CharBuffer.allocate(1024);
        }
        this.m.reset();
        while (byteBuffer.hasRemaining()) {
            i += a(this.m.decode(byteBuffer, this.n, true), charArrayBuffer, byteBuffer);
        }
        i += a(this.m.flush(this.n), charArrayBuffer, byteBuffer);
        this.n.clear();
        return i;
    }

    private int a(CoderResult coderResult, CharArrayBuffer charArrayBuffer, ByteBuffer byteBuffer) throws IOException {
        if (coderResult.isError()) {
            coderResult.throwException();
        }
        this.n.flip();
        int remaining = this.n.remaining();
        while (this.n.hasRemaining()) {
            charArrayBuffer.append(this.n.get());
        }
        this.n.compact();
        return remaining;
    }

    public String readLine() throws IOException {
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(64);
        if (readLine(charArrayBuffer) != -1) {
            return charArrayBuffer.toString();
        }
        return null;
    }

    public HttpTransportMetrics getMetrics() {
        return this.h;
    }
}
