package org.apache.commons.cli;

public class f {
    public static final Class a;
    public static final Class b;
    public static final Class c;
    public static final Class d;
    public static final Class e;
    public static final Class f;
    public static final Class g;
    public static final Class h;
    public static final Class i;
    static Class j;
    static Class k;
    static Class l;
    static Class m;
    static Class n;
    static Class o;
    static Class p;
    static Class q;
    static Class r;

    static {
        Class a;
        if (j == null) {
            a = a("java.lang.String");
            j = a;
        } else {
            a = j;
        }
        a = a;
        if (k == null) {
            a = a("java.lang.Object");
            k = a;
        } else {
            a = k;
        }
        b = a;
        if (l == null) {
            a = a("java.lang.Number");
            l = a;
        } else {
            a = l;
        }
        c = a;
        if (m == null) {
            a = a("java.util.Date");
            m = a;
        } else {
            a = m;
        }
        d = a;
        if (n == null) {
            a = a("java.lang.Class");
            n = a;
        } else {
            a = n;
        }
        e = a;
        if (o == null) {
            a = a("java.io.FileInputStream");
            o = a;
        } else {
            a = o;
        }
        f = a;
        if (p == null) {
            a = a("java.io.File");
            p = a;
        } else {
            a = p;
        }
        g = a;
        if (q == null) {
            a = a("[Ljava.io.File;");
            q = a;
        } else {
            a = q;
        }
        h = a;
        if (r == null) {
            a = a("java.net.URL");
            r = a;
        } else {
            a = r;
        }
        i = a;
    }

    static Class a(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }
}
