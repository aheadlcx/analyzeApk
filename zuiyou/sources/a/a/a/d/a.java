package a.a.a.d;

import java.io.InputStream;
import java.io.OutputStream;

public class a extends c {
    protected InputStream a = null;
    protected OutputStream b = null;

    protected a() {
    }

    public a(InputStream inputStream) {
        this.a = inputStream;
    }

    public a(OutputStream outputStream) {
        this.b = outputStream;
    }

    public int a(byte[] bArr, int i, int i2) throws d {
        if (this.a == null) {
            throw new d(1, "Cannot read from null inputStream");
        }
        try {
            int read = this.a.read(bArr, i, i2);
            if (read >= 0) {
                return read;
            }
            throw new d(4);
        } catch (Throwable e) {
            throw new d(0, e);
        }
    }

    public void b(byte[] bArr, int i, int i2) throws d {
        if (this.b == null) {
            throw new d(1, "Cannot write to null outputStream");
        }
        try {
            this.b.write(bArr, i, i2);
        } catch (Throwable e) {
            throw new d(0, e);
        }
    }
}
