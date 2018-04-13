package com.tencent.bugly.proguard;

import android.content.Context;
import com.tencent.bugly.crashreport.biz.UserInfoBean;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.crashreport.common.info.b;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ah {
    public static bh a(UserInfoBean userInfoBean, a aVar) {
        if (userInfoBean == null) {
            return null;
        }
        bh bhVar = new bh();
        bhVar.a = userInfoBean.e;
        bhVar.e = userInfoBean.j;
        bhVar.d = userInfoBean.c;
        bhVar.c = userInfoBean.d;
        bhVar.g = a.b().i();
        bhVar.h = userInfoBean.o == 1;
        switch (userInfoBean.b) {
            case 1:
                bhVar.b = (byte) 1;
                break;
            case 2:
                bhVar.b = (byte) 4;
                break;
            case 3:
                bhVar.b = (byte) 2;
                break;
            case 4:
                bhVar.b = (byte) 3;
                break;
            default:
                if (userInfoBean.b >= 10 && userInfoBean.b < 20) {
                    bhVar.b = (byte) userInfoBean.b;
                    break;
                }
                an.e("unknown uinfo type %d ", new Object[]{Integer.valueOf(userInfoBean.b)});
                return null;
                break;
        }
        bhVar.f = new HashMap();
        if (userInfoBean.p >= 0) {
            bhVar.f.put("C01", "" + userInfoBean.p);
        }
        if (userInfoBean.q >= 0) {
            bhVar.f.put("C02", "" + userInfoBean.q);
        }
        if (userInfoBean.r != null && userInfoBean.r.size() > 0) {
            for (Entry entry : userInfoBean.r.entrySet()) {
                bhVar.f.put("C03_" + ((String) entry.getKey()), entry.getValue());
            }
        }
        if (userInfoBean.s != null && userInfoBean.s.size() > 0) {
            for (Entry entry2 : userInfoBean.s.entrySet()) {
                bhVar.f.put("C04_" + ((String) entry2.getKey()), entry2.getValue());
            }
        }
        bhVar.f.put("A36", "" + (!userInfoBean.l));
        bhVar.f.put("F02", "" + userInfoBean.g);
        bhVar.f.put("F03", "" + userInfoBean.h);
        bhVar.f.put("F04", "" + userInfoBean.j);
        bhVar.f.put("F05", "" + userInfoBean.i);
        bhVar.f.put("F06", "" + userInfoBean.m);
        bhVar.f.put("F10", "" + userInfoBean.k);
        an.c("summary type %d vm:%d", new Object[]{Byte.valueOf(bhVar.b), Integer.valueOf(bhVar.f.size())});
        return bhVar;
    }

    public static bi a(List<UserInfoBean> list, int i) {
        if (list == null || list.size() == 0) {
            return null;
        }
        a b = a.b();
        if (b == null) {
            return null;
        }
        b.t();
        bi biVar = new bi();
        biVar.b = b.e;
        biVar.c = b.h();
        ArrayList arrayList = new ArrayList();
        for (UserInfoBean a : list) {
            bh a2 = a(a, b);
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        biVar.d = arrayList;
        biVar.e = new HashMap();
        biVar.e.put("A7", "" + b.k);
        biVar.e.put("A6", "" + b.s());
        biVar.e.put("A5", "" + b.r());
        biVar.e.put("A2", "" + b.p());
        biVar.e.put("A1", "" + b.p());
        biVar.e.put("A24", "" + b.m);
        biVar.e.put("A17", "" + b.q());
        biVar.e.put("A15", "" + b.w());
        biVar.e.put("A13", "" + b.x());
        biVar.e.put("F08", "" + b.A);
        biVar.e.put("F09", "" + b.B);
        Map G = b.G();
        if (G != null && G.size() > 0) {
            for (Entry entry : G.entrySet()) {
                biVar.e.put("C04_" + ((String) entry.getKey()), entry.getValue());
            }
        }
        switch (i) {
            case 1:
                biVar.a = (byte) 1;
                break;
            case 2:
                biVar.a = (byte) 2;
                break;
            default:
                an.e("unknown up type %d ", new Object[]{Integer.valueOf(i)});
                return null;
        }
        return biVar;
    }

    public static <T extends m> T a(byte[] bArr, Class<T> cls) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        try {
            m mVar = (m) cls.newInstance();
            k kVar = new k(bArr);
            kVar.a("utf-8");
            mVar.a(kVar);
            return mVar;
        } catch (Throwable th) {
            if (!an.b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static bd a(Context context, int i, byte[] bArr) {
        a b = a.b();
        StrategyBean c = com.tencent.bugly.crashreport.common.strategy.a.a().c();
        if (b == null || c == null) {
            an.e("Can not create request pkg for parameters is invalid.", new Object[0]);
            return null;
        }
        try {
            bd bdVar = new bd();
            synchronized (b) {
                bdVar.a = b.c;
                bdVar.b = b.f();
                bdVar.c = b.d;
                bdVar.d = b.o;
                bdVar.e = b.q;
                b.getClass();
                bdVar.f = "2.6.5";
                bdVar.g = i;
                bdVar.h = bArr == null ? "".getBytes() : bArr;
                bdVar.i = b.l;
                bdVar.j = b.m;
                bdVar.k = new HashMap();
                bdVar.l = b.e();
                bdVar.m = c.p;
                bdVar.o = b.h();
                bdVar.p = b.f(context);
                bdVar.q = System.currentTimeMillis();
                bdVar.r = "" + b.k();
                bdVar.s = b.j();
                bdVar.t = "" + b.m();
                bdVar.u = b.l();
                bdVar.v = "" + b.n();
                bdVar.w = bdVar.p;
                b.getClass();
                bdVar.n = "com.tencent.bugly";
                bdVar.k.put("A26", "" + b.y());
                bdVar.k.put("A60", "" + b.z());
                bdVar.k.put("A61", "" + b.A());
                bdVar.k.put("F11", "" + b.E);
                bdVar.k.put("F12", "" + b.D);
                bdVar.k.put("G1", "" + b.u());
                if (b.G) {
                    bdVar.k.put("G2", "" + b.M());
                    bdVar.k.put("G3", "" + b.N());
                    bdVar.k.put("G4", "" + b.O());
                    bdVar.k.put("G5", "" + b.P());
                    bdVar.k.put("G6", "" + b.Q());
                    bdVar.k.put("G7", "" + Long.toString(b.R()));
                }
                bdVar.k.put("D3", "" + b.p);
                if (com.tencent.bugly.b.b != null) {
                    for (com.tencent.bugly.a aVar : com.tencent.bugly.b.b) {
                        if (!(aVar.versionKey == null || aVar.version == null)) {
                            bdVar.k.put(aVar.versionKey, aVar.version);
                        }
                    }
                }
                bdVar.k.put("G15", ap.c("G15", ""));
                bdVar.k.put("D4", ap.c("D4", "0"));
            }
            ak a = ak.a();
            if (!(a == null || a.b || bArr == null)) {
                bdVar.h = ap.a(bdVar.h, 2, 1, c.u);
                if (bdVar.h == null) {
                    an.e("reqPkg sbuffer error!", new Object[0]);
                    return null;
                }
            }
            Map F = b.F();
            if (F != null) {
                for (Entry entry : F.entrySet()) {
                    bdVar.k.put(entry.getKey(), entry.getValue());
                }
            }
            return bdVar;
        } catch (Throwable th) {
            if (!an.b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static byte[] a(Object obj) {
        try {
            e eVar = new e();
            eVar.b();
            eVar.a("utf-8");
            eVar.a(1);
            eVar.b("RqdServer");
            eVar.c("sync");
            eVar.a("detail", obj);
            return eVar.a();
        } catch (Throwable th) {
            if (!an.b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static be a(byte[] bArr, StrategyBean strategyBean, boolean z) {
        if (bArr != null) {
            try {
                be beVar;
                e eVar = new e();
                eVar.b();
                eVar.a("utf-8");
                eVar.a(bArr);
                Object b = eVar.b("detail", new be());
                if (be.class.isInstance(b)) {
                    beVar = (be) be.class.cast(b);
                } else {
                    beVar = null;
                }
                if (z || beVar == null || beVar.c == null || beVar.c.length <= 0) {
                    return beVar;
                }
                an.c("resp buf %d", new Object[]{Integer.valueOf(beVar.c.length)});
                beVar.c = ap.b(beVar.c, 2, 1, StrategyBean.d);
                if (beVar.c != null) {
                    return beVar;
                }
                an.e("resp sbuffer error!", new Object[0]);
                return null;
            } catch (Throwable th) {
                if (!an.b(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    public static byte[] a(m mVar) {
        try {
            l lVar = new l();
            lVar.a("utf-8");
            mVar.a(lVar);
            return lVar.b();
        } catch (Throwable th) {
            if (!an.b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }
}
