package com.baidu.mobstat;

import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;

public final class da {
    public static boolean a(InputStream inputStream, OutputStream outputStream) {
        if (inputStream == null || outputStream == null) {
            return false;
        }
        byte[] bArr = new byte[4048];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return true;
                }
                outputStream.write(bArr, 0, read);
            } catch (Throwable e) {
                db.b(e);
                return false;
            }
        }
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
                db.b(th);
            }
        }
    }
}
