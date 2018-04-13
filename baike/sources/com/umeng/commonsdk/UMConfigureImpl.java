package com.umeng.commonsdk;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.b;
import com.umeng.commonsdk.framework.c;
import com.umeng.commonsdk.statistics.common.e;

public class UMConfigureImpl {
    private static boolean a = false;
    private static boolean b = false;

    public static void init(Context context) {
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            b(applicationContext);
            a(applicationContext);
        }
    }

    private static synchronized void a(Context context) {
        synchronized (UMConfigureImpl.class) {
            if (context != null) {
                try {
                    if (!b) {
                        Object a = b.a(context);
                        CharSequence packageName = context.getPackageName();
                        if (!(TextUtils.isEmpty(a) || TextUtils.isEmpty(packageName) || !a.equals(packageName))) {
                            new Thread(new b(context)).start();
                        }
                        b = true;
                    }
                } catch (Throwable th) {
                    com.umeng.commonsdk.proguard.b.a(context, th);
                }
            }
        }
    }

    private static synchronized void b(Context context) {
        synchronized (UMConfigureImpl.class) {
            if (context != null) {
                try {
                    if (!a) {
                        new Thread(new c(context)).start();
                        if (!com.umeng.commonsdk.internal.utils.b.a(context).a()) {
                            com.umeng.commonsdk.internal.utils.b.a(context).b();
                        }
                        a = true;
                    }
                } catch (Throwable th) {
                    e.c(c.d, "e is " + th.getMessage());
                    com.umeng.commonsdk.proguard.b.a(context, th);
                }
            }
        }
    }
}
