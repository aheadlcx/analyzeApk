package com.umeng.commonsdk.proguard;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class aw extends ay {
    protected InputStream a = null;
    protected OutputStream b = null;

    protected aw() {
    }

    public aw(InputStream inputStream) {
        this.a = inputStream;
    }

    public aw(OutputStream outputStream) {
        this.b = outputStream;
    }

    public aw(InputStream inputStream, OutputStream outputStream) {
        this.a = inputStream;
        this.b = outputStream;
    }

    public boolean a() {
        return true;
    }

    public void b() throws az {
    }

    public void c() {
        if (this.a != null) {
            try {
                this.a.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.a = null;
        }
        if (this.b != null) {
            try {
                this.b.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            this.b = null;
        }
    }

    public int a(byte[] bArr, int i, int i2) throws az {
        if (this.a == null) {
            throw new az(1, "Cannot read from null inputStream");
        }
        try {
            int read = this.a.read(bArr, i, i2);
            if (read >= 0) {
                return read;
            }
            throw new az(4);
        } catch (Throwable e) {
            throw new az(0, e);
        }
    }

    public void b(byte[] bArr, int i, int i2) throws az {
        if (this.b == null) {
            throw new az(1, "Cannot write to null outputStream");
        }
        try {
            this.b.write(bArr, i, i2);
        } catch (Throwable e) {
            throw new az(0, e);
        }
    }

    public void d() throws az {
        if (this.b == null) {
            throw new az(1, "Cannot flush null outputStream");
        }
        try {
            this.b.flush();
        } catch (Throwable e) {
            throw new az(0, e);
        }
    }
}
