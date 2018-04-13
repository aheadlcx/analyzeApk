package com.umeng.commonsdk.framework;

import android.content.Context;
import com.umeng.commonsdk.internal.a;
import java.util.HashMap;

public class c {
    public static final String a = "analytics";
    public static final String b = "push";
    public static final String c = "share";
    public static final String d = "internal";
    private static HashMap<String, UMLogDataProtocol> e = null;
    private static Context f = null;

    public static String a(int i) {
        String str = "analytics";
        if (i >= 16385 && i <= 20480) {
            str = "push";
        }
        if (i >= 24577 && i <= 28672) {
            str = "share";
        }
        if (i < a.e || i > 36864) {
            return str;
        }
        return d;
    }

    public static boolean a(int i, UMLogDataProtocol uMLogDataProtocol) {
        if (e == null) {
            e = new HashMap();
        }
        String a = a(i);
        if (e.containsKey(a)) {
            return true;
        }
        if (!a().getPackageName().equals(b.a(a().getApplicationContext()))) {
            return false;
        }
        e.put(a, uMLogDataProtocol);
        return true;
    }

    public static void a(Context context) {
        if (f == null) {
            f = context.getApplicationContext();
        }
    }

    public static UMLogDataProtocol a(String str) {
        if (e.containsKey(str)) {
            return (UMLogDataProtocol) e.get(str);
        }
        return null;
    }

    public static Context a() {
        return f;
    }
}
