package org.aspectj.a.b;

import java.util.Hashtable;
import java.util.StringTokenizer;
import org.aspectj.lang.a.a;
import org.aspectj.lang.a.d;
import org.aspectj.lang.c;

public final class b {
    static Hashtable e = new Hashtable();
    static Class f;
    private static Object[] g = new Object[0];
    Class a;
    ClassLoader b;
    String c;
    int d = 0;

    static {
        e.put("void", Void.TYPE);
        e.put("boolean", Boolean.TYPE);
        e.put("byte", Byte.TYPE);
        e.put("char", Character.TYPE);
        e.put("short", Short.TYPE);
        e.put("int", Integer.TYPE);
        e.put("long", Long.TYPE);
        e.put("float", Float.TYPE);
        e.put("double", Double.TYPE);
    }

    static Class a(String str, ClassLoader classLoader) {
        if (str.equals("*")) {
            return null;
        }
        Class cls = (Class) e.get(str);
        if (cls != null) {
            return cls;
        }
        if (classLoader != null) {
            return Class.forName(str, false, classLoader);
        }
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            if (f != null) {
                return f;
            }
            cls = a("java.lang.ClassNotFoundException");
            f = cls;
            return cls;
        }
    }

    static Class a(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public b(String str, Class cls) {
        this.c = str;
        this.a = cls;
        this.b = cls.getClassLoader();
    }

    public a a(String str, c cVar, int i) {
        int i2 = this.d;
        this.d = i2 + 1;
        return new a(i2, str, cVar, a(i, -1));
    }

    public static org.aspectj.lang.a a(a aVar, Object obj, Object obj2) {
        return new c(aVar, obj, obj2, g);
    }

    public static org.aspectj.lang.a a(a aVar, Object obj, Object obj2, Object obj3) {
        return new c(aVar, obj, obj2, new Object[]{obj3});
    }

    public org.aspectj.lang.a.c a(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        int i;
        int parseInt = Integer.parseInt(str, 16);
        Class a = a(str3, this.b);
        StringTokenizer stringTokenizer = new StringTokenizer(str4, ":");
        int countTokens = stringTokenizer.countTokens();
        Class[] clsArr = new Class[countTokens];
        for (i = 0; i < countTokens; i++) {
            clsArr[i] = a(stringTokenizer.nextToken(), this.b);
        }
        stringTokenizer = new StringTokenizer(str5, ":");
        int countTokens2 = stringTokenizer.countTokens();
        String[] strArr = new String[countTokens2];
        for (i = 0; i < countTokens2; i++) {
            strArr[i] = stringTokenizer.nextToken();
        }
        stringTokenizer = new StringTokenizer(str6, ":");
        int countTokens3 = stringTokenizer.countTokens();
        Class[] clsArr2 = new Class[countTokens3];
        for (i = 0; i < countTokens3; i++) {
            clsArr2[i] = a(stringTokenizer.nextToken(), this.b);
        }
        return new e(parseInt, str2, a, clsArr, strArr, clsArr2, a(str7, this.b));
    }

    public d a(int i, int i2) {
        return new g(this.a, this.c, i);
    }
}
