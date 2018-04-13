package okio;

import java.io.OutputStream;

class Buffer$1 extends OutputStream {
    final /* synthetic */ Buffer this$0;

    Buffer$1(Buffer buffer) {
        this.this$0 = buffer;
    }

    public void write(int i) {
        this.this$0.writeByte((byte) i);
    }

    public void write(byte[] bArr, int i, int i2) {
        this.this$0.write(bArr, i, i2);
    }

    public void flush() {
    }

    public void close() {
    }

    public String toString() {
        return this.this$0 + ".outputStream()";
    }
}
