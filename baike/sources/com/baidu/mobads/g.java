package com.baidu.mobads;

import java.util.HashMap;

public class g {
    private static g a;
    private static HashMap<String, String> b = new HashMap();

    private g() {
    }

    public static synchronized g a() {
        g gVar;
        synchronized (g.class) {
            if (a == null) {
                a = new g();
            }
            gVar = a;
        }
        return gVar;
    }

    public int a(int i) {
        int parseInt;
        Exception e;
        if (i < 1) {
            return 1;
        }
        try {
            if (b.containsKey(i + "")) {
                parseInt = Integer.parseInt((String) b.get(i + "")) + 1;
                if (parseInt < 1) {
                    parseInt = 1;
                }
                try {
                    b.put(i + "", parseInt + "");
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    return parseInt;
                }
            }
            b.put(i + "", "1");
            parseInt = 1;
        } catch (Exception e3) {
            Exception exception = e3;
            parseInt = 1;
            e = exception;
            e.printStackTrace();
            return parseInt;
        }
        return parseInt;
    }
}
