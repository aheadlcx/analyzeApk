package com.sprite.ads.internal.download;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

public class c {
    public static Map<String, DownTask> a = new HashMap();
    public static Map<String, DownTask> b = new HashMap();
    private static c c;

    private c() {
    }

    public static c a() {
        if (c == null) {
            synchronized (c.class) {
                if (c == null) {
                    c = new c();
                }
            }
        }
        return c;
    }

    public void a(Context context, DownTask downTask) {
        downTask.a(context);
        downTask.a(a);
        downTask.start();
    }
}
