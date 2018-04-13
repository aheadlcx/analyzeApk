package com.ali.auth.third.core.rpc.protocol;

public class SecurityThreadLocal {
    private static ThreadLocal<String> sThreadKey = new ThreadLocal();

    public static String get() {
        return (String) sThreadKey.get();
    }

    public static void set(String str) {
        sThreadKey.set(str);
    }
}
