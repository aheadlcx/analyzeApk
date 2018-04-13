package com.baidu.mobstat;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

class bu {
    static String a = "Android";
    boolean b = false;
    String c;
    String d;
    String e = "0";
    String f = null;
    String g = null;
    int h = -1;
    String i;
    String j;
    int k;
    int l;
    String m = null;
    String n;
    String o;
    String p;
    String q;
    String r;
    String s;
    String t;
    String u;
    String v;
    String w;
    String x;
    String y;
    JSONObject z;

    bu() {
    }

    public synchronized void a(Context context, JSONObject jSONObject) {
        a(context);
        if (jSONObject.length() > 10) {
            db.a("header has been installed; header is:" + jSONObject);
        } else {
            b(context, jSONObject);
        }
    }

    public synchronized void a(Context context) {
        if (!this.b) {
            cu.e(context, "android.permission.READ_PHONE_STATE");
            cu.e(context, "android.permission.INTERNET");
            cu.e(context, "android.permission.ACCESS_NETWORK_STATE");
            cu.e(context, "android.permission.WRITE_SETTINGS");
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            this.c = CooperService.a().getOSVersion();
            this.d = CooperService.a().getOSSysVersion();
            this.o = CooperService.a().getPhoneModel();
            this.p = CooperService.a().getManufacturer();
            this.y = CooperService.a().getUUID();
            this.z = CooperService.a().getHeaderExt(context);
            this.j = CooperService.a().getDeviceId(telephonyManager, context);
            this.e = bj.a().j(context) ? "1" : "0";
            if (de.s(context)) {
                this.e = "2";
            }
            this.e += "-0";
            try {
                this.t = CooperService.a().getMacAddress(context, CooperService.a().isDeviceMacEnabled(context));
            } catch (Throwable e) {
                db.a(e);
            }
            try {
                this.v = de.f(1, context);
            } catch (Throwable e2) {
                db.a(e2);
            }
            try {
                this.w = de.a(context, 1);
            } catch (Throwable e22) {
                db.a(e22);
            }
            this.g = CooperService.a().getCUID(context, true);
            try {
                this.n = CooperService.a().getOperator(telephonyManager);
            } catch (Throwable e3) {
                db.a(e3);
            }
            try {
                this.k = de.b(context);
                this.l = de.c(context);
                if (context.getResources().getConfiguration().orientation == 2) {
                    this.k ^= this.l;
                    this.l = this.k ^ this.l;
                    this.k ^= this.l;
                }
            } catch (Throwable e32) {
                db.a(e32);
            }
            this.m = CooperService.a().getAppChannel(context);
            this.f = CooperService.a().getAppKey(context);
            try {
                this.h = CooperService.a().getAppVersionCode(context);
                this.i = CooperService.a().getAppVersionName(context);
            } catch (Throwable e322) {
                db.a(e322);
            }
            try {
                if (CooperService.a().checkCellLocationSetting(context)) {
                    this.q = de.g(context);
                } else {
                    this.q = "0_0_0";
                }
            } catch (Throwable e3222) {
                db.a(e3222);
            }
            try {
                if (CooperService.a().checkGPSLocationSetting(context)) {
                    this.r = de.h(context);
                } else {
                    this.r = "";
                }
            } catch (Throwable e32222) {
                db.a(e32222);
            }
            try {
                this.s = CooperService.a().getLinkedWay(context);
            } catch (Throwable e322222) {
                db.a(e322222);
            }
            this.x = de.b();
            this.b = true;
        }
    }

    public synchronized void b(Context context, JSONObject jSONObject) {
        try {
            jSONObject.put(Config.OS, a == null ? "" : a);
            jSONObject.put(Config.STAT_SDK_TYPE, 0);
            jSONObject.put("s", this.c == null ? "" : this.c);
            jSONObject.put("sv", this.d == null ? "" : this.d);
            jSONObject.put(Config.APP_KEY, this.f == null ? "" : this.f);
            jSONObject.put(Config.PLATFORM_TYPE, this.e == null ? "0" : this.e);
            jSONObject.put("i", "");
            jSONObject.put("v", "3.7.6.1");
            jSONObject.put(Config.STAT_SDK_CHANNEL, 0);
            jSONObject.put("a", this.h);
            jSONObject.put("n", this.i == null ? "" : this.i);
            jSONObject.put("d", "");
            jSONObject.put("mc", this.t == null ? "" : this.t);
            jSONObject.put(Config.DEVICE_BLUETOOTH_MAC, this.v == null ? "" : this.v);
            jSONObject.put(Config.DEVICE_ID_SEC, this.j == null ? "" : this.j);
            jSONObject.put(Config.CUID_SEC, this.g == null ? "" : this.g);
            jSONObject.put(Config.SDK_TAG, 1);
            jSONObject.put("w", this.k);
            jSONObject.put("h", this.l);
            jSONObject.put(Config.DEVICE_NAME, this.w == null ? "" : this.w);
            jSONObject.put("c", this.m == null ? "" : this.m);
            jSONObject.put(Config.OPERATOR, this.n == null ? "" : this.n);
            jSONObject.put("m", this.o == null ? "" : this.o);
            jSONObject.put(Config.MANUFACTURER, this.p == null ? "" : this.p);
            jSONObject.put(Config.CELL_LOCATION, this.q);
            jSONObject.put(Config.GPS_LOCATION, this.r == null ? "" : this.r);
            jSONObject.put("l", this.s == null ? "" : this.s);
            jSONObject.put("t", System.currentTimeMillis());
            jSONObject.put(Config.PACKAGE_NAME, de.h(1, context));
            jSONObject.put(Config.ROM, this.x == null ? "" : this.x);
            CharSequence q = de.q(context);
            jSONObject.put(Config.PROCESS_LABEL, q);
            Object obj = null;
            if (!TextUtils.isEmpty(q)) {
                obj = de.r(context);
            }
            String str = Config.PROCESS_CLASS;
            if (obj == null) {
                obj = "";
            }
            jSONObject.put(str, obj);
            jSONObject.put(Config.SIGN, this.y == null ? "" : this.y);
            if (!(this.z == null || this.z.length() == 0)) {
                jSONObject.put("ext", this.z);
            }
            db.a("header is: " + jSONObject.toString() + "; len: " + jSONObject.length());
        } catch (JSONException e) {
            db.a("header ini error");
        }
    }

    public void a(JSONObject jSONObject) {
        this.z = jSONObject;
    }
}
