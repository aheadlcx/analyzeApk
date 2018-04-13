package okio;

import java.io.IOException;
import java.io.OutputStream;

class k extends OutputStream {
    final /* synthetic */ j a;

    k(j jVar) {
        this.a = jVar;
    }

    public void write(int i) throws IOException {
        if (this.a.a) {
            throw new IOException("closed");
        }
        this.a.buffer.writeByte((byte) i);
        this.a.emitCompleteSegments();
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (this.a.a) {
            throw new IOException("closed");
        }
        this.a.buffer.write(bArr, i, i2);
        this.a.emitCompleteSegments();
    }

    public void flush() throws IOException {
        if (!this.a.a) {
            this.a.flush();
        }
    }

    public void close() throws IOException {
        this.a.close();
    }

    public String toString() {
        return this.a + ".outputStream()";
    }
}
