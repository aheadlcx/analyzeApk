package com.ta.utdid2.b.a;

import java.lang.reflect.Method;

public class d {
    private static Class<?> a = null;
    /* renamed from: a */
    private static Method f51a = null;
    private static Method b = null;
    public static boolean e;

    public static int getInt(String str, int i) {
        a();
        try {
            i = ((Integer) b.invoke(a, new Object[]{str, Integer.valueOf(i)})).intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    static {
        boolean z = true;
        if (getInt("alidebug", 0) != 1) {
            z = false;
        }
        e = z;
    }

    private static void a() {
        try {
            if (a == null) {
                a = Class.forName("android.os.SystemProperties");
                f51a = a.getDeclaredMethod("get", new Class[]{String.class});
                b = a.getDeclaredMethod("getInt", new Class[]{String.class, Integer.TYPE});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
