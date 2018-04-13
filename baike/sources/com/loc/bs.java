package com.loc;

import android.content.Context;
import android.os.Build;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import qsbk.app.im.TimeUtils;

public final class bs {
    private static boolean a = true;

    public static void a(Context context) {
        try {
            if (c(context)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date()));
                stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                stringBuffer.append(UUID.randomUUID().toString());
                stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                if (stringBuffer.length() == 53) {
                    Object a = t.a(stringBuffer.toString());
                    Object b = b(context);
                    Object a2 = bt.a(context, x.g);
                    byte[] bArr = new byte[(b.length + a2.length)];
                    System.arraycopy(b, 0, bArr, 0, b.length);
                    System.arraycopy(a2, 0, bArr, b.length, a2.length);
                    b = a(bArr);
                    byte[] bArr2 = new byte[(a.length + b.length)];
                    System.arraycopy(a, 0, bArr2, 0, a.length);
                    System.arraycopy(b, 0, bArr2, a.length, b.length);
                    bn yVar = new y(t.c(bArr2), "2");
                    bi.a();
                    bi.a(yVar);
                }
            }
        } catch (Throwable th) {
            w.a(th, "StatisticsManager", "updateStaticsData");
        }
    }

    public static synchronized void a(br brVar, Context context) {
        synchronized (bs.class) {
            z.b().submit(new dq(context, brVar));
        }
    }

    public static synchronized void a(List<br> list, Context context) {
        synchronized (bs.class) {
            z.b().submit(new dr(list, context));
        }
    }

    private static byte[] a(byte[] bArr) {
        try {
            return m.a(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private static byte[] b(Context context) {
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[0];
        try {
            t.a(byteArrayOutputStream, "1.2.13.6");
            t.a(byteArrayOutputStream, "Android");
            t.a(byteArrayOutputStream, n.q(context));
            t.a(byteArrayOutputStream, n.i(context));
            t.a(byteArrayOutputStream, n.f(context));
            t.a(byteArrayOutputStream, Build.MANUFACTURER);
            t.a(byteArrayOutputStream, Build.MODEL);
            t.a(byteArrayOutputStream, Build.DEVICE);
            t.a(byteArrayOutputStream, n.r(context));
            t.a(byteArrayOutputStream, k.c(context));
            t.a(byteArrayOutputStream, k.d(context));
            t.a(byteArrayOutputStream, k.f(context));
            byteArrayOutputStream.write(new byte[]{(byte) 0});
            bArr = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th = th2;
                th.printStackTrace();
                return bArr;
            }
        } catch (Throwable th3) {
            th = th3;
            th.printStackTrace();
        }
        return bArr;
    }

    private static boolean c(Context context) {
        try {
            if (n.m(context) != 1 || !a || bt.b(context, x.g) < 30) {
                return false;
            }
            long c = bt.c(context, "c.log");
            long time = new Date().getTime();
            if (time - c < TimeUtils.HOUR) {
                return false;
            }
            bt.a(context, time, "c.log");
            a = false;
            return true;
        } catch (Throwable th) {
            w.a(th, "StatisticsManager", "isUpdate");
            return false;
        }
    }
}
