package cz.msebera.android.httpclient.impl.io;

import cz.msebera.android.httpclient.ConnectionClosedException;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.io.BufferInfo;
import cz.msebera.android.httpclient.io.SessionInputBuffer;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.io.InputStream;

@NotThreadSafe
public class ContentLengthInputStream extends InputStream {
    private final long a;
    private long b = 0;
    private boolean c = false;
    private SessionInputBuffer d = null;

    public ContentLengthInputStream(SessionInputBuffer sessionInputBuffer, long j) {
        this.d = (SessionInputBuffer) Args.notNull(sessionInputBuffer, "Session input buffer");
        this.a = Args.notNegative(j, "Content length");
    }

    public void close() throws IOException {
        if (!this.c) {
            try {
                if (this.b < this.a) {
                    do {
                    } while (read(new byte[2048]) >= 0);
                }
                this.c = true;
            } catch (Throwable th) {
                this.c = true;
            }
        }
    }

    public int available() throws IOException {
        if (this.d instanceof BufferInfo) {
            return Math.min(((BufferInfo) this.d).length(), (int) (this.a - this.b));
        }
        return 0;
    }

    public int read() throws IOException {
        if (this.c) {
            throw new IOException("Attempted read from closed stream.");
        } else if (this.b >= this.a) {
            return -1;
        } else {
            int read = this.d.read();
            if (read != -1) {
                this.b++;
            } else if (this.b < this.a) {
                throw new ConnectionClosedException("Premature end of Content-Length delimited message body (expected: " + this.a + "; received: " + this.b);
            }
            return read;
        }
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.c) {
            throw new IOException("Attempted read from closed stream.");
        } else if (this.b >= this.a) {
            return -1;
        } else {
            if (this.b + ((long) i2) > this.a) {
                i2 = (int) (this.a - this.b);
            }
            int read = this.d.read(bArr, i, i2);
            if (read != -1 || this.b >= this.a) {
                if (read > 0) {
                    this.b += (long) read;
                }
                return read;
            }
            throw new ConnectionClosedException("Premature end of Content-Length delimited message body (expected: " + this.a + "; received: " + this.b);
        }
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public long skip(long j) throws IOException {
        if (j <= 0) {
            return 0;
        }
        byte[] bArr = new byte[2048];
        long min = Math.min(j, this.a - this.b);
        long j2 = 0;
        while (min > 0) {
            int read = read(bArr, 0, (int) Math.min(2048, min));
            if (read == -1) {
                break;
            }
            j2 += (long) read;
            min -= (long) read;
        }
        return j2;
    }
}
