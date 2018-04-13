package mtopsdk.mtop.b;

import android.content.Context;
import mtopsdk.common.util.TBSdkLog$LogEnable;
import mtopsdk.common.util.l;
import mtopsdk.common.util.m;
import mtopsdk.mtop.a.f;
import mtopsdk.mtop.a.g;
import mtopsdk.mtop.domain.EnvModeEnum;
import mtopsdk.mtop.domain.MtopRequest;

public class a {
    private static volatile a a = null;
    private static volatile boolean b = false;

    private a() {
    }

    public static a a(Context context) {
        return a(context, null);
    }

    public static a a(Context context, String str) {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a();
                }
            }
            if (!b) {
                b(context, str);
            }
        }
        if (l.a(str)) {
            f.a().b(str);
        }
        return a;
    }

    private static synchronized void b(Context context, String str) {
        synchronized (a.class) {
            if (!b) {
                if (context == null) {
                    m.d("mtopsdk.Mtop", "[Mtop init] The Parameter context can not be null.");
                    throw new IllegalArgumentException("The Parameter context can not be null.");
                }
                if (m.a(TBSdkLog$LogEnable.DebugEnable)) {
                    m.a("mtopsdk.Mtop", "[init] ttid=" + str);
                }
                mtopsdk.mtop.a.a.a(context, str);
                b = true;
            }
        }
    }

    public a a() {
        g.c();
        return this;
    }

    public a a(String str, String str2) {
        g.a(str, str2);
        return this;
    }

    public a a(EnvModeEnum envModeEnum) {
        mtopsdk.mtop.a.a.a(envModeEnum);
        return this;
    }

    public b a(MtopRequest mtopRequest, String str) {
        return new b(mtopRequest, str);
    }
}
