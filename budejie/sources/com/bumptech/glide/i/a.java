package com.bumptech.glide.i;

import android.util.Log;
import java.util.Queue;

public final class a {
    private static final a b = new a();
    private final Queue<byte[]> a = h.a(0);

    public static a a() {
        return b;
    }

    private a() {
    }

    public byte[] b() {
        byte[] bArr;
        synchronized (this.a) {
            bArr = (byte[]) this.a.poll();
        }
        if (bArr == null) {
            bArr = new byte[65536];
            if (Log.isLoggable("ByteArrayPool", 3)) {
                Log.d("ByteArrayPool", "Created temp bytes");
            }
        }
        return bArr;
    }

    public boolean a(byte[] bArr) {
        boolean z = false;
        if (bArr.length == 65536) {
            synchronized (this.a) {
                if (this.a.size() < 32) {
                    z = true;
                    this.a.offer(bArr);
                }
            }
        }
        return z;
    }
}
