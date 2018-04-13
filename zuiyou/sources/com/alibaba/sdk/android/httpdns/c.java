package com.alibaba.sdk.android.httpdns;

class c {
    static String PROTOCOL = "http://";
    static int a = 15000;
    static String b;
    /* renamed from: b */
    static String[] f6b = new String[]{"203.107.1.1"};
    static String c = "80";
    /* renamed from: c */
    static final String[] f7c = new String[]{"203.107.1.97", "203.107.1.100", "httpdns-sc.aliyuncs.com"};
    static final String[] d = new String[0];

    static synchronized boolean a(String[] strArr) {
        boolean z;
        synchronized (c.class) {
            if (strArr != null) {
                if (strArr.length != 0) {
                    f6b = strArr;
                    z = true;
                }
            }
            z = false;
        }
        return z;
    }

    static synchronized void c(String str) {
        synchronized (c.class) {
            b = str;
        }
    }

    static synchronized void setHTTPSRequestEnabled(boolean z) {
        synchronized (c.class) {
            if (z) {
                PROTOCOL = "https://";
                c = "443";
            } else {
                PROTOCOL = "http://";
                c = "80";
            }
        }
    }

    static synchronized void setTimeoutInterval(int i) {
        synchronized (c.class) {
            if (i > 0) {
                a = i;
            }
        }
    }
}
