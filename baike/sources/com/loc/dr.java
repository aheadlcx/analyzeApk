package com.loc;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.util.List;

final class dr implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ Context b;

    dr(List list, Context context) {
        this.a = list;
        this.b = context;
    }

    public final void run() {
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] toByteArray;
        Throwable th;
        byte[] bArr = new byte[0];
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                for (br a : this.a) {
                    byteArrayOutputStream.write(a.a());
                }
                toByteArray = byteArrayOutputStream.toByteArray();
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                try {
                    w.a(th, "StatisticsEntity", "applyStaticsBatch");
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable th4) {
                            th4.printStackTrace();
                        }
                    }
                    toByteArray = bArr;
                    bt.a(this.b, x.g, toByteArray);
                } catch (Throwable th5) {
                    th4 = th5;
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable th22) {
                            th22.printStackTrace();
                        }
                    }
                    throw th4;
                }
            }
        } catch (Throwable th6) {
            th4 = th6;
            byteArrayOutputStream = null;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th4;
        }
        bt.a(this.b, x.g, toByteArray);
    }
}
