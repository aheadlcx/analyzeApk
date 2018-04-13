package org.apache.commons.codec.binary;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Base64OutputStream extends FilterOutputStream {
    private final Base64 base64;
    private final boolean doEncode;
    private final byte[] singleByte;

    public Base64OutputStream(OutputStream outputStream) {
        this(outputStream, true);
    }

    public Base64OutputStream(OutputStream outputStream, boolean z) {
        super(outputStream);
        this.singleByte = new byte[1];
        this.doEncode = z;
        this.base64 = new Base64();
    }

    public Base64OutputStream(OutputStream outputStream, boolean z, int i, byte[] bArr) {
        super(outputStream);
        this.singleByte = new byte[1];
        this.doEncode = z;
        this.base64 = new Base64(i, bArr);
    }

    public void write(int i) throws IOException {
        this.singleByte[0] = (byte) i;
        write(this.singleByte, 0, 1);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            throw new NullPointerException();
        } else if (i < 0 || i2 < 0) {
            throw new IndexOutOfBoundsException();
        } else if (i > bArr.length || i + i2 > bArr.length) {
            throw new IndexOutOfBoundsException();
        } else if (i2 > 0) {
            if (this.doEncode) {
                this.base64.encode(bArr, i, i2);
            } else {
                this.base64.decode(bArr, i, i2);
            }
            flush(false);
        }
    }

    private void flush(boolean z) throws IOException {
        int avail = this.base64.avail();
        if (avail > 0) {
            byte[] bArr = new byte[avail];
            avail = this.base64.readResults(bArr, 0, avail);
            if (avail > 0) {
                this.out.write(bArr, 0, avail);
            }
        }
        if (z) {
            this.out.flush();
        }
    }

    public void flush() throws IOException {
        flush(true);
    }

    public void close() throws IOException {
        if (this.doEncode) {
            this.base64.encode(this.singleByte, 0, -1);
        } else {
            this.base64.decode(this.singleByte, 0, -1);
        }
        flush();
        this.out.close();
    }
}
