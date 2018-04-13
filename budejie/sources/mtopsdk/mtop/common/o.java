package mtopsdk.mtop.common;

import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import mtopsdk.a.b.e;
import mtopsdk.a.f;
import mtopsdk.common.util.TBSdkLog$LogEnable;
import mtopsdk.common.util.m;
import mtopsdk.mtop.a;
import mtopsdk.mtop.domain.MtopRequest;
import mtopsdk.mtop.domain.MtopResponse;
import mtopsdk.mtop.util.h;

public class o implements f {
    public e a;
    public f b;
    public h c = null;
    private a d;
    private Object e;

    public o(a aVar) {
        this.d = aVar;
        this.e = aVar.f;
    }

    private String a() {
        return this.c != null ? this.c.g() : null;
    }

    private void a(h hVar) {
        if (hVar != null) {
            hVar.b();
            hVar.h();
            if (m.a(TBSdkLog$LogEnable.DebugEnable)) {
                m.a("mtopsdk.MtopStatistics", a(), hVar.toString());
            }
        }
    }

    private int b() {
        String a = a();
        return a != null ? a.hashCode() : hashCode();
    }

    private void c(e eVar, Object obj) {
        if (this.c == null) {
            this.c = new h();
        }
        this.c.f();
        if (eVar != null) {
            this.c.a(eVar.d());
            this.c.e = eVar.a();
        }
        String g = this.c.g();
        if (this.a == null) {
            m.d("mtopsdk.NetworkListenerAdapter", g, "[onFinishTask]finishListener is null");
        } else if (this.d == null) {
            m.d("mtopsdk.NetworkListenerAdapter", g, "[onFinishTask]mtopProxy is null");
        } else {
            MtopRequest mtopRequest = this.d.d;
            MtopResponse mtopResponse = new MtopResponse(mtopRequest.getApiName(), mtopRequest.getVersion(), null, null);
            mtopResponse.setMtopStat(this.c);
            i iVar = new i(mtopResponse);
            if (eVar == null) {
                mtopResponse.setRetCode("ANDROID_SYS_NETWORK_ERROR");
                mtopResponse.setRetMsg(UserTrackerConstants.EM_NETWORK_ERROR);
                a(this.c);
                try {
                    this.a.onFinished(iVar, obj);
                    return;
                } catch (Throwable th) {
                    m.b("mtopsdk.NetworkListenerAdapter", g, "[onFinishTask]finishListener error --apiKey=" + mtopRequest.getKey(), th);
                    return;
                }
            }
            this.c.c();
            iVar.a = l.a(mtopResponse, null, this.d, new m(eVar.a(), eVar.b(), eVar.c()));
            this.c.d();
            this.c.f = mtopResponse.getRetCode();
            a(this.c);
            try {
                this.a.onFinished(iVar, obj);
            } catch (Throwable th2) {
                m.b("mtopsdk.NetworkListenerAdapter", g, "[onFinishTask]finishListener error --apiKey=" + mtopRequest.getKey(), th2);
            }
        }
    }

    public void a(mtopsdk.a.a aVar) {
        b(new mtopsdk.a.b.f().a(aVar.a()).a(-8).a(), this.e);
    }

    public void a(mtopsdk.a.a aVar, Exception exception) {
        b(new mtopsdk.a.b.f().a(aVar.a()).a(-7).a(exception.getMessage()).a(), this.e);
    }

    public void a(mtopsdk.a.a aVar, e eVar) {
        a(eVar, this.e);
        b(eVar, this.e);
    }

    public void a(e eVar, Object obj) {
        mtopsdk.mtop.util.e.a(b(), new p(this, eVar, obj));
    }

    public void b(e eVar, Object obj) {
        mtopsdk.mtop.util.e.a(b(), new q(this, eVar, obj));
    }
}
