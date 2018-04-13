package org.apache.commons.httpclient;

import java.io.IOException;
import java.io.InputStream;

public class ContentLengthInputStream extends InputStream {
    private boolean closed;
    private long contentLength;
    private long pos;
    private InputStream wrappedStream;

    public ContentLengthInputStream(InputStream inputStream, int i) {
        this(inputStream, (long) i);
    }

    public ContentLengthInputStream(InputStream inputStream, long j) {
        this.pos = 0;
        this.closed = false;
        this.wrappedStream = null;
        this.wrappedStream = inputStream;
        this.contentLength = j;
    }

    public void close() throws IOException {
        if (!this.closed) {
            try {
                ChunkedInputStream.exhaustInputStream(this);
            } finally {
                this.closed = true;
            }
        }
    }

    public int read() throws IOException {
        if (this.closed) {
            throw new IOException("Attempted read from closed stream.");
        } else if (this.pos >= this.contentLength) {
            return -1;
        } else {
            this.pos++;
            return this.wrappedStream.read();
        }
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.closed) {
            throw new IOException("Attempted read from closed stream.");
        } else if (this.pos >= this.contentLength) {
            return -1;
        } else {
            if (this.pos + ((long) i2) > this.contentLength) {
                i2 = (int) (this.contentLength - this.pos);
            }
            int read = this.wrappedStream.read(bArr, i, i2);
            this.pos += (long) read;
            return read;
        }
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public long skip(long j) throws IOException {
        long skip = this.wrappedStream.skip(Math.min(j, this.contentLength - this.pos));
        if (skip > 0) {
            this.pos += skip;
        }
        return skip;
    }
}
