package okio;

import java.io.InputStream;

class Buffer$2 extends InputStream {
    final /* synthetic */ Buffer this$0;

    Buffer$2(Buffer buffer) {
        this.this$0 = buffer;
    }

    public int read() {
        if (this.this$0.size > 0) {
            return this.this$0.readByte() & 255;
        }
        return -1;
    }

    public int read(byte[] bArr, int i, int i2) {
        return this.this$0.read(bArr, i, i2);
    }

    public int available() {
        return (int) Math.min(this.this$0.size, 2147483647L);
    }

    public void close() {
    }

    public String toString() {
        return this.this$0 + ".inputStream()";
    }
}
