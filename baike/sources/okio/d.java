package okio;

import java.io.OutputStream;

class d extends OutputStream {
    final /* synthetic */ Buffer a;

    d(Buffer buffer) {
        this.a = buffer;
    }

    public void write(int i) {
        this.a.writeByte((byte) i);
    }

    public void write(byte[] bArr, int i, int i2) {
        this.a.write(bArr, i, i2);
    }

    public void flush() {
    }

    public void close() {
    }

    public String toString() {
        return this.a + ".outputStream()";
    }
}
