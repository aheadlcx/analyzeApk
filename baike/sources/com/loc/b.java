package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.xiaomi.mipush.sdk.Constants;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.activity.pay.PayPWDUniversalActivity;
import qsbk.app.core.model.CustomButton;

public final class b {
    bi a;

    public b() {
        this.a = null;
        this.a = bi.a();
    }

    private String a(Context context, String str, Map<String, String> map) {
        if (de.a(de.c(context)) == -1) {
            return null;
        }
        String str2;
        Map hashMap = new HashMap();
        bn cqVar = new cq();
        hashMap.clear();
        hashMap.put("Content-Type", "application/x-www-form-urlencoded");
        hashMap.put("Connection", HTTP.CONN_KEEP_ALIVE);
        hashMap.put("User-Agent", "AMAP_Location_SDK_Android 3.4.0");
        String a = m.a();
        String a2 = m.a(context, a, t.b((Map) map));
        map.put("ts", a);
        map.put("scode", a2);
        cqVar.b(map);
        cqVar.a(hashMap);
        cqVar.a(str);
        cqVar.a(q.a(context));
        cqVar.a(cw.e);
        cqVar.b(cw.e);
        try {
            bi biVar = this.a;
            str2 = new String(bi.a(cqVar), "utf-8");
        } catch (Throwable th) {
            cw.a(th, "GeoFenceNetManager", CustomButton.REQUEST_METHOD_POST);
            str2 = null;
        }
        return str2;
    }

    private static Map<String, String> b(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        Map<String, String> hashMap = new HashMap();
        hashMap.put(PayPWDUniversalActivity.KEY, k.f(context));
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("keywords", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("types", str2);
        }
        if (!(TextUtils.isEmpty(str5) || TextUtils.isEmpty(str6))) {
            hashMap.put("location", str6 + Constants.ACCEPT_TIME_SEPARATOR_SP + str5);
        }
        if (!TextUtils.isEmpty(str3)) {
            hashMap.put("city", str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            hashMap.put(ParamKey.OFFSET, str4);
        }
        if (!TextUtils.isEmpty(str7)) {
            hashMap.put("radius", str7);
        }
        return hashMap;
    }

    public final String a(Context context, String str, String str2) {
        Map b = b(context, str2, null, null, null, null, null, null);
        b.put("extensions", "all");
        return a(context, str, b);
    }

    public final String a(Context context, String str, String str2, String str3, String str4, String str5) {
        Map b = b(context, str2, str3, str4, str5, null, null, null);
        b.put("children", "1");
        b.put(ParamKey.PAGE, "1");
        b.put("extensions", "base");
        return a(context, str, b);
    }

    public final String a(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        Map b = b(context, str2, str3, null, str4, str5, str6, str7);
        b.put("children", "1");
        b.put(ParamKey.PAGE, "1");
        b.put("extensions", "base");
        return a(context, str, b);
    }
}
