package com.umeng.commonsdk.proguard;

import android.content.Context;
import com.umeng.commonsdk.statistics.common.e;

public class b {
    private static boolean a = false;
    private static Object b = new Object();

    public static void a(Context context, Throwable th) {
        if (!a) {
            e.a("walle-crash", "report is " + a);
            new Thread(new bb(context, th)).start();
        }
    }
}
