package com.alibaba.baichuan.android.trade.c.a.a;

import android.os.Looper;
import com.ali.auth.third.core.model.Constants;
import com.alibaba.baichuan.android.trade.AlibcContext;
import com.alibaba.baichuan.android.trade.c.a.a.a.c;
import com.alibaba.baichuan.android.trade.c.a.a.a.d;
import com.alibaba.baichuan.android.trade.c.a.a.b.f;
import com.alibaba.baichuan.android.trade.c.a.b.h;
import com.alibaba.baichuan.android.trade.c.a.b.i;
import com.alibaba.baichuan.android.trade.c.a.b.k;
import com.alibaba.baichuan.android.trade.constants.UrlConstants;
import com.alibaba.baichuan.android.trade.utils.j;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import com.tencent.stat.DeviceInfo;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class e {
    private static final e a = new e();
    private volatile Map b = new LinkedHashMap();
    private volatile Map c = new LinkedHashMap();
    private d d;
    private volatile boolean e;
    private LinkedHashMap f = new LinkedHashMap();
    private LinkedHashMap g = new LinkedHashMap();

    private static class a {
        public Boolean a;

        private a() {
        }
    }

    private d a(boolean z) {
        d[] dVarArr = (d[]) this.g.values().toArray(new d[0]);
        if (z) {
            j.a(dVarArr);
        }
        com.alibaba.baichuan.android.trade.c.a.a.b.d[] dVarArr2 = (com.alibaba.baichuan.android.trade.c.a.a.b.d[]) this.f.values().toArray(new com.alibaba.baichuan.android.trade.c.a.a.b.d[0]);
        if (z) {
            j.a(dVarArr2);
        }
        d dVar = new d();
        dVar.a = 0;
        dVar.c = new LinkedHashMap();
        dVar.b = new LinkedHashMap();
        for (d dVar2 : dVarArr) {
            dVar.c.put(dVar2.a, dVar2);
        }
        for (com.alibaba.baichuan.android.trade.c.a.a.b.d dVar3 : dVarArr2) {
            dVar.b.put(dVar3.a, dVar3);
        }
        return dVar;
    }

    public static e a() {
        return a;
    }

    private void a(c cVar) {
        for (Entry value : this.c.entrySet()) {
            com.alibaba.baichuan.android.trade.c.a.a.a.a aVar = (com.alibaba.baichuan.android.trade.c.a.a.a.a) value.getValue();
            if (a(cVar.b(), aVar.a().g) && !Constants.SERVICE_SCOPE_FLAG_VALUE.equals(cVar.b("skip_" + aVar.a().a)) && a(cVar.e(), aVar.a().h)) {
                try {
                    if (aVar.a(cVar)) {
                        cVar.h();
                        if (!aVar.a().i) {
                            return;
                        }
                    } else {
                        continue;
                    }
                } catch (Throwable e) {
                    String str = DeviceInfo.TAG_IMEI;
                    StringBuilder append = new StringBuilder().append("Fail to execute the Filter ");
                    String str2 = (aVar == null || aVar.a() == null) ? null : aVar.a().a;
                    AlibcLogger.e(str, append.append(str2).toString(), e);
                }
            }
        }
    }

    private boolean a(int i, int[] iArr) {
        if (iArr == null) {
            return true;
        }
        for (int i2 : iArr) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }

    private boolean a(com.alibaba.baichuan.android.trade.c.a.a.b.a aVar, com.alibaba.baichuan.android.trade.c.a.a.b.c cVar, boolean z) {
        String str = null;
        if (!aVar.a().l || z) {
            try {
                return aVar.a(cVar);
            } catch (Throwable th) {
                String str2 = DeviceInfo.TAG_IMEI;
                StringBuilder append = new StringBuilder().append("fail to execute the Handler ");
                if (!(aVar == null || aVar.a() == null)) {
                    str = aVar.a().a;
                }
                AlibcLogger.e(str2, append.append(str).toString(), th);
                return false;
            }
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        a aVar2 = new a();
        AlibcContext.executorService.b(new f(this, aVar2, aVar, cVar, countDownLatch));
        try {
            countDownLatch.await(3, TimeUnit.SECONDS);
        } catch (Exception e) {
        }
        return aVar2.a == null ? false : aVar2.a.booleanValue();
    }

    private boolean a(com.alibaba.baichuan.android.trade.c.a.a.b.c cVar) {
        boolean z = Looper.myLooper() == Looper.getMainLooper();
        for (Entry value : this.b.entrySet()) {
            com.alibaba.baichuan.android.trade.c.a.a.b.a aVar = (com.alibaba.baichuan.android.trade.c.a.a.b.a) value.getValue();
            if (a(cVar.b(), aVar.a().g) && !Constants.SERVICE_SCOPE_FLAG_VALUE.equals(cVar.b("skip_" + aVar.a().a)) && a(cVar.e(), aVar.a().h) && a(aVar, cVar, z)) {
                return true;
            }
        }
        return false;
    }

    private boolean a(String str, String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return true;
        }
        if (str == null) {
            return false;
        }
        for (Object equals : strArr) {
            if (str.equals(equals)) {
                return true;
            }
        }
        return false;
    }

    private void c() {
        com.alibaba.baichuan.android.trade.c.a.a.a.e a = com.alibaba.baichuan.android.trade.c.a.a.a.e.a("addAllParamsFilter");
        a.a("addAllParamsUrlsStartMatches", "pattern", f.a, UrlConstants.RE_ADD_PARAM_URLS);
        a.a(new int[]{2, 1});
        Map hashMap = new HashMap();
        hashMap.put("key", "addAllParams");
        hashMap.put("value", "${ddAllParams}");
        hashMap.put("mode", "addIfAbsent");
        a.a("addAllParams", "updateParameter", hashMap);
        a.a(a.a());
    }

    private void d() {
        a.a(com.alibaba.baichuan.android.trade.c.a.a.b.e.a("loginHandler").a("login", "pattern", f.a, UrlConstants.RE_LOGIN_URLS).a(new com.alibaba.baichuan.android.trade.c.a.b.d()).a(new int[]{2}).a());
        a.a(com.alibaba.baichuan.android.trade.c.a.a.b.e.a("logoutHandler").a("logout", "pattern", f.a, UrlConstants.RE_LOGOUT_URLS).a(new com.alibaba.baichuan.android.trade.c.a.b.f()).a(new int[]{2}).a());
        a.a(com.alibaba.baichuan.android.trade.c.a.a.b.e.a("alipayHandlerInfo").a("alipay", "pattern", f.a, UrlConstants.RE_ALIPAY_URLS).a(new com.alibaba.baichuan.android.trade.c.a.b.c()).a(new int[]{2}).a());
        a.a(com.alibaba.baichuan.android.trade.c.a.a.b.e.a("tbopenHandlerInfo").a("tbopen", "pattern", f.b, UrlConstants.RE_TBOPEN_URLS).a(new k()).a(new int[]{2}).a());
        com.alibaba.baichuan.android.trade.c.a.a.b.e a = com.alibaba.baichuan.android.trade.c.a.a.b.e.a("addCartHandler");
        a.a("addCartUrlMatches", "pattern", f.a, UrlConstants.RE_ADDCART_URLS);
        a.a(new com.alibaba.baichuan.android.trade.c.a.b.a());
        a.a(true);
        a.a(a.a());
        a = com.alibaba.baichuan.android.trade.c.a.a.b.e.a("nativeTaobaoShopHandlerInfoBuilder");
        a.a("showShopNativeMatches", "pattern", f.a, UrlConstants.RE_SHOP_URLS);
        a.a(new String[]{"noForceH5"});
        a.a(new i());
        a.a(new int[]{2});
        a.a(a.a());
        a = com.alibaba.baichuan.android.trade.c.a.a.b.e.a("nativeTaobaoDetailHandlerInfoBuilder");
        a.a(new String[]{"noForceH5"});
        a.a("showDetailNativeMatches", "pattern", f.a, UrlConstants.RE_DETAIL_URLS);
        a.a(new h());
        a.a(new int[]{2});
        a.a(a.a());
    }

    private void e() {
        try {
            Map linkedHashMap = new LinkedHashMap();
            for (Entry entry : this.d.c.entrySet()) {
                linkedHashMap.put(entry.getKey(), ((d) entry.getValue()).a());
            }
            this.c = linkedHashMap;
            linkedHashMap = new LinkedHashMap();
            for (Entry entry2 : this.d.b.entrySet()) {
                linkedHashMap.put(entry2.getKey(), ((com.alibaba.baichuan.android.trade.c.a.a.b.d) entry2.getValue()).a());
            }
            this.b = linkedHashMap;
        } catch (Throwable e) {
            AlibcLogger.e(DeviceInfo.TAG_IMEI, "fail to initialize filter/handler", e);
        }
    }

    private void f() {
        AlibcLogger.d("InterceptionManager", "InterceptionManager info is not found in disk, use default");
        this.d = a(true);
    }

    public String a(com.alibaba.baichuan.android.trade.c.a.a.c.a aVar) {
        if (aVar.d == null) {
            return null;
        }
        c cVar = new c(aVar.e, aVar.d, aVar.i);
        cVar.c(aVar.f);
        if (aVar.j != null) {
            for (String str : aVar.j) {
                cVar.a("skip_" + str, Constants.SERVICE_SCOPE_FLAG_VALUE);
            }
        }
        a(cVar);
        return cVar.d();
    }

    public void a(d dVar) {
        this.g.put(dVar.a, dVar);
    }

    public void a(com.alibaba.baichuan.android.trade.c.a.a.b.d dVar) {
        this.f.put(dVar.a, dVar);
    }

    public b b(com.alibaba.baichuan.android.trade.c.a.a.c.a aVar) {
        if (aVar.d == null) {
            return new b(false, aVar.d);
        }
        if (!this.e) {
            b();
        }
        c cVar = new c(aVar.e, aVar.d, aVar.i);
        cVar.c(aVar.f);
        a(cVar);
        com.alibaba.baichuan.android.trade.c.a.a.b.c cVar2 = new com.alibaba.baichuan.android.trade.c.a.a.b.c(aVar.e, cVar.d(), aVar.i);
        cVar2.c(aVar.f);
        if (aVar.e == 1 || aVar.e == 2) {
            cVar2.d = aVar.a;
            cVar2.e = aVar.b;
            cVar2.g = aVar.g;
        } else if (aVar.e == 3) {
            cVar2.g = aVar.g;
        } else if (aVar.e == 4) {
            cVar2.f = aVar.c;
        }
        cVar2.b.putAll(cVar.b);
        if (aVar.h != null) {
            boolean z = Looper.getMainLooper() == Looper.myLooper();
            for (Object obj : aVar.h) {
                com.alibaba.baichuan.android.trade.c.a.a.b.a aVar2 = (com.alibaba.baichuan.android.trade.c.a.a.b.a) this.b.get(obj);
                if (aVar2 != null) {
                    if (a(cVar2.e(), aVar2.a().h) && a(cVar2.b(), aVar2.a().g) && a(aVar2, cVar2, z)) {
                        return new b(true, cVar2.d());
                    }
                    cVar2.a("skip_" + aVar2.a().a, Constants.SERVICE_SCOPE_FLAG_VALUE);
                }
            }
        }
        return new b(a(cVar2), cVar2.d());
    }

    public void b() {
        if (!this.e) {
            synchronized (this) {
                if (this.e) {
                    return;
                }
                c();
                d();
                f();
                e();
                this.e = true;
            }
        }
    }
}
