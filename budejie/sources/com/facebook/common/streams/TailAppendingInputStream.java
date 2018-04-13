package com.facebook.common.streams;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TailAppendingInputStream extends FilterInputStream {
    private int mMarkedTailOffset;
    private final byte[] mTail;
    private int mTailOffset;

    public TailAppendingInputStream(InputStream inputStream, byte[] bArr) {
        super(inputStream);
        if (inputStream == null) {
            throw new NullPointerException();
        } else if (bArr == null) {
            throw new NullPointerException();
        } else {
            this.mTail = bArr;
        }
    }

    public int read() throws IOException {
        int read = this.in.read();
        return read != -1 ? read : readNextTailByte();
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.in.read(bArr, i, i2);
        if (read != -1) {
            return read;
        }
        if (i2 == 0) {
            return 0;
        }
        read = 0;
        while (read < i2) {
            int readNextTailByte = readNextTailByte();
            if (readNextTailByte == -1) {
                break;
            }
            bArr[i + read] = (byte) readNextTailByte;
            read++;
        }
        return read <= 0 ? -1 : read;
    }

    public void reset() throws IOException {
        if (this.in.markSupported()) {
            this.in.reset();
            this.mTailOffset = this.mMarkedTailOffset;
            return;
        }
        throw new IOException("mark is not supported");
    }

    public void mark(int i) {
        if (this.in.markSupported()) {
            super.mark(i);
            this.mMarkedTailOffset = this.mTailOffset;
        }
    }

    private int readNextTailByte() {
        if (this.mTailOffset >= this.mTail.length) {
            return -1;
        }
        byte[] bArr = this.mTail;
        int i = this.mTailOffset;
        this.mTailOffset = i + 1;
        return bArr[i] & 255;
    }
}
