package com.iflytek.cloud.thirdparty;

import android.content.Context;
import com.iflytek.cloud.SpeechError;

public abstract class cq {
    protected static final Object a = new Object();

    public interface a {
        String A();

        String B();

        String g();
    }

    public static synchronized cq a(a aVar) {
        cq b;
        synchronized (cq.class) {
            b = cr.b(aVar);
        }
        return b;
    }

    public static synchronized void a() {
        synchronized (cq.class) {
            cr.c();
        }
    }

    public static synchronized boolean a(Context context, String str, boolean z) {
        boolean b;
        synchronized (cq.class) {
            b = cr.b(context, str, z);
        }
        return b;
    }

    public abstract void a(SpeechError speechError);

    public abstract void a(String str, boolean z);

    public abstract void b();
}
