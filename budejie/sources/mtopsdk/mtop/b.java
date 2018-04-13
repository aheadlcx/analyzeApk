package mtopsdk.mtop;

import mtopsdk.common.util.TBSdkLog$LogEnable;
import mtopsdk.common.util.l;
import mtopsdk.common.util.m;
import mtopsdk.mtop.a.f;
import mtopsdk.mtop.antiattack.a;
import mtopsdk.mtop.antiattack.d;
import mtopsdk.mtop.antiattack.e;
import mtopsdk.mtop.common.MtopNetworkProp;
import mtopsdk.mtop.common.i;
import mtopsdk.mtop.common.k;
import mtopsdk.mtop.domain.EntranceEnum;
import mtopsdk.mtop.domain.EnvModeEnum;
import mtopsdk.mtop.domain.MtopRequest;
import mtopsdk.mtop.domain.MtopResponse;
import mtopsdk.mtop.util.Result;
import mtopsdk.mtop.util.c;
import mtopsdk.mtop.util.h;

public class b implements mtopsdk.mtop.domain.b {
    public static EnvModeEnum a = EnvModeEnum.ONLINE;
    public static a b = new mtopsdk.mtop.antiattack.b();
    public static d c = new e();
    private static volatile boolean l = false;
    public MtopRequest d;
    public MtopNetworkProp e = new MtopNetworkProp();
    public Object f;
    public k g;
    public h h;
    private EntranceEnum i = EntranceEnum.GW_OPEN;
    private String j;
    private String k;

    public b(MtopRequest mtopRequest, MtopNetworkProp mtopNetworkProp, Object obj, k kVar) {
        this.d = mtopRequest;
        if (mtopNetworkProp != null) {
            this.e = mtopNetworkProp;
        }
        this.f = obj;
        this.g = kVar;
    }

    private static void a() {
        EnvModeEnum i = f.a().i();
        if (i != null) {
            a = i;
        }
        mtopsdk.mtop.a.a.a();
        l = true;
    }

    protected static void b() {
        if (!l) {
            synchronized (b.class) {
                if (!l) {
                    a();
                }
            }
        }
    }

    public void a(String str) {
        this.j = str;
    }

    public void a(MtopResponse mtopResponse) {
        if (mtopResponse != null && (this.g instanceof mtopsdk.mtop.common.e)) {
            ((mtopsdk.mtop.common.e) this.g).onFinished(new i(mtopResponse), this.f);
        }
    }

    public void b(String str) {
        this.k = str;
    }

    public String c(String str) {
        try {
            EnvModeEnum envModeEnum = a;
            this.e.envMode = envModeEnum;
            if (l.a(this.k)) {
                StringBuilder stringBuilder = new StringBuilder(40);
                stringBuilder.append(this.e.protocol.getProtocol());
                if (l.a(str)) {
                    stringBuilder.append(str);
                }
                stringBuilder.append(this.k).append("/");
                stringBuilder.append(this.i.getEntrance());
                return stringBuilder.toString();
            }
            if (l.b(this.j)) {
                StringBuilder stringBuilder2 = new StringBuilder(40);
                stringBuilder2.append(this.e.protocol.getProtocol());
                if (l.a(str)) {
                    stringBuilder2.append(str);
                }
                stringBuilder2.append(c.a[envModeEnum.getEnvMode()]);
                stringBuilder2.append(this.i.getEntrance());
                return stringBuilder2.toString();
            }
            return this.j;
        } catch (Exception e) {
            m.d("mtopsdk.MtopProxyBase", "[getFullBaseUrl] create MtopProxyBase fullbaseurl error ---" + e.toString());
        }
    }

    public EntranceEnum c() {
        return this.i;
    }

    public MtopRequest d() {
        return this.d;
    }

    public MtopNetworkProp e() {
        return this.e;
    }

    public k f() {
        return this.g;
    }

    protected Result g() {
        String g = this.h.g();
        if (this.d == null || !this.d.isLegalRequest()) {
            String str = "mtopRequest is invalid." + (this.d != null ? this.d.toString() : "mtopRequest=null");
            m.d("mtopsdk.MtopProxyBase", g, "[validateBusinessInit]" + str);
            return new Result(false, "ANDROID_SYS_MTOPPROXYBASE_INIT_ERROR", str);
        }
        if (m.a(TBSdkLog$LogEnable.DebugEnable)) {
            m.a("mtopsdk.MtopProxyBase", g, "[validateBusinessInit]" + this.d.toString());
        }
        if (this.e != null) {
            return new Result(Boolean.valueOf(true));
        }
        str = "MtopNetworkProp is invalid.";
        m.d("mtopsdk.MtopProxyBase", g, "[validateBusinessInit]" + str);
        return new Result(false, "ANDROID_SYS_MTOPPROXYBASE_INIT_ERROR", str);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append("MtopProxyBase [entrance=").append(this.i);
        stringBuilder.append(", fullBaseUrl=").append(this.j);
        stringBuilder.append(", customDomain=").append(this.k);
        stringBuilder.append(", mtopRequest=").append(this.d);
        stringBuilder.append(", property=").append(this.e);
        stringBuilder.append(", context=").append(this.f);
        stringBuilder.append(", callback=").append(this.g);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
