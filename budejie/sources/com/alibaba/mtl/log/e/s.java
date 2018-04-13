package com.alibaba.mtl.log.e;

import java.util.Map;

public class s {
    public static void send(Map<String, String> map) {
        try {
            Object a = o.a("com.ut.mini.UTAnalytics", "getInstance");
            if (a != null) {
                a = o.a(a, "getDefaultTracker");
                if (a != null) {
                    o.a(a, "send", new Object[]{map}, Map.class);
                }
            }
        } catch (Exception e) {
        }
    }
}
