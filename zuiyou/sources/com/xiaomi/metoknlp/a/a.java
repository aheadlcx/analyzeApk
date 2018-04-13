package com.xiaomi.metoknlp.a;

import android.os.Build;
import android.util.Log;
import java.lang.reflect.Field;

public class a {
    private static String a = "NLPBuild";
    private static boolean b = false;
    private static String c = Build.BRAND;
    private static String d = Build.TYPE;
    private static Class e;
    private static Field f;
    private static Field g;
    private static Field h;
    private static Field i;
    private static Field j;

    static {
        boolean z = false;
        try {
            e = Class.forName("miui.os.Build");
            f = e.getField("IS_CTS_BUILD");
            g = e.getField("IS_CTA_BUILD");
            h = e.getField("IS_ALPHA_BUILD");
            i = e.getField("IS_DEVELOPMENT_VERSION");
            j = e.getField("IS_STABLE_VERSION");
        } catch (ClassNotFoundException e) {
            z = true;
        } catch (NoSuchFieldException e2) {
            z = true;
        } catch (Exception e3) {
            z = true;
        }
        if (z) {
            e = null;
            f = null;
            g = null;
            h = null;
            i = null;
            j = null;
        }
    }

    public static boolean a() {
        if (b) {
            Log.d(a, "brand=" + c);
        }
        return c != null && c.equalsIgnoreCase("xiaomi");
    }

    public static String b() {
        return "3rdROM-" + d;
    }

    public static boolean c() {
        if (!(!a() || e == null || f == null)) {
            try {
                boolean z = f.getBoolean(e);
                if (!b) {
                    return z;
                }
                Log.d(a, "is cts build=" + z);
                return z;
            } catch (IllegalAccessException e) {
            }
        }
        return false;
    }

    public static boolean d() {
        if (!(!a() || e == null || h == null)) {
            try {
                boolean z = h.getBoolean(e);
                if (!b) {
                    return z;
                }
                Log.d(a, "is alpha version=" + z);
                return z;
            } catch (IllegalAccessException e) {
            }
        }
        return false;
    }

    public static boolean e() {
        if (!(!a() || e == null || i == null)) {
            try {
                boolean z = i.getBoolean(e);
                if (!b) {
                    return z;
                }
                Log.d(a, "is dev version=" + z);
                return z;
            } catch (IllegalAccessException e) {
            }
        }
        return false;
    }

    public static boolean f() {
        if (!(!a() || e == null || j == null)) {
            try {
                boolean z = j.getBoolean(e);
                if (!b) {
                    return z;
                }
                Log.d(a, "is stable version=" + z);
                return z;
            } catch (IllegalAccessException e) {
            }
        }
        return false;
    }
}
