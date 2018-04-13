package com.loc;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

class l$b extends bj {
    private String f;
    private Map<String, String> g = null;
    private boolean h;

    l$b(Context context, s sVar, String str) {
        super(context, sVar);
        this.f = str;
        this.h = VERSION.SDK_INT != 19;
    }

    private Map<String, String> j() {
        Object q = n.q(this.a);
        if (!TextUtils.isEmpty(q)) {
            q = p.b(new StringBuilder(q).reverse().toString());
        }
        Map<String, String> hashMap = new HashMap();
        hashMap.put("authkey", this.f);
        hashMap.put("plattype", "android");
        hashMap.put("product", this.b.a());
        hashMap.put("version", this.b.b());
        hashMap.put("output", "json");
        hashMap.put("androidversion", VERSION.SDK_INT);
        hashMap.put("deviceId", q);
        if (!(this.g == null || this.g.isEmpty())) {
            hashMap.putAll(this.g);
        }
        if (VERSION.SDK_INT >= 21) {
            try {
                ApplicationInfo applicationInfo = this.a.getApplicationInfo();
                Field declaredField = Class.forName(ApplicationInfo.class.getName()).getDeclaredField("primaryCpuAbi");
                declaredField.setAccessible(true);
                q = (String) declaredField.get(applicationInfo);
            } catch (Throwable th) {
                w.a(th, "ConfigManager", "getcpu");
            }
            if (TextUtils.isEmpty(q)) {
                q = Build.CPU_ABI;
            }
            hashMap.put("abitype", q);
            hashMap.put("ext", this.b.d());
            return hashMap;
        }
        q = null;
        if (TextUtils.isEmpty(q)) {
            q = Build.CPU_ABI;
        }
        hashMap.put("abitype", q);
        hashMap.put("ext", this.b.d());
        return hashMap;
    }

    public final boolean a() {
        return this.h;
    }

    public final byte[] a_() {
        return null;
    }

    public final Map<String, String> b() {
        return null;
    }

    public final String c() {
        return this.h ? "https://restapi.amap.com/v3/iasdkauth" : "http://restapi.amap.com/v3/iasdkauth";
    }

    public final byte[] e() {
        return t.a(t.a(j()));
    }

    protected final String f() {
        return "3.0";
    }
}
