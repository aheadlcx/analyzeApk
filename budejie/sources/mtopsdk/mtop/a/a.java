package mtopsdk.mtop.a;

import android.content.Context;
import com.taobao.tao.remotebusiness.listener.c;
import java.util.concurrent.atomic.AtomicBoolean;
import mtopsdk.common.util.TBSdkLog$LogEnable;
import mtopsdk.common.util.g;
import mtopsdk.common.util.l;
import mtopsdk.common.util.m;
import mtopsdk.mtop.domain.EnvModeEnum;
import mtopsdk.mtop.util.e;

public class a {
    private static f a = f.a();
    private static volatile boolean b = false;
    private static AtomicBoolean c = new AtomicBoolean(true);
    private static Object d = new Object();

    public static void a() {
        if (!b) {
            synchronized (d) {
                try {
                    if (!b) {
                        d.wait(60000);
                        if (!b) {
                            m.d("mtopsdk.MtopSDK", "[checkMtopSDKInit]Didn't call MtopSDK.init(...),please execute global init.");
                        }
                    }
                } catch (Exception e) {
                    m.d("mtopsdk.MtopSDK", "[checkMtopSDKInit] wait MtopSDK initLock failed---" + e.toString());
                }
            }
        }
    }

    public static synchronized void a(Context context, c cVar, String str) {
        synchronized (a.class) {
            if (l.a(str)) {
                a.b(str);
            }
            if (!b) {
                a.a(context);
                e.a(new b(context, cVar, str));
            }
        }
    }

    public static synchronized void a(Context context, String str) {
        synchronized (a.class) {
            if (m.a(TBSdkLog$LogEnable.InfoEnable)) {
                m.b("mtopsdk.MtopSDK", "[init]ttid=" + str);
            }
            a(context, null, str);
        }
    }

    public static synchronized void a(EnvModeEnum envModeEnum) {
        synchronized (a.class) {
            if (envModeEnum != null) {
                if (a.i() != envModeEnum) {
                    if (g.c() || c.compareAndSet(true, false)) {
                        if (m.a(TBSdkLog$LogEnable.InfoEnable)) {
                            m.b("mtopsdk.MtopSDK", "[switchEnvMode]MtopSDK switchEnvMode Called");
                        }
                        e.a(new d(envModeEnum));
                    } else {
                        m.d("mtopsdk.MtopSDK", "debug package can switch environment only once!");
                    }
                }
            }
        }
    }

    public static void a(boolean z) {
        m.a(z);
    }

    private static void b(Context context) {
        if (m.a(TBSdkLog$LogEnable.InfoEnable)) {
            m.b("mtopsdk.MtopSDK", "[executeInitExtraTask]MtopSDK initextra start");
        }
        try {
            i.a().a(context);
        } catch (Throwable th) {
            m.b("mtopsdk.MtopSDK", "[executeInitExtraTask] execute MtopSDK initExtraTask error.---", th);
        }
        if (m.a(TBSdkLog$LogEnable.InfoEnable)) {
            m.b("mtopsdk.MtopSDK", "[executeInitExtraTask]MtopSDK initextra end");
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void c(android.content.Context r5, com.taobao.tao.remotebusiness.listener.c r6, java.lang.String r7) {
        /*
        r1 = d;
        monitor-enter(r1);
        r0 = b;	 Catch:{ all -> 0x00a5 }
        if (r0 == 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r1);	 Catch:{ all -> 0x00a5 }
    L_0x0008:
        return;
    L_0x0009:
        r0 = mtopsdk.common.util.TBSdkLog$LogEnable.InfoEnable;	 Catch:{ all -> 0x00a5 }
        r0 = mtopsdk.common.util.m.a(r0);	 Catch:{ all -> 0x00a5 }
        if (r0 == 0) goto L_0x0025;
    L_0x0011:
        r0 = "mtopsdk.MtopSDK";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00a5 }
        r3 = "[executeInitCoreTask]MtopSDK initcore start. ttid=";
        r2.<init>(r3);	 Catch:{ all -> 0x00a5 }
        r2 = r2.append(r7);	 Catch:{ all -> 0x00a5 }
        r2 = r2.toString();	 Catch:{ all -> 0x00a5 }
        mtopsdk.common.util.m.b(r0, r2);	 Catch:{ all -> 0x00a5 }
    L_0x0025:
        r0 = a;	 Catch:{ Throwable -> 0x0083 }
        r0.a(r5);	 Catch:{ Throwable -> 0x0083 }
        mtopsdk.xstate.a.a(r5);	 Catch:{ Throwable -> 0x0083 }
        r0 = mtopsdk.common.util.l.a(r7);	 Catch:{ Throwable -> 0x0083 }
        if (r0 == 0) goto L_0x0038;
    L_0x0033:
        r0 = a;	 Catch:{ Throwable -> 0x0083 }
        r0.b(r7);	 Catch:{ Throwable -> 0x0083 }
    L_0x0038:
        if (r6 != 0) goto L_0x003f;
    L_0x003a:
        r6 = new com.taobao.tao.remotebusiness.listener.c;	 Catch:{ Throwable -> 0x0083 }
        r6.<init>();	 Catch:{ Throwable -> 0x0083 }
    L_0x003f:
        r0 = a;	 Catch:{ Throwable -> 0x0083 }
        r0 = r0.e();	 Catch:{ Throwable -> 0x0083 }
        r6.a(r5, r0);	 Catch:{ Throwable -> 0x0083 }
        r0 = a;	 Catch:{ Throwable -> 0x0083 }
        r0.a(r6);	 Catch:{ Throwable -> 0x0083 }
        r0 = a;	 Catch:{ Throwable -> 0x0083 }
        r2 = new mtopsdk.b.a;	 Catch:{ Throwable -> 0x0083 }
        r3 = a;	 Catch:{ Throwable -> 0x0083 }
        r3 = r3.e();	 Catch:{ Throwable -> 0x0083 }
        r4 = 0;
        r2.<init>(r3, r4);	 Catch:{ Throwable -> 0x0083 }
        r2 = r6.a(r2);	 Catch:{ Throwable -> 0x0083 }
        r0.a(r2);	 Catch:{ Throwable -> 0x0083 }
        r0 = 1;
        b = r0;	 Catch:{ all -> 0x00a5 }
        r0 = d;	 Catch:{ all -> 0x00a5 }
        r0.notifyAll();	 Catch:{ all -> 0x00a5 }
    L_0x006a:
        r0 = mtopsdk.common.util.TBSdkLog$LogEnable.InfoEnable;	 Catch:{ all -> 0x00a5 }
        r0 = mtopsdk.common.util.m.a(r0);	 Catch:{ all -> 0x00a5 }
        if (r0 == 0) goto L_0x0079;
    L_0x0072:
        r0 = "mtopsdk.MtopSDK";
        r2 = "[executeInitCoreTask]MtopSDK initcore end";
        mtopsdk.common.util.m.b(r0, r2);	 Catch:{ all -> 0x00a5 }
    L_0x0079:
        monitor-exit(r1);	 Catch:{ all -> 0x00a5 }
        r0 = new mtopsdk.mtop.a.c;
        r0.<init>(r5);
        mtopsdk.mtop.util.e.a(r0);
        goto L_0x0008;
    L_0x0083:
        r0 = move-exception;
        r2 = "mtopsdk.MtopSDK";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00a8 }
        r4 = "[executeInitCoreTask]MtopSDK initcore error---";
        r3.<init>(r4);	 Catch:{ all -> 0x00a8 }
        r0 = r0.toString();	 Catch:{ all -> 0x00a8 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x00a8 }
        r0 = r0.toString();	 Catch:{ all -> 0x00a8 }
        mtopsdk.common.util.m.d(r2, r0);	 Catch:{ all -> 0x00a8 }
        r0 = 1;
        b = r0;	 Catch:{ all -> 0x00a5 }
        r0 = d;	 Catch:{ all -> 0x00a5 }
        r0.notifyAll();	 Catch:{ all -> 0x00a5 }
        goto L_0x006a;
    L_0x00a5:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
    L_0x00a8:
        r0 = move-exception;
        r2 = 1;
        b = r2;	 Catch:{ all -> 0x00a5 }
        r2 = d;	 Catch:{ all -> 0x00a5 }
        r2.notifyAll();	 Catch:{ all -> 0x00a5 }
        throw r0;	 Catch:{ all -> 0x00a5 }
        */
        throw new UnsupportedOperationException("Method not decompiled: mtopsdk.mtop.a.a.c(android.content.Context, com.taobao.tao.remotebusiness.listener.c, java.lang.String):void");
    }

    private static void c(EnvModeEnum envModeEnum) {
        if (a.c() != null && envModeEnum != null) {
            int e = a.e();
            if (EnvModeEnum.TEST.getEnvMode() == envModeEnum.getEnvMode() || EnvModeEnum.TEST_SANDBOX.getEnvMode() == envModeEnum.getEnvMode()) {
                e = a.d();
            }
            a.c().a(a.b(), e);
            a.a(a.c().a(new mtopsdk.b.a(e, null)));
        }
    }
}
