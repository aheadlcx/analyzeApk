package com.tencent.bugly.beta.upgrade;

import android.text.TextUtils;
import com.tencent.bugly.beta.global.e;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.proguard.ah;
import com.tencent.bugly.proguard.aj;
import com.tencent.bugly.proguard.ak;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.m;
import com.tencent.bugly.proguard.x;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class b {
    public static b a = new b();

    protected synchronized void a(int i, byte[] bArr, a aVar, boolean z, String str) {
        a b = a.b();
        try {
            Object a = ah.a(e.E.s, i, bArr);
            if (a != null) {
                e eVar = e.E;
                a.b = eVar.u;
                if (!TextUtils.isEmpty(eVar.P)) {
                    a.e = eVar.P;
                }
                if (a.k != null) {
                    a.k.put("G6", eVar.v);
                    a.k.put("G10", "1.3.4");
                    a.k.put("G3", "" + eVar.x);
                    a.k.put("G11", String.valueOf(eVar.w));
                    a.k.put("G7", "" + b.m());
                    a.k.put("G8", "" + b.k());
                    a.k.put("G9", "" + b.l());
                    a.k.put("G2", String.valueOf(com.tencent.bugly.beta.global.a.a(e.E.s)));
                    a.k.put("G12", String.valueOf(eVar.o));
                    a.k.put("A21", "" + b.g());
                    a.k.put("A22", "" + b.h());
                    a.k.put("G13", "" + eVar.J);
                    a.k.put("G14", "" + eVar.K);
                    a.k.put("G15", "" + eVar.M);
                    a.k.put("G17", "" + b.x());
                    a.k.put("C01", "" + b.H());
                    a.k.put("G18", "" + b.g());
                    Map B = b.B();
                    if (B != null && B.size() > 0) {
                        for (Entry entry : B.entrySet()) {
                            a.k.put("C03_" + ((String) entry.getKey()), entry.getValue());
                        }
                    }
                    an.c("app version is: [%s.%s], [deviceId:%s], channel: [%s], base tinkerId:[%s], patch tinkerId:[%s], patch version:[%s]", new Object[]{eVar.x, Integer.valueOf(eVar.w), b.h(), a.e, eVar.J, eVar.K, eVar.M});
                    Map hashMap = new HashMap();
                    hashMap.put("grayStrategyUpdateTime", e.E.F.b + "");
                    if (z) {
                        ak.a().a(1002, a.g, ah.a(a), str, str, aVar, 0, 1, true, hashMap);
                    } else {
                        ak.a().a(1002, a.g, ah.a(a), str, str, (aj) aVar, false, hashMap);
                    }
                }
            }
        } catch (Throwable th) {
            if (!an.a(th)) {
                th.printStackTrace();
            }
            aVar.a(i, null, 0, 0, false, "sendRequest failed");
        }
    }

    public synchronized void a(x xVar, boolean z) {
        if (xVar != null) {
            try {
                a(803, ah.a((m) xVar), new a(2, 803, xVar, Boolean.valueOf(z)), false, e.E.F.a.d);
            } catch (Throwable th) {
                if (!an.b(th)) {
                    th.printStackTrace();
                }
            }
        }
    }
}
