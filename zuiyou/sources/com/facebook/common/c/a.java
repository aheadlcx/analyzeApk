package com.facebook.common.c;

public class a {
    private static c a = b.a();

    public static boolean a(int i) {
        return a.a(i);
    }

    public static void a(Class<?> cls, String str) {
        if (a.a(2)) {
            a.a(a((Class) cls), str);
        }
    }

    public static void a(Class<?> cls, String str, Object obj) {
        if (a.a(2)) {
            a.a(a((Class) cls), a(str, obj));
        }
    }

    public static void a(Class<?> cls, String str, Object obj, Object obj2) {
        if (a.a(2)) {
            a.a(a((Class) cls), a(str, obj, obj2));
        }
    }

    public static void a(Class<?> cls, String str, Object obj, Object obj2, Object obj3) {
        if (a(2)) {
            a((Class) cls, a(str, obj, obj2, obj3));
        }
    }

    public static void a(Class<?> cls, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (a.a(2)) {
            a.a(a((Class) cls), a(str, obj, obj2, obj3, obj4));
        }
    }

    public static void a(String str, String str2, Object... objArr) {
        if (a.a(2)) {
            a.a(str, a(str2, objArr));
        }
    }

    public static void a(Class<?> cls, String str, Object... objArr) {
        if (a.a(2)) {
            a.a(a((Class) cls), a(str, objArr));
        }
    }

    public static void a(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (a.a(2)) {
            a.a(a((Class) cls), a(str, objArr), th);
        }
    }

    public static void b(Class<?> cls, String str) {
        if (a.a(3)) {
            a.b(a((Class) cls), str);
        }
    }

    public static void b(Class<?> cls, String str, Object obj) {
        if (a.a(3)) {
            a.b(a((Class) cls), a(str, obj));
        }
    }

    public static void c(Class<?> cls, String str) {
        if (a.a(4)) {
            a.c(a((Class) cls), str);
        }
    }

    public static void d(Class<?> cls, String str) {
        if (a.a(5)) {
            a.d(a((Class) cls), str);
        }
    }

    public static void b(String str, String str2, Object... objArr) {
        if (a.a(5)) {
            a.d(str, a(str2, objArr));
        }
    }

    public static void a(String str, Throwable th, String str2, Object... objArr) {
        if (a.a(5)) {
            a.b(str, a(str2, objArr), th);
        }
    }

    public static void b(Class<?> cls, String str, Object... objArr) {
        if (a.a(5)) {
            a.d(a((Class) cls), a(str, objArr));
        }
    }

    public static void b(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (a(5)) {
            a((Class) cls, a(str, objArr), th);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (a.a(5)) {
            a.b(str, str2, th);
        }
    }

    public static void a(Class<?> cls, String str, Throwable th) {
        if (a.a(5)) {
            a.b(a((Class) cls), str, th);
        }
    }

    public static void a(String str, String str2) {
        if (a.a(6)) {
            a.e(str, str2);
        }
    }

    public static void e(Class<?> cls, String str) {
        if (a.a(6)) {
            a.e(a((Class) cls), str);
        }
    }

    public static void b(String str, Throwable th, String str2, Object... objArr) {
        if (a.a(6)) {
            a.c(str, a(str2, objArr), th);
        }
    }

    public static void c(Class<?> cls, String str, Object... objArr) {
        if (a.a(6)) {
            a.e(a((Class) cls), a(str, objArr));
        }
    }

    public static void c(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (a.a(6)) {
            a.c(a((Class) cls), a(str, objArr), th);
        }
    }

    public static void b(String str, String str2, Throwable th) {
        if (a.a(6)) {
            a.c(str, str2, th);
        }
    }

    public static void b(Class<?> cls, String str, Throwable th) {
        if (a.a(6)) {
            a.c(a((Class) cls), str, th);
        }
    }

    public static void c(String str, String str2, Object... objArr) {
        if (a.a(6)) {
            a.f(str, a(str2, objArr));
        }
    }

    public static void d(Class<?> cls, String str, Object... objArr) {
        if (a.a(6)) {
            a.f(a((Class) cls), a(str, objArr));
        }
    }

    public static void c(Class<?> cls, String str, Throwable th) {
        if (a.a(6)) {
            a.d(a((Class) cls), str, th);
        }
    }

    private static String a(String str, Object... objArr) {
        return String.format(null, str, objArr);
    }

    private static String a(Class<?> cls) {
        return cls.getSimpleName();
    }
}
