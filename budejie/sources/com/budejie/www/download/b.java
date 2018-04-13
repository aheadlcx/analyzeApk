package com.budejie.www.download;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

public class b {
    public static Map<String, a> a = new HashMap();
    public static Map<String, a> b = new HashMap();
    private static b c;

    private b() {
    }

    public static b a() {
        if (c == null) {
            synchronized (b.class) {
                if (c == null) {
                    c = new b();
                }
            }
        }
        return c;
    }

    public void a(Context context, a aVar) {
        aVar.a(context);
        aVar.a(a);
        aVar.start();
    }
}
