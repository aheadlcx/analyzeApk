package com.baidu.mobstat;

import android.content.Context;

public class au {
    private static l a;

    public static synchronized l a(Context context) {
        l awVar;
        Throwable th;
        synchronized (au.class) {
            bd.a("getBPStretegyController begin");
            l lVar = a;
            if (lVar == null) {
                try {
                    Class a = ax.a(context, "com.baidu.bottom.remote.BPStretegyController2");
                    if (a != null) {
                        awVar = new aw(a.newInstance());
                        try {
                            bd.a("Get BPStretegyController load remote class v2");
                        } catch (Throwable e) {
                            Throwable th2 = e;
                            lVar = awVar;
                            th = th2;
                            bd.a(th);
                            awVar = lVar;
                            if (awVar == null) {
                                awVar = new av();
                                bd.a("Get BPStretegyController load local class");
                            }
                            a = awVar;
                            ax.a(context, awVar);
                            bd.a("getBPStretegyController end");
                            return awVar;
                        }
                        if (awVar == null) {
                            awVar = new av();
                            bd.a("Get BPStretegyController load local class");
                        }
                        a = awVar;
                        ax.a(context, awVar);
                        bd.a("getBPStretegyController end");
                    }
                } catch (Exception e2) {
                    th = e2;
                    bd.a(th);
                    awVar = lVar;
                    if (awVar == null) {
                        awVar = new av();
                        bd.a("Get BPStretegyController load local class");
                    }
                    a = awVar;
                    ax.a(context, awVar);
                    bd.a("getBPStretegyController end");
                    return awVar;
                }
            }
            awVar = lVar;
            if (awVar == null) {
                awVar = new av();
                bd.a("Get BPStretegyController load local class");
            }
            a = awVar;
            ax.a(context, awVar);
            bd.a("getBPStretegyController end");
        }
        return awVar;
    }

    public static synchronized void a() {
        synchronized (au.class) {
            a = null;
        }
    }
}
