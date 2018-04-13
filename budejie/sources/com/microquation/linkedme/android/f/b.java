package com.microquation.linkedme.android.f;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import android.util.Log;
import com.alipay.sdk.util.h;
import com.microquation.linkedme.android.g.f.a;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class b {
    private static boolean a = false;
    private static boolean b = false;
    private static boolean c = true;
    private static boolean d = true;
    private static String e = null;
    private static String f = "lkme_is_gal";
    private static b g;
    private SharedPreferences h;
    private Editor i;
    private Context j;

    private b(Context context) {
        this.h = context.getSharedPreferences("linkedme_referral_shared_pref", 0);
        this.i = this.h.edit();
        this.j = context;
    }

    public static b a(Context context) {
        if (g == null) {
            g = new b(context);
        }
        return g;
    }

    public static void a(String str) {
        if (!b) {
            return;
        }
        if (g != null) {
            g.c("LKMEInner", str);
        } else if (a) {
            Log.i("LKMEInner", str);
        }
    }

    public static void a(String str, String str2) {
        if (g != null) {
            g.c(str, str2);
        } else if (a) {
            Log.i(str, str2);
        }
    }

    public static boolean a() {
        return b;
    }

    private void ab() {
        String n = n();
        String o = o();
        this.i.clear();
        j(n);
        k(o);
        a.a().a(g.i);
    }

    private void ac() {
        b("lkme_lc_data", "lkme_no_value");
    }

    private void ad() {
        b("lkme_si_data", "lkme_no_value");
    }

    public String A() {
        return q("lkme_device_id");
    }

    public String B() {
        return q("lkme_user_id");
    }

    public String C() {
        return q("lkme_imei");
    }

    public String D() {
        return q("lkme_mac");
    }

    public int E() {
        return a("lkme_gal_interval", 1);
    }

    public void F() {
        a("lkme_app_list_ud", System.currentTimeMillis());
    }

    public long G() {
        if (p("lkme_app_list_ud") != 0) {
            return p("lkme_app_list_ud");
        }
        F();
        return System.currentTimeMillis();
    }

    @TargetApi(9)
    public boolean H() {
        return System.currentTimeMillis() > G() + TimeUnit.DAYS.toMillis((long) E()) && J();
    }

    public int I() {
        return a("lkme_gal_req_interval", 10);
    }

    public boolean J() {
        return r(f);
    }

    public boolean K() {
        return r("lkme_is_lc");
    }

    public int L() {
        return a("lkme_lc_interval", 60);
    }

    public boolean M() {
        return r("lkme_keep_tracking");
    }

    public int N() {
        return a("lkme_min_time", 10);
    }

    public int O() {
        return a("lkme_min_distance", 0);
    }

    public int P() {
        return a("lkme_delay", 60);
    }

    public int Q() {
        return a("lkme_period", 30);
    }

    public int R() {
        return a("lkme_duration", 0);
    }

    public void S() {
        a("lkme_lc_ud", System.currentTimeMillis());
    }

    public long T() {
        if (p("lkme_lc_ud") != 0) {
            return p("lkme_lc_ud");
        }
        S();
        return System.currentTimeMillis();
    }

    public boolean U() {
        return r("lkme_lc_fine");
    }

    @TargetApi(9)
    public boolean V() {
        return System.currentTimeMillis() > T() + TimeUnit.SECONDS.toMillis((long) L()) && K();
    }

    public String W() {
        String q = TextUtils.equals(q("lkme_si_data"), "lkme_no_value") ? "" : q("lkme_si_data");
        ad();
        return q;
    }

    public String X() {
        String q = TextUtils.equals(q("lkme_lc_data"), "lkme_no_value") ? "" : q("lkme_lc_data");
        ac();
        return q;
    }

    public boolean Y() {
        return r("lkme_close_enable");
    }

    public boolean Z() {
        return r("lkme_lc_up");
    }

    public int a(String str, int i) {
        return g.h.getInt(str, i);
    }

    public void a(int i) {
        b("lkme_gal_interval", i);
    }

    public void a(String str, long j) {
        g.i.putLong(str, j);
        a.a().a(g.i);
    }

    public void a(String str, Boolean bool) {
        g.i.putBoolean(str, bool.booleanValue());
        a.a().a(g.i);
    }

    public void a(String str, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            Object X = X();
            if (!z || TextUtils.isEmpty(X)) {
                b("lkme_lc_data", str);
            } else {
                b("lkme_lc_data", X + h.b + str);
            }
        }
    }

    public void a(boolean z) {
        a("lkme_handle_status", Boolean.valueOf(z));
    }

    public boolean aa() {
        return r("lkme_use_https");
    }

    public String b() {
        return (aa() ? "https://lkme.cc" : "http://lkme.cc") + "/i";
    }

    public void b(int i) {
        b("lkme_gal_req_interval", i);
    }

    public void b(String str) {
        b("lkme_app_version", str);
    }

    public void b(String str, int i) {
        g.i.putInt(str, i);
        a.a().a(g.i);
    }

    public void b(String str, String str2) {
        g.i.putString(str, str2);
        a.a().a(g.i);
    }

    public void b(boolean z) {
        a(f, Boolean.valueOf(z));
    }

    public String c() {
        return (aa() ? "https://lkme.cc" : "http://lkme.cc") + "/track/";
    }

    public void c(int i) {
        b("lkme_lc_interval", i);
    }

    public void c(String str, String str2) {
        if (a) {
            Log.i(str, str2);
        }
    }

    public void c(boolean z) {
        a("lkme_is_lc", Boolean.valueOf(z));
    }

    public boolean c(String str) {
        e = str;
        String q = q("lkme_linkedme_key");
        if (str != null && q != null && q.equals(str)) {
            return false;
        }
        ab();
        b("lkme_linkedme_key", str);
        return true;
    }

    public int d() {
        return a("lkme_timeout", 5500);
    }

    public void d(int i) {
        b("lkme_min_time", i);
    }

    public void d(String str) {
        b("lkme_device_fingerprint_id", str);
    }

    public void d(boolean z) {
        a("lkme_keep_tracking", Boolean.valueOf(z));
    }

    public int e() {
        return a("lkme_retry_count", 3);
    }

    public void e(int i) {
        b("lkme_min_distance", i);
    }

    public void e(String str) {
        b("lkme_session_id", str);
    }

    public void e(boolean z) {
        a("lkme_lc_fine", Boolean.valueOf(z));
    }

    public int f() {
        return a("lkme_retry_interval", 0);
    }

    public void f(int i) {
        b("lkme_delay", i);
    }

    public void f(String str) {
        b("lkme_identity_id", str);
    }

    public void f(boolean z) {
        a("lkme_close_enable", Boolean.valueOf(z));
    }

    public String g() {
        return q("lkme_app_version");
    }

    public void g(int i) {
        b("lkme_period", i);
    }

    public void g(String str) {
        b("lkme_identity", str);
    }

    public void g(boolean z) {
        a("lkme_lc_up", Boolean.valueOf(z));
    }

    public String h() {
        if (e == null) {
            e = q("lkme_linkedme_key");
        }
        return e;
    }

    public void h(int i) {
        b("lkme_duration", i);
    }

    public void h(String str) {
        b("lkme_external_intent_uri", str);
    }

    public String i() {
        String str = null;
        String str2 = "linkedme.sdk.key";
        try {
            ApplicationInfo applicationInfo = this.j.getPackageManager().getApplicationInfo(this.j.getPackageName(), 128);
            if (applicationInfo.metaData != null) {
                str = applicationInfo.metaData.getString(str2);
            }
        } catch (NameNotFoundException e) {
        }
        if (str == null) {
            str = "lkme_no_value";
            a("LinkedME --> ", "<meta-data />中没有配置LinkedME Key");
        }
        a("LinkedME --> ", str);
        return str;
    }

    public void i(String str) {
        b("lkme_external_intent_extra", str);
    }

    public String j() {
        return q("lkme_device_fingerprint_id");
    }

    public void j(String str) {
        b("lkme_link_click_identifier", str);
    }

    public String k() {
        return q("lkme_session_id");
    }

    public void k(String str) {
        b("lkme_app_link", str);
    }

    public String l() {
        return q("lkme_identity_id");
    }

    public void l(String str) {
        b("lkme_session_params", str);
    }

    public String m() {
        return q("lkme_external_intent_uri");
    }

    public void m(String str) {
        b("lkme_install_params", str);
    }

    public String n() {
        return q("lkme_link_click_identifier");
    }

    public void n(String str) {
        b("lkme_user_url", str);
    }

    public int o(String str) {
        return a(str, 0);
    }

    public String o() {
        return q("lkme_app_link");
    }

    public long p(String str) {
        return g.h.getLong(str, 0);
    }

    public String p() {
        return q("lkme_session_params");
    }

    public String q() {
        return q("lkme_install_params");
    }

    public String q(String str) {
        return g.h.getString(str, "lkme_no_value");
    }

    public String r() {
        return q("lkme_user_url");
    }

    public boolean r(String str) {
        return g.h.getBoolean(str, false);
    }

    public int s() {
        return o("lkme_is_referrable");
    }

    public void s(String str) {
        b("lkme_device_id", str);
    }

    public void t() {
        b("lkme_is_referrable", 1);
    }

    public void t(String str) {
        b("lkme_link", str);
    }

    public void u() {
        b("lkme_is_referrable", 0);
    }

    public void u(String str) {
        b("lkme_user_id", str);
    }

    public void v() {
        a("lkme_system_read_date", Calendar.getInstance().getTimeInMillis() / 1000);
    }

    public void v(String str) {
        b("lkme_imei", str);
    }

    public void w() {
        a = true;
    }

    public void w(String str) {
        b("lkme_mac", str);
    }

    public void x(String str) {
        if (!TextUtils.isEmpty(str)) {
            String W = W();
            if (TextUtils.isEmpty(W)) {
                b("lkme_si_data", str + "," + System.currentTimeMillis());
                return;
            }
            String[] split = TextUtils.split(W, h.b);
            String str2 = split[split.length - 1];
            int lastIndexOf = str2.lastIndexOf(",");
            if (lastIndexOf != -1) {
                String substring = str2.substring(0, lastIndexOf);
                str2 = str2.substring(lastIndexOf + 1);
                if (!substring.equalsIgnoreCase(str) || System.currentTimeMillis() - Long.valueOf(str2).longValue() >= TimeUnit.MINUTES.toMillis(3)) {
                    b("lkme_si_data", W + "," + System.currentTimeMillis() + h.b + str + "," + System.currentTimeMillis());
                    return;
                } else {
                    b("lkme_si_data", W);
                    return;
                }
            }
            b("lkme_si_data", W + "," + System.currentTimeMillis() + h.b + str + "," + System.currentTimeMillis());
        }
    }

    public boolean x() {
        return a;
    }

    public boolean y() {
        return c;
    }

    public boolean z() {
        return a;
    }
}
