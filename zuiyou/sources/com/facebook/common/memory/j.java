package com.facebook.common.memory;

import com.facebook.common.internal.g;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class j {
    private final int a;
    private final a b;

    public j(a aVar) {
        this(aVar, 16384);
    }

    public j(a aVar, int i) {
        g.a(i > 0);
        this.a = i;
        this.b = aVar;
    }

    public long a(InputStream inputStream, OutputStream outputStream) throws IOException {
        long j = 0;
        byte[] bArr = (byte[]) this.b.get(this.a);
        while (true) {
            int read = inputStream.read(bArr, 0, this.a);
            if (read == -1) {
                break;
            }
            try {
                outputStream.write(bArr, 0, read);
                j += (long) read;
            } finally {
                j = this.b;
                j.release(bArr);
            }
        }
        return j;
    }
}
