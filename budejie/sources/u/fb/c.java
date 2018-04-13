package u.fb;

import android.content.Context;

public class c {
    private static final String a = c.class.getName();
    private static c b = null;
    private static String c = null;
    private static Class d = null;
    private static Class e = null;
    private static Class f = null;
    private static Class g = null;
    private static Class h = null;
    private static Class i = null;
    private static Class j = null;

    private c(String str) {
        try {
            e = Class.forName(new StringBuilder(String.valueOf(str)).append(".R$drawable").toString());
        } catch (ClassNotFoundException e) {
            b.b(a, e.getMessage());
        }
        try {
            f = Class.forName(new StringBuilder(String.valueOf(str)).append(".R$layout").toString());
        } catch (ClassNotFoundException e2) {
            b.b(a, e2.getMessage());
        }
        try {
            d = Class.forName(new StringBuilder(String.valueOf(str)).append(".R$id").toString());
        } catch (ClassNotFoundException e22) {
            b.b(a, e22.getMessage());
        }
        try {
            g = Class.forName(new StringBuilder(String.valueOf(str)).append(".R$anim").toString());
        } catch (ClassNotFoundException e222) {
            b.b(a, e222.getMessage());
        }
        try {
            h = Class.forName(new StringBuilder(String.valueOf(str)).append(".R$style").toString());
        } catch (ClassNotFoundException e2222) {
            b.b(a, e2222.getMessage());
        }
        try {
            i = Class.forName(new StringBuilder(String.valueOf(str)).append(".R$string").toString());
        } catch (ClassNotFoundException e22222) {
            b.b(a, e22222.getMessage());
        }
        try {
            j = Class.forName(new StringBuilder(String.valueOf(str)).append(".R$array").toString());
        } catch (ClassNotFoundException e222222) {
            b.b(a, e222222.getMessage());
        }
    }

    public static synchronized c a(Context context) {
        c cVar;
        synchronized (c.class) {
            if (b == null) {
                String str;
                if (c != null) {
                    str = c;
                } else {
                    str = context.getPackageName();
                }
                c = str;
                b = new c(c);
            }
            cVar = b;
        }
        return cVar;
    }

    public int a(String str) {
        return a(g, str);
    }

    public int b(String str) {
        return a(d, str);
    }

    public int c(String str) {
        return a(e, str);
    }

    public int d(String str) {
        return a(f, str);
    }

    public int e(String str) {
        return a(i, str);
    }

    private int a(Class<?> cls, String str) {
        if (cls == null) {
            b.b(a, "getRes(null," + str + ")");
            throw new IllegalArgumentException("ResClass is not initialized. Please make sure you have added neccessary resources. Also make sure you have " + c + ".R$* configured in obfuscation. field=" + str);
        }
        try {
            return cls.getField(str).getInt(str);
        } catch (Exception e) {
            b.b(a, "getRes(" + cls.getName() + ", " + str + ")");
            b.b(a, "Error getting resource. Make sure you have copied all resources (res/) from SDK to your project. ");
            b.b(a, e.getMessage());
            return -1;
        }
    }
}
