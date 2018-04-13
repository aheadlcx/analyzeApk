package com.budejie.www.download;

import java.util.HashMap;
import java.util.Map;

public class g {
    public static Map<String, f> a = new HashMap();
    private static g b;

    private g() {
    }

    public static g a() {
        if (b == null) {
            synchronized (g.class) {
                if (b == null) {
                    b = new g();
                }
            }
        }
        return b;
    }

    public void a(f fVar) {
        fVar.a(a);
        fVar.start();
    }
}
