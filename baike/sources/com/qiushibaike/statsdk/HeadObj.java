package com.qiushibaike.statsdk;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import org.json.JSONException;
import org.json.JSONObject;

public class HeadObj {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j = "0";

    private static String a(String str) {
        return str == null ? "" : str;
    }

    private static void a(Context context, String str) {
        if ((context.checkCallingOrSelfPermission(str) == 0 ? 1 : null) == null) {
            String str2 = "Permission Denial: requires permission " + str;
            L.e(StatSDK.class.getSimpleName(), str2);
            throw new SecurityException(str2);
        }
    }

    public String getAppChannel() {
        return this.b;
    }

    public void setAppChannel(String str) {
        this.b = str;
    }

    public String getAppVersionName() {
        return this.c;
    }

    public void setAppVersionName(String str) {
        this.c = str;
    }

    public String getCUID() {
        return this.d;
    }

    public void setCUID(String str) {
        this.d = str;
    }

    public String getExtra() {
        return this.i;
    }

    public void setExtra(String str) {
        this.i = str;
    }

    public synchronized void constructHeader(Context context, String str) {
        this.a = str;
        this.e = Build.FINGERPRINT;
        Resources resources = context.getResources();
        this.h = resources.getDisplayMetrics().widthPixels + "_" + resources.getDisplayMetrics().heightPixels;
        try {
            a(context, "android.permission.READ_PHONE_STATE");
        } catch (SecurityException e) {
        }
        try {
            a(context, "android.permission.WRITE_SETTINGS");
        } catch (SecurityException e2) {
        }
        try {
            a(context, "android.permission.ACCESS_NETWORK_STATE");
        } catch (SecurityException e3) {
        }
        try {
            a(context, "android.permission.INTERNET");
        } catch (SecurityException e4) {
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting() && activeNetworkInfo.getType() == 1) {
                this.j = "1";
            }
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            this.g = telephonyManager.getNetworkOperator();
            this.f = telephonyManager.getNetworkType() + "";
        } catch (SecurityException e5) {
            if (TextUtils.isEmpty(this.j)) {
                this.j = "0";
            }
            if (TextUtils.isEmpty(this.g)) {
                this.g = "";
            }
            if (TextUtils.isEmpty(this.f)) {
                this.f = "0";
            }
        }
    }

    public synchronized void installHeader(Context context, JSONObject jSONObject, String str) {
        constructHeader(context, str);
        try {
            jSONObject.put("a", this.a);
            jSONObject.put("i", a(this.d));
            jSONObject.put(Config.OS, a(this.e));
            jSONObject.put("s", a(this.h));
            jSONObject.put("x", Constant.VERSION);
            jSONObject.put("p", a(this.b));
            jSONObject.put("v", a(this.c));
            jSONObject.put(Config.EXCEPTION_PART, a(this.i));
            jSONObject.put("nt", a(this.f));
            jSONObject.put("no", a(this.g));
            jSONObject.put(Config.PROCESS_LABEL, "Android");
            jSONObject.put("t", System.currentTimeMillis());
            jSONObject.put("w", this.j);
        } catch (JSONException e) {
        }
    }
}
