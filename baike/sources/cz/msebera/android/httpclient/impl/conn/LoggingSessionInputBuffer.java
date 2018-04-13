package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.Consts;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.io.EofSensor;
import cz.msebera.android.httpclient.io.HttpTransportMetrics;
import cz.msebera.android.httpclient.io.SessionInputBuffer;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.io.IOException;

@Immutable
@Deprecated
public class LoggingSessionInputBuffer implements EofSensor, SessionInputBuffer {
    private final SessionInputBuffer a;
    private final EofSensor b;
    private final Wire c;
    private final String d;

    public LoggingSessionInputBuffer(SessionInputBuffer sessionInputBuffer, Wire wire, String str) {
        this.a = sessionInputBuffer;
        this.b = sessionInputBuffer instanceof EofSensor ? (EofSensor) sessionInputBuffer : null;
        this.c = wire;
        if (str == null) {
            str = Consts.ASCII.name();
        }
        this.d = str;
    }

    public LoggingSessionInputBuffer(SessionInputBuffer sessionInputBuffer, Wire wire) {
        this(sessionInputBuffer, wire, null);
    }

    public boolean isDataAvailable(int i) throws IOException {
        return this.a.isDataAvailable(i);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.a.read(bArr, i, i2);
        if (this.c.enabled() && read > 0) {
            this.c.input(bArr, i, read);
        }
        return read;
    }

    public int read() throws IOException {
        int read = this.a.read();
        if (this.c.enabled() && read != -1) {
            this.c.input(read);
        }
        return read;
    }

    public int read(byte[] bArr) throws IOException {
        int read = this.a.read(bArr);
        if (this.c.enabled() && read > 0) {
            this.c.input(bArr, 0, read);
        }
        return read;
    }

    public String readLine() throws IOException {
        String readLine = this.a.readLine();
        if (this.c.enabled() && readLine != null) {
            this.c.input((readLine + "\r\n").getBytes(this.d));
        }
        return readLine;
    }

    public int readLine(CharArrayBuffer charArrayBuffer) throws IOException {
        int readLine = this.a.readLine(charArrayBuffer);
        if (this.c.enabled() && readLine >= 0) {
            this.c.input((new String(charArrayBuffer.buffer(), charArrayBuffer.length() - readLine, readLine) + "\r\n").getBytes(this.d));
        }
        return readLine;
    }

    public HttpTransportMetrics getMetrics() {
        return this.a.getMetrics();
    }

    public boolean isEof() {
        if (this.b != null) {
            return this.b.isEof();
        }
        return false;
    }
}
