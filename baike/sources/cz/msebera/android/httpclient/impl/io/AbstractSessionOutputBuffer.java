package cz.msebera.android.httpclient.impl.io;

import cz.msebera.android.httpclient.Consts;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.io.BufferInfo;
import cz.msebera.android.httpclient.io.HttpTransportMetrics;
import cz.msebera.android.httpclient.io.SessionOutputBuffer;
import cz.msebera.android.httpclient.params.CoreConnectionPNames;
import cz.msebera.android.httpclient.params.CoreProtocolPNames;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.ByteArrayBuffer;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;

@NotThreadSafe
@Deprecated
public abstract class AbstractSessionOutputBuffer implements BufferInfo, SessionOutputBuffer {
    private static final byte[] a = new byte[]{(byte) 13, (byte) 10};
    private OutputStream b;
    private ByteArrayBuffer c;
    private Charset d;
    private boolean e;
    private int f;
    private HttpTransportMetricsImpl g;
    private CodingErrorAction h;
    private CodingErrorAction i;
    private CharsetEncoder j;
    private ByteBuffer k;

    protected void a(OutputStream outputStream, int i, HttpParams httpParams) {
        Args.notNull(outputStream, "Input stream");
        Args.notNegative(i, "Buffer size");
        Args.notNull(httpParams, "HTTP parameters");
        this.b = outputStream;
        this.c = new ByteArrayBuffer(i);
        String str = (String) httpParams.getParameter(CoreProtocolPNames.HTTP_ELEMENT_CHARSET);
        this.d = str != null ? Charset.forName(str) : Consts.ASCII;
        this.e = this.d.equals(Consts.ASCII);
        this.j = null;
        this.f = httpParams.getIntParameter(CoreConnectionPNames.MIN_CHUNK_LIMIT, 512);
        this.g = a();
        CodingErrorAction codingErrorAction = (CodingErrorAction) httpParams.getParameter(CoreProtocolPNames.HTTP_MALFORMED_INPUT_ACTION);
        if (codingErrorAction == null) {
            codingErrorAction = CodingErrorAction.REPORT;
        }
        this.h = codingErrorAction;
        codingErrorAction = (CodingErrorAction) httpParams.getParameter(CoreProtocolPNames.HTTP_UNMAPPABLE_INPUT_ACTION);
        if (codingErrorAction == null) {
            codingErrorAction = CodingErrorAction.REPORT;
        }
        this.i = codingErrorAction;
    }

    protected HttpTransportMetricsImpl a() {
        return new HttpTransportMetricsImpl();
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

    protected void b() throws IOException {
        int length = this.c.length();
        if (length > 0) {
            this.b.write(this.c.buffer(), 0, length);
            this.c.clear();
            this.g.incrementBytesTransferred((long) length);
        }
    }

    public void flush() throws IOException {
        b();
        this.b.flush();
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (bArr != null) {
            if (i2 > this.f || i2 > this.c.capacity()) {
                b();
                this.b.write(bArr, i, i2);
                this.g.incrementBytesTransferred((long) i2);
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
        if (this.c.isFull()) {
            b();
        }
        this.c.append(i);
    }

    public void writeLine(String str) throws IOException {
        if (str != null) {
            if (str.length() > 0) {
                if (this.e) {
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
            if (this.e) {
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
            if (this.j == null) {
                this.j = this.d.newEncoder();
                this.j.onMalformedInput(this.h);
                this.j.onUnmappableCharacter(this.i);
            }
            if (this.k == null) {
                this.k = ByteBuffer.allocate(1024);
            }
            this.j.reset();
            while (charBuffer.hasRemaining()) {
                a(this.j.encode(charBuffer, this.k, true));
            }
            a(this.j.flush(this.k));
            this.k.clear();
        }
    }

    private void a(CoderResult coderResult) throws IOException {
        if (coderResult.isError()) {
            coderResult.throwException();
        }
        this.k.flip();
        while (this.k.hasRemaining()) {
            write(this.k.get());
        }
        this.k.compact();
    }

    public HttpTransportMetrics getMetrics() {
        return this.g;
    }
}
