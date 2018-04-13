package com.umeng.commonsdk;

import android.content.Context;
import com.umeng.commonsdk.framework.c;
import com.umeng.commonsdk.statistics.common.e;

public class a {
    private static boolean a = false;

    public static synchronized void a(Context context) {
        synchronized (a.class) {
            if (context != null) {
                try {
                    if (!a) {
                        new Thread(new d(context)).start();
                        a = true;
                    }
                } catch (Throwable th) {
                    e.c(c.d, "e is " + th.getMessage());
                }
            }
        }
    }
}
