package com.google.protobuf;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

final class AbstractMessageLite$Builder$LimitedInputStream extends FilterInputStream {
    private int limit;

    AbstractMessageLite$Builder$LimitedInputStream(InputStream inputStream, int i) {
        super(inputStream);
        this.limit = i;
    }

    public int available() throws IOException {
        return Math.min(super.available(), this.limit);
    }

    public int read() throws IOException {
        if (this.limit <= 0) {
            return -1;
        }
        int read = super.read();
        if (read < 0) {
            return read;
        }
        this.limit--;
        return read;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.limit <= 0) {
            return -1;
        }
        int read = super.read(bArr, i, Math.min(i2, this.limit));
        if (read < 0) {
            return read;
        }
        this.limit -= read;
        return read;
    }

    public long skip(long j) throws IOException {
        long skip = super.skip(Math.min(j, (long) this.limit));
        if (skip >= 0) {
            this.limit = (int) (((long) this.limit) - skip);
        }
        return skip;
    }
}
