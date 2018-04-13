package mtopsdk.mtop.d.a;

import com.ali.auth.third.login.LoginConstants;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.umeng.analytics.pro.x;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import mtopsdk.a.b.c;
import mtopsdk.common.util.m;
import mtopsdk.mtop.a;
import mtopsdk.mtop.common.MtopNetworkProp;
import mtopsdk.mtop.domain.MethodEnum;
import mtopsdk.mtop.domain.MtopHeaderFieldEnum;
import mtopsdk.mtop.util.h;

public class b extends a {
    private Map a(Map map, Map map2) {
        String str;
        if (map2 == null) {
            map2 = new HashMap();
        }
        for (MtopHeaderFieldEnum mtopHeaderFieldEnum : MtopHeaderFieldEnum.values()) {
            str = (String) map.remove(mtopHeaderFieldEnum.getXstateKey());
            if (str != null) {
                try {
                    map2.put(mtopHeaderFieldEnum.getHeadField(), URLEncoder.encode(str, "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    m.d("mtopsdk.Api4NetworkConverter", "[prepareRequestHeaders]urlencode " + mtopHeaderFieldEnum.getHeadField() + LoginConstants.EQUAL + str + x.aF);
                }
            }
        }
        str = (String) map.remove(x.af);
        String str2 = (String) map.remove(x.ae);
        if (!(str == null || str2 == null)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(",");
            stringBuilder.append(str2);
            try {
                map2.put("x-location", URLEncoder.encode(stringBuilder.toString(), "utf-8"));
            } catch (UnsupportedEncodingException e2) {
                m.d("mtopsdk.Api4NetworkConverter", "[prepareRequestHeaders]urlencode x-location=" + stringBuilder.toString() + x.aF);
            }
        }
        return map2;
    }

    public mtopsdk.a.b.b a(a aVar, Map map) {
        if (aVar.h == null) {
            aVar.h = new h();
        }
        MtopNetworkProp e = aVar.e();
        String g = aVar.h.g();
        c cVar = new c();
        cVar.b(g);
        cVar.a(e.connTimeout);
        cVar.b(e.socketTimeout);
        cVar.c(e.retryTimes);
        MethodEnum methodEnum = e.method;
        Map a = a(map, e.requestHeaders);
        try {
            URL a2;
            String str = (String) map.remove("api");
            String str2 = (String) map.remove(IXAdRequestInfo.V);
            String a3 = a(str, str2, aVar);
            StringBuilder stringBuilder = new StringBuilder(64);
            stringBuilder.append(aVar.c(a3));
            stringBuilder.append("/");
            stringBuilder.append(str).append("/");
            stringBuilder.append(str2).append("/");
            if (MethodEnum.POST.getMethod().equals(methodEnum.getMethod())) {
                cVar.a(methodEnum.getMethod(), new c(this, a(map, "utf-8")));
                a2 = e.a(stringBuilder.toString(), null);
            } else {
                a(a, aVar);
                a2 = e.a(stringBuilder.toString(), map);
            }
            if (a2 != null) {
                aVar.h.g = a2.getHost();
            }
            cVar.a(a2.toString());
            cVar.a(a);
            return cVar.a();
        } catch (Throwable th) {
            m.b("mtopsdk.Api4NetworkConverter", g, "[Api4NetworkConverter] convert Request failed!", th);
            return null;
        }
    }
}
