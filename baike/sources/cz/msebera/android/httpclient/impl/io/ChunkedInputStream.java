package cz.msebera.android.httpclient.impl.io;

import cz.msebera.android.httpclient.ConnectionClosedException;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.MalformedChunkCodingException;
import cz.msebera.android.httpclient.TruncatedChunkException;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.config.MessageConstraints;
import cz.msebera.android.httpclient.io.BufferInfo;
import cz.msebera.android.httpclient.io.SessionInputBuffer;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.io.IOException;
import java.io.InputStream;

@NotThreadSafe
public class ChunkedInputStream extends InputStream {
    private final SessionInputBuffer a;
    private final CharArrayBuffer b;
    private final MessageConstraints c;
    private int d;
    private int e;
    private int f;
    private boolean g;
    private boolean h;
    private Header[] i;

    public ChunkedInputStream(SessionInputBuffer sessionInputBuffer, MessageConstraints messageConstraints) {
        this.g = false;
        this.h = false;
        this.i = new Header[0];
        this.a = (SessionInputBuffer) Args.notNull(sessionInputBuffer, "Session input buffer");
        this.f = 0;
        this.b = new CharArrayBuffer(16);
        if (messageConstraints == null) {
            messageConstraints = MessageConstraints.DEFAULT;
        }
        this.c = messageConstraints;
        this.d = 1;
    }

    public ChunkedInputStream(SessionInputBuffer sessionInputBuffer) {
        this(sessionInputBuffer, null);
    }

    public int available() throws IOException {
        if (this.a instanceof BufferInfo) {
            return Math.min(((BufferInfo) this.a).length(), this.e - this.f);
        }
        return 0;
    }

    public int read() throws IOException {
        if (this.h) {
            throw new IOException("Attempted read from closed stream.");
        } else if (this.g) {
            return -1;
        } else {
            if (this.d != 2) {
                a();
                if (this.g) {
                    return -1;
                }
            }
            int read = this.a.read();
            if (read != -1) {
                this.f++;
                if (this.f >= this.e) {
                    this.d = 3;
                }
            }
            return read;
        }
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.h) {
            throw new IOException("Attempted read from closed stream.");
        } else if (this.g) {
            return -1;
        } else {
            if (this.d != 2) {
                a();
                if (this.g) {
                    return -1;
                }
            }
            int read = this.a.read(bArr, i, Math.min(i2, this.e - this.f));
            if (read != -1) {
                this.f += read;
                if (this.f >= this.e) {
                    this.d = 3;
                }
                return read;
            }
            this.g = true;
            throw new TruncatedChunkException("Truncated chunk ( expected size: " + this.e + "; actual size: " + this.f + ")");
        }
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    private void a() throws IOException {
        if (this.d == Integer.MAX_VALUE) {
            throw new MalformedChunkCodingException("Corrupt data stream");
        }
        try {
            this.e = b();
            if (this.e < 0) {
                throw new MalformedChunkCodingException("Negative chunk size");
            }
            this.d = 2;
            this.f = 0;
            if (this.e == 0) {
                this.g = true;
                c();
            }
        } catch (MalformedChunkCodingException e) {
            this.d = Integer.MAX_VALUE;
            throw e;
        }
    }

    private int b() throws IOException {
        switch (this.d) {
            case 1:
                break;
            case 3:
                this.b.clear();
                if (this.a.readLine(this.b) != -1) {
                    if (this.b.isEmpty()) {
                        this.d = 1;
                        break;
                    }
                    throw new MalformedChunkCodingException("Unexpected content at the end of chunk");
                }
                throw new MalformedChunkCodingException("CRLF expected at end of chunk");
            default:
                throw new IllegalStateException("Inconsistent codec state");
        }
        this.b.clear();
        if (this.a.readLine(this.b) == -1) {
            throw new ConnectionClosedException("Premature end of chunk coded message body: closing chunk expected");
        }
        int indexOf = this.b.indexOf(59);
        if (indexOf < 0) {
            indexOf = this.b.length();
        }
        try {
            return Integer.parseInt(this.b.substringTrimmed(0, indexOf), 16);
        } catch (NumberFormatException e) {
            throw new MalformedChunkCodingException("Bad chunk header");
        }
    }

    private void c() throws IOException {
        try {
            this.i = AbstractMessageParser.parseHeaders(this.a, this.c.getMaxHeaderCount(), this.c.getMaxLineLength(), null);
        } catch (Throwable e) {
            IOException malformedChunkCodingException = new MalformedChunkCodingException("Invalid footer: " + e.getMessage());
            malformedChunkCodingException.initCause(e);
            throw malformedChunkCodingException;
        }
    }

    public void close() throws IOException {
        if (!this.h) {
            try {
                if (this.g || this.d == Integer.MAX_VALUE) {
                    this.g = true;
                    this.h = true;
                }
                do {
                } while (read(new byte[2048]) >= 0);
                this.g = true;
                this.h = true;
            } catch (Throwable th) {
                this.g = true;
                this.h = true;
            }
        }
    }

    public Header[] getFooters() {
        return (Header[]) this.i.clone();
    }
}
