package com.budejie.www.activity.video.barrage.danmaku.loader.a;

import com.budejie.www.activity.video.barrage.danmaku.a.c;
import com.budejie.www.activity.video.barrage.danmaku.loader.a;
import java.io.InputStream;

public class b implements a {
    private static b a;
    private com.budejie.www.activity.video.barrage.danmaku.a.a.a b;

    public /* synthetic */ c a() {
        return c();
    }

    private b() {
    }

    public static b b() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    public void a(InputStream inputStream) {
        this.b = new com.budejie.www.activity.video.barrage.danmaku.a.a.a(inputStream);
    }

    public com.budejie.www.activity.video.barrage.danmaku.a.a.a c() {
        return this.b;
    }
}
