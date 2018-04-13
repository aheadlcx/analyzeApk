package mtopsdk.mtop.c.a;

import com.ali.auth.third.core.model.Constants;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.taobao.tao.remotebusiness.listener.c;
import com.umeng.analytics.pro.x;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import mtopsdk.common.util.l;
import mtopsdk.common.util.m;
import mtopsdk.mtop.a.f;
import mtopsdk.mtop.a.g;
import mtopsdk.mtop.common.MtopNetworkProp;
import mtopsdk.mtop.domain.MtopRequest;
import mtopsdk.xstate.a;

public class b implements a {
    private c a = null;
    private f b = f.a();

    private Map a() {
        Map hashMap = new HashMap();
        hashMap.put("pv", "1.0");
        String a = a.a(x.ae);
        if (l.a(a)) {
            String a2 = a.a(x.af);
            if (l.a(a2)) {
                hashMap.put(x.ae, a);
                hashMap.put(x.af, a2);
            }
        }
        hashMap.put("t", String.valueOf(g.a()));
        hashMap.put("sid", a.a("sid"));
        hashMap.put("accessToken", a.a("accessToken"));
        hashMap.put("utdid", a.a("utdid"));
        hashMap.put("x-features", String.valueOf(mtopsdk.mtop.features.a.a()));
        return hashMap;
    }

    private void a(mtopsdk.mtop.a aVar, Map map) {
        MtopNetworkProp e = aVar.e();
        if (!(e.queryParameterMap == null || e.queryParameterMap.isEmpty())) {
            for (Entry entry : e.queryParameterMap.entrySet()) {
                map.put(entry.getKey(), entry.getValue());
            }
        }
        String j = this.b.j();
        if (l.a(j)) {
            map.put("x-app-ver", j);
        }
        j = a.a(Constants.UA);
        if (j != null) {
            map.put("user-agent", j);
        }
    }

    private Map b(mtopsdk.mtop.a aVar) {
        MtopRequest d = aVar.d();
        MtopNetworkProp e = aVar.e();
        Map a = a();
        a.put("api", d.getApiName().toLowerCase());
        a.put(IXAdRequestInfo.V, d.getVersion().toLowerCase());
        a.put("data", d.getData());
        a.put(AlibcConstants.TTID, l.a(e.ttid) ? e.ttid : a.a(AlibcConstants.TTID));
        String f = this.b.f();
        a.put("appKey", f);
        a.put("sid", a.a("sid"));
        if (e.wuaFlag >= 0) {
            a.get("t");
            c cVar = this.a;
            int i = e.wuaFlag;
            a.put("wua", cVar.a());
        }
        String a2 = this.a.a((HashMap) a, f);
        if (l.b(a2)) {
            StringBuilder stringBuilder = new StringBuilder(128);
            stringBuilder.append("api=").append(d.getApiName()).append(";v=").append(d.getVersion()).append(" getMtopApiWBSign  failed. [appKey=").append(f).append("]");
            m.d("mtopsdk.ProtocolParamBuilderImpl", aVar.h.g(), stringBuilder.toString());
            return null;
        }
        a.put("sign", a2);
        a(aVar, a);
        return a;
    }

    public Map a(mtopsdk.mtop.a aVar) {
        if (aVar == null || aVar.c() == null) {
            m.d("mtopsdk.ProtocolParamBuilderImpl", "[buildParams]mtopProxy or entrance is invalid.---" + aVar);
            return null;
        }
        this.a = this.b.c();
        if (this.a != null) {
            return b(aVar);
        }
        m.d("mtopsdk.ProtocolParamBuilderImpl", aVar.h.g(), "ISign for SDKConfig.getInstance().getGlobalSign is null");
        return null;
    }
}
