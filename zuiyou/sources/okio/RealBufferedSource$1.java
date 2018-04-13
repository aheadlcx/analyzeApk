package okio;

import java.io.IOException;
import java.io.InputStream;

class RealBufferedSource$1 extends InputStream {
    final /* synthetic */ RealBufferedSource this$0;

    RealBufferedSource$1(RealBufferedSource realBufferedSource) {
        this.this$0 = realBufferedSource;
    }

    public int read() throws IOException {
        if (this.this$0.closed) {
            throw new IOException("closed");
        } else if (this.this$0.buffer.size == 0 && this.this$0.source.read(this.this$0.buffer, 8192) == -1) {
            return -1;
        } else {
            return this.this$0.buffer.readByte() & 255;
        }
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.this$0.closed) {
            throw new IOException("closed");
        }
        Util.checkOffsetAndCount((long) bArr.length, (long) i, (long) i2);
        if (this.this$0.buffer.size == 0 && this.this$0.source.read(this.this$0.buffer, 8192) == -1) {
            return -1;
        }
        return this.this$0.buffer.read(bArr, i, i2);
    }

    public int available() throws IOException {
        if (!this.this$0.closed) {
            return (int) Math.min(this.this$0.buffer.size, 2147483647L);
        }
        throw new IOException("closed");
    }

    public void close() throws IOException {
        this.this$0.close();
    }

    public String toString() {
        return this.this$0 + ".inputStream()";
    }
}
