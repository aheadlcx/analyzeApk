package com.b.a.c;

import android.content.Context;
import android.os.Handler;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class a {
    private static ScheduledExecutorService a = Executors.newScheduledThreadPool(1);
    private static boolean b = false;
    private static int c = 0;

    public static void a(Context context) {
        if (context == null) {
            com.b.a.b.a.a.h();
            return;
        }
        Handler g = com.b.a.b.a.a.g();
        if (g != null) {
            g.post(new e(context, 2, System.currentTimeMillis()));
        }
        com.b.a.b.a.a.h();
    }

    public static void a(Context context, String str, String str2) {
        if (context == null) {
            com.b.a.b.a.a.h();
        } else if (str == null || str.equals("")) {
            com.b.a.b.a.a.h();
        } else if (str2 == null || str2.equals("")) {
            com.b.a.b.a.a.h();
        } else {
            Handler g = com.b.a.b.a.a.g();
            if (g != null) {
                g.post(new b(context, str, str2, System.currentTimeMillis()));
            }
            com.b.a.b.a.a.h();
        }
    }
}
