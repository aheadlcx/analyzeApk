package org.aspectj.a.b;

import java.lang.ref.SoftReference;
import java.util.StringTokenizer;
import org.aspectj.lang.c;

abstract class f implements c {
    private static boolean a = true;
    static String[] k = new String[0];
    static Class[] l = new Class[0];
    private String b;
    int e = -1;
    String f;
    String g;
    Class h;
    a i;
    ClassLoader j = null;

    private interface a {
        String a(int i);

        void a(int i, String str);
    }

    private static final class b implements a {
        private SoftReference a;

        public b() {
            b();
        }

        public String a(int i) {
            String[] a = a();
            if (a == null) {
                return null;
            }
            return a[i];
        }

        public void a(int i, String str) {
            String[] a = a();
            if (a == null) {
                a = b();
            }
            a[i] = str;
        }

        private String[] a() {
            return (String[]) this.a.get();
        }

        private String[] b() {
            Object obj = new String[3];
            this.a = new SoftReference(obj);
            return obj;
        }
    }

    protected abstract String a(h hVar);

    f(int i, String str, Class cls) {
        this.e = i;
        this.f = str;
        this.h = cls;
    }

    String b(h hVar) {
        String str = null;
        if (a) {
            if (this.i == null) {
                try {
                    this.i = new b();
                } catch (Throwable th) {
                    a = false;
                }
            } else {
                str = this.i.a(hVar.i);
            }
        }
        if (str == null) {
            str = a(hVar);
        }
        if (a) {
            this.i.a(hVar.i, str);
        }
        return str;
    }

    public final String toString() {
        return b(h.k);
    }

    public int d() {
        if (this.e == -1) {
            this.e = b(0);
        }
        return this.e;
    }

    public String e() {
        if (this.f == null) {
            this.f = a(1);
        }
        return this.f;
    }

    public Class f() {
        if (this.h == null) {
            this.h = c(2);
        }
        return this.h;
    }

    public String g() {
        if (this.g == null) {
            this.g = f().getName();
        }
        return this.g;
    }

    private ClassLoader a() {
        if (this.j == null) {
            this.j = getClass().getClassLoader();
        }
        return this.j;
    }

    String a(int i) {
        int i2 = 0;
        int indexOf = this.b.indexOf(45);
        while (true) {
            int i3 = i - 1;
            if (i <= 0) {
                break;
            }
            i2 = indexOf + 1;
            indexOf = this.b.indexOf(45, i2);
            i = i3;
        }
        if (indexOf == -1) {
            indexOf = this.b.length();
        }
        return this.b.substring(i2, indexOf);
    }

    int b(int i) {
        return Integer.parseInt(a(i), 16);
    }

    Class c(int i) {
        return b.a(a(i), a());
    }

    Class[] d(int i) {
        StringTokenizer stringTokenizer = new StringTokenizer(a(i), ":");
        int countTokens = stringTokenizer.countTokens();
        Class[] clsArr = new Class[countTokens];
        for (int i2 = 0; i2 < countTokens; i2++) {
            clsArr[i2] = b.a(stringTokenizer.nextToken(), a());
        }
        return clsArr;
    }
}
