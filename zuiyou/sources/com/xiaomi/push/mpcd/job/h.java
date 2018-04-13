package com.xiaomi.push.mpcd.job;

import android.content.Context;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.xiaomi.xmpush.thrift.d;

public class h extends f {
    private boolean a;
    private boolean b;
    private boolean e;
    private boolean f;
    private boolean g;

    public h(Context context, int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        super(context, i);
        this.a = z;
        this.b = z2;
        this.e = z3;
        this.f = z4;
        this.g = z5;
    }

    private String f() {
        if (!this.a) {
            return "off";
        }
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) this.d.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.heightPixels + "," + displayMetrics.widthPixels;
        } catch (Throwable th) {
            return "";
        }
    }

    private String g() {
        if (!this.b) {
            return "off";
        }
        try {
            return VERSION.RELEASE;
        } catch (Throwable th) {
            return "";
        }
    }

    private String h() {
        if (!this.e) {
            return "off";
        }
        try {
            return String.valueOf(VERSION.SDK_INT);
        } catch (Throwable th) {
            return "";
        }
    }

    private String i() {
        if (!this.f) {
            return "off";
        }
        try {
            return Secure.getString(this.d.getContentResolver(), "android_id");
        } catch (Throwable th) {
            return "";
        }
    }

    private String j() {
        if (!this.g) {
            return "off";
        }
        try {
            return ((TelephonyManager) this.d.getSystemService("phone")).getSimOperator();
        } catch (Throwable th) {
            return "";
        }
    }

    public int a() {
        return 3;
    }

    public String b() {
        return f() + "|" + g() + "|" + h() + "|" + i() + "|" + j();
    }

    public d d() {
        return d.DeviceInfoV2;
    }
}
