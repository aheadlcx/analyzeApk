package com.izuiyou.a.a;

import android.util.Log;

public final class b {
    public static void a(Object obj) {
        a(0, null, obj);
    }

    public static void b(Object obj) {
        a(1, null, obj);
    }

    public static void a(String str, Object... objArr) {
        a(1, str, objArr);
    }

    public static void a() {
        a(2, null, "execute");
    }

    public static void c(Object obj) {
        a(2, null, obj);
    }

    public static void b(String str, Object... objArr) {
        a(2, str, objArr);
    }

    public static void d(Object obj) {
        a(3, null, obj);
    }

    public static void c(String str, Object... objArr) {
        a(3, str, objArr);
    }

    public static void b() {
        a(4, null, "execute");
    }

    public static void e(Object obj) {
        a(4, null, obj);
    }

    public static void d(String str, Object... objArr) {
        a(4, str, objArr);
    }

    private static void a(int i, String str, Object... objArr) {
        if (a.a()) {
            String[] e = e(str, objArr);
            a(i, e[0] + " tid:" + Thread.currentThread().getId() + " ", e[2] + e[1]);
        }
    }

    private static String[] e(String str, Object... objArr) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String fileName = stackTrace[5].getFileName();
        String methodName = stackTrace[5].getMethodName();
        int lineNumber = stackTrace[5].getLineNumber();
        methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
        if (str == null) {
            str = fileName;
        }
        String a = objArr == null ? "Log with null object" : a(objArr);
        fileName = "(" + fileName + ":" + lineNumber + ")#" + methodName + " :";
        return new String[]{str, a, fileName};
    }

    private static String a(Object... objArr) {
        int i = 0;
        if (objArr.length > 1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\n");
            while (i < objArr.length) {
                Object obj = objArr[i];
                if (obj == null) {
                    stringBuilder.append("Param").append("[").append(i).append("]").append(" = ").append("null").append("\n");
                } else {
                    stringBuilder.append("Param").append("[").append(i).append("]").append(" = ").append(obj.toString()).append("\n");
                }
                i++;
            }
            return stringBuilder.toString();
        }
        Object obj2 = objArr[0];
        return obj2 == null ? "null" : obj2.toString();
    }

    static void a(int i, String str, String str2) {
        int i2 = 0;
        int length = str2.length() / 4000;
        if (length > 0) {
            int i3 = 0;
            while (i2 < length) {
                b(i, str, str2.substring(i3, i3 + 4000));
                i3 += 4000;
                i2++;
            }
            b(i, str, str2.substring(i3, str2.length()));
            return;
        }
        b(i, str, str2);
    }

    private static void b(int i, String str, String str2) {
        switch (i) {
            case 0:
                Log.v(str, str2);
                return;
            case 1:
                Log.d(str, str2);
                return;
            case 2:
                Log.i(str, str2);
                return;
            case 3:
                Log.w(str, str2);
                return;
            case 4:
                Log.e(str, str2);
                return;
            default:
                return;
        }
    }
}
