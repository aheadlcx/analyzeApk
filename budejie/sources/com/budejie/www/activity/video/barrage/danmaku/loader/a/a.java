package com.budejie.www.activity.video.barrage.danmaku.loader.a;

import com.budejie.www.activity.video.barrage.danmaku.a.a.c;
import com.budejie.www.activity.video.barrage.danmaku.loader.IllegalDataException;
import java.io.InputStream;

public class a implements com.budejie.www.activity.video.barrage.danmaku.loader.a {
    private static volatile a a;
    private c b;

    public /* synthetic */ com.budejie.www.activity.video.barrage.danmaku.a.c a() {
        return c();
    }

    private a() {
    }

    public static com.budejie.www.activity.video.barrage.danmaku.loader.a b() {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a();
                }
            }
        }
        return a;
    }

    public c c() {
        return this.b;
    }

    public void a(InputStream inputStream) throws IllegalDataException {
        try {
            this.b = new c(inputStream);
        } catch (Throwable e) {
            throw new IllegalDataException(e);
        }
    }
}
