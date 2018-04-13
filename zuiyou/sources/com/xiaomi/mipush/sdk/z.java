package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.b;

public class z {
    private static volatile d a = null;

    public static d a(Context context) {
        if (v.HUAWEI.equals(ak.a(context)) && ak.a()) {
            if (!ak.b(context)) {
                if (a != null) {
                    a.b();
                    a = null;
                }
                return a;
            } else if (a == null) {
                synchronized (z.class) {
                    if (a == null) {
                        try {
                            a = (d) Class.forName("com.xiaomi.assemble.control.HmsPushManager").getMethod("getInstance", new Class[]{Context.class}).invoke(null, new Object[]{context});
                        } catch (Throwable th) {
                            b.d(th.toString());
                            return null;
                        }
                    }
                }
            }
        }
        return a;
    }
}
