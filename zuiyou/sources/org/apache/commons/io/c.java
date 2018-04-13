package org.apache.commons.io;

import java.io.File;

public class c {
    public static final String a = Character.toString('.');
    private static final char b = File.separatorChar;
    private static final char c;

    static {
        if (a()) {
            c = '/';
        } else {
            c = '\\';
        }
    }

    static boolean a() {
        return b == '\\';
    }

    public static int a(String str) {
        if (str == null) {
            return -1;
        }
        return Math.max(str.lastIndexOf(47), str.lastIndexOf(92));
    }

    public static int b(String str) {
        if (str == null) {
            return -1;
        }
        int lastIndexOf = str.lastIndexOf(46);
        if (a(str) <= lastIndexOf) {
            return lastIndexOf;
        }
        return -1;
    }

    public static String c(String str) {
        if (str == null) {
            return null;
        }
        g(str);
        return str.substring(a(str) + 1);
    }

    private static void g(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) == '\u0000') {
                throw new IllegalArgumentException("Null byte present in file/path name. There are no known legitimate use cases for such data, but several injection attacks may use it");
            }
        }
    }

    public static String d(String str) {
        return f(c(str));
    }

    public static String e(String str) {
        if (str == null) {
            return null;
        }
        int b = b(str);
        if (b == -1) {
            return "";
        }
        return str.substring(b + 1);
    }

    public static String f(String str) {
        if (str == null) {
            return null;
        }
        g(str);
        int b = b(str);
        return b != -1 ? str.substring(0, b) : str;
    }
}
