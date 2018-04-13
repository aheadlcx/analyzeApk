package com.a.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    public static a k;
    public String a = (Build.BRAND + " " + Build.MODEL + " " + Build.TYPE);
    public String b;
    public String c = AlibcConstants.PF_ANDROID;
    public String d = VERSION.RELEASE;
    public int e = VERSION.SDK_INT;
    public int f;
    public String g;
    public String h = "android_2.15.5.16.1";
    public String i;
    public String j;

    public static a a(Context context) {
        if (k == null) {
            k = new a(context);
        }
        return k;
    }

    private a(Context context) {
        this.j = b.a(context) + "x" + b.b(context);
        e eVar = new e(context);
        this.i = eVar.a();
        this.b = eVar.b();
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            this.f = packageInfo.versionCode;
            this.g = packageInfo.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mType", this.a);
            jSONObject.put("imei", this.b);
            jSONObject.put("osType", this.c);
            jSONObject.put("osVerRelease", this.d);
            jSONObject.put("osVerInt", this.e);
            jSONObject.put("hAppVerCode", this.f);
            jSONObject.put("hAppVerName", this.g);
            jSONObject.put("gsdkVerCode", this.h);
            jSONObject.put("phone", this.i);
            jSONObject.put("mScreen", this.j);
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }
}
