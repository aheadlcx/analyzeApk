package com.nostra13.universalimageloader.b;

import android.util.Log;
import com.nostra13.universalimageloader.core.d;

public final class e {
    private static volatile boolean a = false;
    private static volatile boolean b = true;

    public static void a(boolean z) {
        a = z;
    }

    public static void a(String str, Object... objArr) {
        if (a) {
            a(3, null, str, objArr);
        }
    }

    public static void b(String str, Object... objArr) {
        a(4, null, str, objArr);
    }

    public static void c(String str, Object... objArr) {
        a(5, null, str, objArr);
    }

    public static void a(Throwable th) {
        a(6, th, null, new Object[0]);
    }

    public static void d(String str, Object... objArr) {
        a(6, null, str, objArr);
    }

    private static void a(int i, Throwable th, String str, Object... objArr) {
        if (b) {
            String format;
            if (objArr.length > 0) {
                format = String.format(str, objArr);
            } else {
                format = str;
            }
            if (th != null) {
                if (format == null) {
                    format = th.getMessage();
                }
                String stackTraceString = Log.getStackTraceString(th);
                format = String.format("%1$s\n%2$s", new Object[]{format, stackTraceString});
            }
            Log.println(i, d.a, format);
        }
    }
}
