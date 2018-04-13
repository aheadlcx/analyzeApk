package mtopsdk.mtop.d;

import android.os.Handler;
import java.util.Map;
import mtopsdk.a.a;
import mtopsdk.a.b.e;
import mtopsdk.common.util.TBSdkLog$LogEnable;
import mtopsdk.common.util.m;
import mtopsdk.mtop.a.f;
import mtopsdk.mtop.a.g;
import mtopsdk.mtop.antiattack.c;
import mtopsdk.mtop.common.l;
import mtopsdk.mtop.domain.MtopRequest;
import mtopsdk.mtop.domain.MtopResponse;
import mtopsdk.mtop.util.d;
import mtopsdk.mtop.util.h;

public class b implements a {
    private a a(mtopsdk.a.b.b bVar) {
        return f.a().l().a(bVar);
    }

    public mtopsdk.mtop.common.a a(mtopsdk.mtop.a aVar, Map map, Handler handler) {
        Throwable e;
        if (aVar.h == null) {
            aVar.h = new h();
        }
        String g = aVar.h.g();
        MtopRequest d = aVar.d();
        String key = d.getKey();
        if (d.a().contains(key) || !c.a(key, g.a())) {
            a a;
            mtopsdk.a.b.b b = b(aVar, map);
            mtopsdk.a.f a2 = d.a(aVar);
            if (a2 != null) {
                a2.c = aVar.h;
            }
            try {
                aVar.h.e();
                a = a(b);
                try {
                    a.a(a2);
                } catch (Exception e2) {
                    e = e2;
                    m.b("mtopsdk.MtopTransformImpl", g, "[asyncTransform] invoke call.enqueue error :apiKey=" + d.getKey(), e);
                    return new mtopsdk.mtop.common.a(a, aVar);
                }
            } catch (Throwable e3) {
                Throwable th = e3;
                a = null;
                e = th;
                m.b("mtopsdk.MtopTransformImpl", g, "[asyncTransform] invoke call.enqueue error :apiKey=" + d.getKey(), e);
                return new mtopsdk.mtop.common.a(a, aVar);
            }
            return new mtopsdk.mtop.common.a(a, aVar);
        }
        aVar.a(new MtopResponse(d.getApiName(), d.getVersion(), "ANDROID_SYS_API_FLOW_LIMIT_LOCKED", "哎哟喂,被挤爆啦,请稍后重试"));
        if (m.a(TBSdkLog$LogEnable.WarnEnable)) {
            m.c("mtopsdk.MtopTransformImpl", "[asyncTransform] ANDROID_SYS_API_FLOW_LIMIT_LOCKED apiKey=" + key);
        }
        return new mtopsdk.mtop.common.a(null, aVar);
    }

    public MtopResponse a(mtopsdk.mtop.a aVar, Map map) {
        Throwable th;
        if (aVar.h == null) {
            aVar.h = new h();
        }
        String g = aVar.h.g();
        MtopRequest d = aVar.d();
        String key = d.getKey();
        MtopResponse a;
        if (d.a().contains(key) || !c.a(key, g.a())) {
            e b;
            mtopsdk.a.b.b b2 = b(aVar, map);
            try {
                aVar.h.e();
                b = a(b2).b();
                try {
                    aVar.h.f();
                    if (b != null) {
                        aVar.h.a(b.d());
                    }
                } catch (Throwable th2) {
                    th = th2;
                    m.b("mtopsdk.MtopTransformImpl", g, "[syncTransform] invoke call.execute error :apiKey=" + d.getKey(), th);
                    aVar.h.c();
                    a = l.a(b, null, aVar);
                    aVar.h.d();
                    return a;
                }
            } catch (Throwable th3) {
                th = th3;
                b = null;
                m.b("mtopsdk.MtopTransformImpl", g, "[syncTransform] invoke call.execute error :apiKey=" + d.getKey(), th);
                aVar.h.c();
                a = l.a(b, null, aVar);
                aVar.h.d();
                return a;
            }
            aVar.h.c();
            a = l.a(b, null, aVar);
            aVar.h.d();
            return a;
        }
        a = new MtopResponse(d.getApiName(), d.getVersion(), "ANDROID_SYS_API_FLOW_LIMIT_LOCKED", "哎哟喂,被挤爆啦,请稍后重试");
        if (!m.a(TBSdkLog$LogEnable.WarnEnable)) {
            return a;
        }
        m.c("mtopsdk.MtopTransformImpl", g, "[syncTransform] ANDROID_SYS_API_FLOW_LIMIT_LOCKED apiKey=" + key);
        return a;
    }

    public mtopsdk.a.b.b b(mtopsdk.mtop.a aVar, Map map) {
        return (aVar == null || map == null) ? null : new mtopsdk.mtop.d.a.b().a(aVar, map);
    }
}
