package com.alipay.sdk.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public final class c {
    public static final boolean a = false;
    public static final String b = "mspstd";

    private static void a() {
    }

    private static void b() {
    }

    private static void c() {
    }

    private static void d() {
    }

    private static void e() {
    }

    private static void f() {
    }

    private static void g() {
    }

    private static void a(Object obj) {
        if (!(obj instanceof Exception)) {
        }
    }

    private static void h() {
    }

    private static String a(Throwable th) {
        Writer stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
