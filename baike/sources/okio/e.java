package okio;

import java.io.InputStream;

class e extends InputStream {
    final /* synthetic */ Buffer a;

    e(Buffer buffer) {
        this.a = buffer;
    }

    public int read() {
        if (this.a.b > 0) {
            return this.a.readByte() & 255;
        }
        return -1;
    }

    public int read(byte[] bArr, int i, int i2) {
        return this.a.read(bArr, i, i2);
    }

    public int available() {
        return (int) Math.min(this.a.b, 2147483647L);
    }

    public void close() {
    }

    public String toString() {
        return this.a + ".inputStream()";
    }
}
