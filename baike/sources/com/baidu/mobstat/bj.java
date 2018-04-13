package com.baidu.mobstat;

import android.content.Context;
import android.content.SharedPreferences;

class bj extends bk {
    static bj a = new bj();

    private bj() {
    }

    public static bj a() {
        return a;
    }

    public SharedPreferences a(Context context) {
        return context.getSharedPreferences("__Baidu_Stat_SDK_SendRem", 0);
    }

    protected void a(Context context, int i) {
        b(context, "sendLogtype", i);
    }

    protected int b(Context context) {
        return a(context, "sendLogtype", 0);
    }

    protected void b(Context context, int i) {
        b(context, "timeinterval", i);
    }

    protected int c(Context context) {
        return a(context, "timeinterval", 1);
    }

    protected void a(Context context, boolean z) {
        b(context, "onlywifi", z);
    }

    protected boolean d(Context context) {
        return a(context, "onlywifi", false);
    }

    protected void a(Context context, String str) {
        b(context, "device_id_1", str);
    }

    protected String e(Context context) {
        return a(context, "device_id_1", null);
    }

    protected void b(Context context, String str) {
        if (a(context, "cuid", null) != null) {
            g(context, "cuid");
        }
        b(context, "cuidsec_1", str);
    }

    protected String f(Context context) {
        return a(context, "cuidsec_1", null);
    }

    protected void c(Context context, String str) {
        b(context, "setchannelwithcodevalue", str);
    }

    protected String g(Context context) {
        return a(context, "setchannelwithcodevalue", null);
    }

    protected void b(Context context, boolean z) {
        b(context, "setchannelwithcode", z);
    }

    protected boolean h(Context context) {
        return a(context, "setchannelwithcode", false);
    }

    protected void d(Context context, String str) {
        b(context, "mtjsdkmacss2_1", str);
    }

    protected String i(Context context) {
        return a(context, "mtjsdkmacss2_1", null);
    }

    protected void c(Context context, boolean z) {
        b(context, "mtjtv", z);
    }

    protected boolean j(Context context) {
        return a(context, "mtjtv", false);
    }

    protected void e(Context context, String str) {
        b(context, "mtjsdkmacsstv_1", str);
    }

    protected String k(Context context) {
        return a(context, "mtjsdkmacsstv_1", null);
    }

    protected void f(Context context, String str) {
        b(context, "he.ext", str);
    }

    protected String l(Context context) {
        return a(context, "he.ext", null);
    }

    protected void d(Context context, boolean z) {
        b(context, "mtjsdkmactrick", z);
    }

    protected boolean m(Context context) {
        return a(context, "mtjsdkmactrick", true);
    }
}
