package com.marshalchen.ultimaterecyclerview;

import android.util.Log;

public final class c {
    private static boolean a = true;
    private static String b = "Chen";

    private static StackTraceElement a() {
        return Thread.currentThread().getStackTrace()[4];
    }

    private static String a(StackTraceElement stackTraceElement) {
        return String.format("%s:%s.%s:%d", new Object[]{b, stackTraceElement.getClassName(), stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber())});
    }

    private static String b(StackTraceElement stackTraceElement) {
        return String.format("%s:%s:%d", new Object[]{b, stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber())});
    }

    public static void a(String str) {
        if (a) {
            Log.d(b, b(a()) + ">" + str);
        }
    }

    public static void a(Exception exception, String str) {
        if (a) {
            Log.e(b, a(a()) + "\n>" + exception.getMessage() + "\n>" + exception.getStackTrace() + "   " + str);
            exception.printStackTrace();
        }
    }
}
