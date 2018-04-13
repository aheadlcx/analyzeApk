package com.tencent.smtt.sdk;

import android.os.HandlerThread;

class ah extends HandlerThread {
    private static ah a;

    public ah(String str) {
        super(str);
    }

    public static synchronized ah a() {
        ah ahVar;
        synchronized (ah.class) {
            if (a == null) {
                a = new ah("TbsHandlerThread");
                a.start();
            }
            ahVar = a;
        }
        return ahVar;
    }
}
