package com.facebook.stetho.common;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import javax.annotation.Nullable;

public class ProcessUtil {
    private static final int CMDLINE_BUFFER_SIZE = 64;
    private static String sProcessName;
    private static boolean sProcessNameRead;

    @Nullable
    public static synchronized String getProcessName() {
        String str;
        synchronized (ProcessUtil.class) {
            if (!sProcessNameRead) {
                sProcessNameRead = true;
                try {
                    sProcessName = readProcessName();
                } catch (IOException e) {
                }
            }
            str = sProcessName;
        }
        return str;
    }

    private static String readProcessName() throws IOException {
        Throwable th;
        boolean z;
        boolean z2 = true;
        byte[] bArr = new byte[64];
        Closeable fileInputStream = new FileInputStream("/proc/self/cmdline");
        try {
            int read = fileInputStream.read(bArr);
            try {
                int indexOf = indexOf(bArr, 0, read, (byte) 0);
                if (indexOf <= 0) {
                    indexOf = read;
                }
                String str = new String(bArr, 0, indexOf);
                Util.close(fileInputStream, false);
                return str;
            } catch (Throwable th2) {
                th = th2;
                z = true;
                if (z) {
                    z2 = false;
                }
                Util.close(fileInputStream, z2);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            z = false;
            if (z) {
                z2 = false;
            }
            Util.close(fileInputStream, z2);
            throw th;
        }
    }

    private static int indexOf(byte[] bArr, int i, int i2, byte b) {
        for (int i3 = 0; i3 < bArr.length; i3++) {
            if (bArr[i3] == b) {
                return i3;
            }
        }
        return -1;
    }
}
