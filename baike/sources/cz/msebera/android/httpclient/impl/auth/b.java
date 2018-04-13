package cz.msebera.android.httpclient.impl.auth;

import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;

class b extends OutputStream {
    private final MessageDigest a;
    private boolean b;
    private byte[] c;

    b(MessageDigest messageDigest) {
        this.a = messageDigest;
        this.a.reset();
    }

    public void write(int i) throws IOException {
        if (this.b) {
            throw new IOException("Stream has been already closed");
        }
        this.a.update((byte) i);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (this.b) {
            throw new IOException("Stream has been already closed");
        }
        this.a.update(bArr, i, i2);
    }

    public void close() throws IOException {
        if (!this.b) {
            this.b = true;
            this.c = this.a.digest();
            super.close();
        }
    }

    public byte[] getDigest() {
        return this.c;
    }
}
