package com.crashlytics.android.internal;

import java.io.IOException;
import java.io.InputStream;

final class bx extends InputStream {
    private int a;
    private int b;
    private /* synthetic */ C0010aq c;

    private bx(C0010aq c0010aq, bw bwVar) {
        this.c = c0010aq;
        this.a = c0010aq.b(bwVar.b + 4);
        this.b = bwVar.c;
    }

    public final int read(byte[] bArr, int i, int i2) throws IOException {
        C0010aq.b(bArr, "buffer");
        if ((i | i2) < 0 || i2 > bArr.length - i) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (this.b <= 0) {
            return -1;
        } else {
            if (i2 > this.b) {
                i2 = this.b;
            }
            this.c.b(this.a, bArr, i, i2);
            this.a = this.c.b(this.a + i2);
            this.b -= i2;
            return i2;
        }
    }

    public final int read() throws IOException {
        if (this.b == 0) {
            return -1;
        }
        this.c.b.seek((long) this.a);
        int read = this.c.b.read();
        this.a = this.c.b(this.a + 1);
        this.b--;
        return read;
    }
}
