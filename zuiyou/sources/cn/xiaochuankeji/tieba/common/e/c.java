package cn.xiaochuankeji.tieba.common.e;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import cn.htjyb.c.a;
import java.io.ByteArrayOutputStream;
import java.util.Random;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class c {
    private static String a;
    private static String b;

    public static String a(Context context) {
        if (a == null) {
            synchronized (c.class) {
                if (a == null) {
                    a = c(context);
                }
            }
        }
        return a;
    }

    public static String b(Context context) {
        if (b == null) {
            synchronized (c.class) {
                if (b == null) {
                    b = d(context);
                }
            }
        }
        return b;
    }

    private static String c(Context context) {
        String a = d.a(context);
        if (TextUtils.isEmpty(a)) {
            a = a.b();
            if (TextUtils.isEmpty(a)) {
                try {
                    a = e(context);
                } catch (Exception e) {
                    a = a.a();
                }
            }
        }
        d.a(context, a);
        return a;
    }

    private static String d(Context context) {
        String b = d.b(context);
        if (TextUtils.isEmpty(b)) {
            b = a.a(context);
        }
        d.b(context, b);
        return b;
    }

    private static String e(Context context) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        int nextInt = new Random().nextInt();
        byteArrayOutputStream.write(b.a(currentTimeMillis), 0, 4);
        byteArrayOutputStream.write(b.a(nextInt), 0, 4);
        byteArrayOutputStream.write(3);
        byteArrayOutputStream.write(0);
        byteArrayOutputStream.write(b.a(b.a(a.a(context))), 0, 4);
        byteArrayOutputStream.write(b.a(b.a(a(byteArrayOutputStream.toByteArray()))));
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
    }

    private static String a(byte[] bArr) throws Exception {
        Mac instance = Mac.getInstance("HmacSHA1");
        instance.init(new SecretKeySpec("833511bb5cae51a45c1b87b29406300d".getBytes(), instance.getAlgorithm()));
        return Base64.encodeToString(instance.doFinal(bArr), 2);
    }
}
