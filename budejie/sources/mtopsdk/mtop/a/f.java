package mtopsdk.mtop.a;

import android.content.Context;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.taobao.tao.remotebusiness.listener.c;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import mtopsdk.a.b;
import mtopsdk.common.util.TBSdkLog$LogEnable;
import mtopsdk.common.util.m;
import mtopsdk.mtop.domain.EnvModeEnum;
import mtopsdk.mtop.unit.ApiUnit;
import mtopsdk.mtop.util.e;
import mtopsdk.xstate.a;

public class f {
    private static final f a = new f();
    private static EnvModeEnum b = EnvModeEnum.ONLINE;
    private static Context c;
    private static c d;
    private static int e = 0;
    private static int f = 0;
    private static String g;
    private static String h;
    private static String i;
    private static String j;
    private static String k;
    private static volatile ApiUnit l;
    private static b n = new b(e.b());
    private Lock m = new ReentrantLock();

    private f() {
    }

    public static f a() {
        return a;
    }

    public f a(int i) {
        f = i;
        return this;
    }

    public f a(Context context) {
        if (context != null) {
            c = context.getApplicationContext();
        }
        return this;
    }

    public f a(c cVar) {
        d = cVar;
        return this;
    }

    public f a(String str) {
        h = str;
        a.a("appKey", str);
        return this;
    }

    public f a(EnvModeEnum envModeEnum) {
        if (envModeEnum != null) {
            b = envModeEnum;
        }
        return this;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public mtopsdk.mtop.a.f a(mtopsdk.mtop.unit.ApiUnit r5) {
        /*
        r4 = this;
        if (r5 == 0) goto L_0x002e;
    L_0x0002:
        r0 = r4.m;
        r0.lock();
        l = r5;	 Catch:{ Exception -> 0x002f }
        r0 = mtopsdk.common.util.TBSdkLog$LogEnable.DebugEnable;	 Catch:{ Exception -> 0x002f }
        r0 = mtopsdk.common.util.m.a(r0);	 Catch:{ Exception -> 0x002f }
        if (r0 == 0) goto L_0x0029;
    L_0x0011:
        r0 = "mtopsdk.SDKConfig";
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x002f }
        r2 = "[setGlobalApiUnit] set apiUnit succeed,apiUnit=";
        r1.<init>(r2);	 Catch:{ Exception -> 0x002f }
        r2 = r5.toString();	 Catch:{ Exception -> 0x002f }
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x002f }
        r1 = r1.toString();	 Catch:{ Exception -> 0x002f }
        mtopsdk.common.util.m.a(r0, r1);	 Catch:{ Exception -> 0x002f }
    L_0x0029:
        r0 = r4.m;
        r0.unlock();
    L_0x002e:
        return r4;
    L_0x002f:
        r0 = move-exception;
        r1 = "mtopsdk.SDKConfig";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x004e }
        r3 = "[setGlobalApiUnit] set apiUnit error ---";
        r2.<init>(r3);	 Catch:{ all -> 0x004e }
        r0 = r0.toString();	 Catch:{ all -> 0x004e }
        r0 = r2.append(r0);	 Catch:{ all -> 0x004e }
        r0 = r0.toString();	 Catch:{ all -> 0x004e }
        mtopsdk.common.util.m.d(r1, r0);	 Catch:{ all -> 0x004e }
        r0 = r4.m;
        r0.unlock();
        goto L_0x002e;
    L_0x004e:
        r0 = move-exception;
        r1 = r4.m;
        r1.unlock();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: mtopsdk.mtop.a.f.a(mtopsdk.mtop.unit.ApiUnit):mtopsdk.mtop.a.f");
    }

    public Context b() {
        return c;
    }

    public f b(int i) {
        e = i;
        return this;
    }

    public f b(String str) {
        i = str;
        a.a(AlibcConstants.TTID, str);
        return this;
    }

    public c c() {
        return d;
    }

    public f c(String str) {
        j = str;
        if (m.a(TBSdkLog$LogEnable.InfoEnable)) {
            m.b("mtopsdk.SDKConfig", "[setGlobalAppVersion]set appVersion=" + str);
        }
        return this;
    }

    public int d() {
        return f;
    }

    public int e() {
        return e;
    }

    public String f() {
        return h;
    }

    public String g() {
        return k;
    }

    public String h() {
        return g;
    }

    public EnvModeEnum i() {
        return b;
    }

    public String j() {
        return j;
    }

    public ApiUnit k() {
        return l;
    }

    public b l() {
        return n;
    }
}
