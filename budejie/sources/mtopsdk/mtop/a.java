package mtopsdk.mtop;

import android.os.Handler;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import java.util.Map;
import mtopsdk.common.util.l;
import mtopsdk.mtop.a.f;
import mtopsdk.mtop.a.i;
import mtopsdk.mtop.c.a.b;
import mtopsdk.mtop.common.MtopNetworkProp;
import mtopsdk.mtop.common.k;
import mtopsdk.mtop.domain.MtopRequest;
import mtopsdk.mtop.domain.MtopResponse;
import mtopsdk.mtop.domain.ProtocolEnum;
import mtopsdk.mtop.unit.UserUnit;
import mtopsdk.mtop.unit.UserUnit.UnitType;
import mtopsdk.mtop.util.Result;
import mtopsdk.mtop.util.h;

public class a extends b {
    private mtopsdk.mtop.c.a.a i = new b();
    private mtopsdk.mtop.d.a j = new mtopsdk.mtop.d.b();

    public a(MtopRequest mtopRequest, MtopNetworkProp mtopNetworkProp, Object obj, k kVar) {
        super(mtopRequest, mtopNetworkProp, obj, kVar);
    }

    private void h() {
        if (this.h == null) {
            this.h = new h();
            this.h.a();
            if (this.d != null) {
                this.h.p = this.d.getKey();
            }
        }
    }

    private void i() {
        String str = this.e.reqUserId;
        if (l.b(str)) {
            str = mtopsdk.xstate.a.a(HistoryOpenHelper.COLUMN_UID);
        }
        f.a().g();
        this.e.userUnit = l.b(null) ? new UserUnit(str, UnitType.CENTER, "") : new UserUnit(str, UnitType.UNIT, null);
    }

    private void j() {
        b.b();
        h();
        i();
        if (!i.a().b()) {
            this.e.protocol = ProtocolEnum.HTTP;
        }
    }

    public mtopsdk.mtop.common.a a(Handler handler) {
        j();
        Result g = g();
        if (g.isSuccess()) {
            Map a = this.i.a(this);
            if (a != null) {
                return this.j.a(this, a, handler);
            }
            a(new MtopResponse(this.d.getApiName(), this.d.getVersion(), "ANDROID_SYS_GENERATE_MTOP_SIGN_ERROR", "生成Mtop签名sign失败"));
            return new mtopsdk.mtop.common.a(null, this);
        }
        a(this.d != null ? new MtopResponse(this.d.getApiName(), this.d.getVersion(), g.getErrCode(), g.getErrInfo()) : new MtopResponse(g.getErrCode(), g.getErrInfo()));
        return new mtopsdk.mtop.common.a(null, this);
    }

    public MtopResponse a() {
        j();
        Result g = g();
        if (g.isSuccess()) {
            Map a = this.i.a(this);
            if (a == null) {
                return new MtopResponse(this.d.getApiName(), this.d.getVersion(), "ANDROID_SYS_GENERATE_MTOP_SIGN_ERROR", "生成Mtop签名sign失败");
            }
            MtopResponse a2 = this.j.a(this, a);
            this.h.f = a2.getRetCode();
            this.h.h();
            a2.setMtopStat(this.h);
            return a2;
        }
        a2 = this.d != null ? new MtopResponse(this.d.getApiName(), this.d.getVersion(), g.getErrCode(), g.getErrInfo()) : new MtopResponse(g.getErrCode(), g.getErrInfo());
        a(a2);
        return a2;
    }
}
