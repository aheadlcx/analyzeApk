package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Base64;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.tencent.open.GameAppOperation;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.pro.ca.b;
import com.umeng.analytics.pro.ca.c;
import com.umeng.analytics.pro.ca.d;
import com.umeng.analytics.pro.ca.e;
import com.umeng.analytics.pro.ca.f;
import com.umeng.analytics.pro.ca.g;
import com.umeng.analytics.pro.ca.h;
import com.umeng.analytics.pro.ca.i;
import com.umeng.analytics.pro.ca.j;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class aq implements au, az {
    private static Context j = null;
    private static final String q = "thtstart";
    private static final String r = "gkvc";
    private static final String s = "ekvc";
    String a = null;
    private cc b = null;
    private be c = null;
    private bh d = null;
    private bg e = null;
    private bi f = null;
    private a g = null;
    private com.umeng.analytics.pro.af.a h = null;
    private long i = 0;
    private int k = 10;
    private JSONArray l = new JSONArray();
    private final int m = 5000;
    private int n = 0;
    private int o = 0;
    private long p = 0;
    private final long t = 28800000;

    public class a {
        final /* synthetic */ aq a;
        private h b;
        private int c = -1;
        private int d = -1;
        private int e = -1;
        private int f = -1;

        public a(aq aqVar) {
            this.a = aqVar;
            int[] a = aqVar.h.a(-1, -1);
            this.c = a[0];
            this.d = a[1];
        }

        protected void a(boolean z) {
            int i = 1;
            int i2 = 0;
            if (this.a.d.d()) {
                h hVar;
                if (!((this.b instanceof b) && this.b.a())) {
                    i = 0;
                }
                if (i != 0) {
                    hVar = this.b;
                } else {
                    hVar = new b(this.a.c, this.a.d);
                }
                this.b = hVar;
                return;
            }
            if (!((this.b instanceof c) && this.b.a())) {
                i = 0;
            }
            if (i != 0) {
                return;
            }
            if (z && this.a.f.a()) {
                this.b = new c((int) this.a.f.b());
                this.a.b((int) this.a.f.b());
            } else if (by.a && this.a.h.b()) {
                this.b = new com.umeng.analytics.pro.ca.a(this.a.c);
            } else if (this.a.e.a() && "RPT".equals(this.a.e.f())) {
                if (this.a.e.b() == 6) {
                    if (this.a.h.a()) {
                        i2 = this.a.h.d(90000);
                    } else if (this.d > 0) {
                        i2 = this.d;
                    } else {
                        i2 = this.f;
                    }
                }
                this.b = b(this.a.e.b(), i2);
            } else {
                i = this.e;
                i2 = this.f;
                if (this.c != -1) {
                    i = this.c;
                    i2 = this.d;
                }
                this.b = b(i, i2);
            }
        }

        public h b(boolean z) {
            a(z);
            return this.b;
        }

        private h b(int i, int i2) {
            switch (i) {
                case 0:
                    return this.b instanceof g ? this.b : new g();
                case 1:
                    return this.b instanceof d ? this.b : new d();
                case 4:
                    if (this.b instanceof f) {
                        return this.b;
                    }
                    return new f(this.a.c);
                case 5:
                    if (this.b instanceof i) {
                        return this.b;
                    }
                    return new i(aq.j);
                case 6:
                    if (!(this.b instanceof e)) {
                        return new e(this.a.c, (long) i2);
                    }
                    h hVar = this.b;
                    ((e) hVar).a((long) i2);
                    return hVar;
                case 8:
                    if (this.b instanceof j) {
                        return this.b;
                    }
                    return new j(this.a.c);
                default:
                    if (this.b instanceof d) {
                        return this.b;
                    }
                    return new d();
            }
        }

        public void a(int i, int i2) {
            this.e = i;
            this.f = i2;
        }

        public void a(com.umeng.analytics.pro.af.a aVar) {
            int[] a = aVar.a(-1, -1);
            this.c = a[0];
            this.d = a[1];
        }
    }

    public aq(Context context) {
        j = context;
        this.c = new be(context);
        this.b = cc.a(context);
        this.h = af.a(context).b();
        this.g = new a(this);
        this.e = bg.a(j);
        this.d = bh.a(j);
        this.f = bi.a(j, this.c);
        SharedPreferences a = ba.a(j);
        this.p = a.getLong(q, 0);
        this.n = a.getInt(r, 0);
        this.o = a.getInt(s, 0);
        this.a = af.a(j).b().b(null);
    }

    public void a() {
        if (bv.l(j)) {
            d();
        } else {
            by.b("network is unavailable");
        }
    }

    public void a(Object obj) {
        if (this.c.f()) {
            this.i = this.c.l();
        }
        boolean z = true;
        if (obj instanceof JSONObject) {
            z = false;
            try {
                b((JSONObject) obj);
            } catch (Throwable th) {
            }
        }
        if (a(z)) {
            d();
        }
    }

    private void b(JSONObject jSONObject) {
        try {
            if (w.b == jSONObject.getInt("__t")) {
                if (c(this.n)) {
                    this.n++;
                } else {
                    return;
                }
            } else if (w.a == jSONObject.getInt("__t")) {
                if (c(this.o)) {
                    this.o++;
                } else {
                    return;
                }
            }
            if (this.l.length() > this.k) {
                w.a(j).a(this.l);
                this.l = new JSONArray();
            }
            if (this.p == 0) {
                this.p = System.currentTimeMillis();
            }
            this.l.put(jSONObject);
        } catch (Throwable th) {
        }
    }

    public void b() {
        c(a(new int[0]));
    }

    private void a(int i) {
        int currentTimeMillis = (int) (System.currentTimeMillis() - this.c.m());
        c(a(i, currentTimeMillis));
        bz.a(new cb(this) {
            final /* synthetic */ aq a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.a();
            }
        }, (long) i);
    }

    private void c(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                ad a = ad.a(j);
                a.a();
                try {
                    CharSequence encodeToString = Base64.encodeToString(new cp().a(a.b()), 0);
                    if (!TextUtils.isEmpty(encodeToString)) {
                        JSONObject jSONObject2 = jSONObject.getJSONObject(com.umeng.analytics.a.A);
                        jSONObject2.put(x.O, encodeToString);
                        jSONObject.put(com.umeng.analytics.a.A, jSONObject2);
                    }
                } catch (Exception e) {
                }
                byte[] bytes = String.valueOf(jSONObject).getBytes();
                if (bytes != null && !bt.a(j, bytes)) {
                    aa b;
                    if (e()) {
                        b = aa.b(j, AnalyticsConfig.getAppkey(j), bytes);
                    } else {
                        b = aa.a(j, AnalyticsConfig.getAppkey(j), bytes);
                    }
                    bytes = b.c();
                    cc a2 = cc.a(j);
                    a2.g();
                    a2.a(bytes);
                    a.d();
                }
            } catch (Exception e2) {
            }
        }
    }

    protected JSONObject a(int... iArr) {
        if (TextUtils.isEmpty(AnalyticsConfig.getAppkey(j))) {
            by.e("Appkey is missing ,Please check AndroidManifest.xml");
            return null;
        }
        JSONObject jSONObject;
        a(j);
        JSONObject a = w.a(j).a();
        if (a == null) {
            a = new JSONObject();
        }
        try {
            jSONObject = a.getJSONObject(com.umeng.analytics.a.z);
        } catch (Throwable th) {
            jSONObject = new JSONObject();
        }
        try {
            CharSequence string;
            JSONObject jSONObject2;
            JSONObject jSONObject3 = new JSONObject(jSONObject.toString());
            SharedPreferences a2 = ba.a(j);
            if (a2 != null) {
                string = a2.getString("userlevel", "");
                if (!TextUtils.isEmpty(string)) {
                    jSONObject.put("userlevel", string);
                }
            }
            if (this.c.f() && this.i != 0) {
                jSONObject2 = new JSONObject();
                jSONObject2.put("ts", this.i);
                jSONObject.put(x.ak, jSONObject2);
                jSONObject3.put(x.ak, jSONObject2);
            }
            jSONObject2 = new JSONObject();
            JSONObject b = m.a(j).b();
            if (b != null && b.length() > 0) {
                jSONObject2.put(x.av, b);
            }
            b = m.a(j).c();
            if (b != null && b.length() > 0) {
                jSONObject2.put(x.aB, b);
            }
            if (jSONObject2.length() > 0) {
                jSONObject.put(x.au, jSONObject2);
                jSONObject3.put(x.au, jSONObject2);
            }
            String[] a3 = com.umeng.analytics.c.a(j);
            if (!(a3 == null || TextUtils.isEmpty(a3[0]) || TextUtils.isEmpty(a3[1]))) {
                b = new JSONObject();
                b.put(x.as, a3[0]);
                b.put(x.at, a3[1]);
                if (b.length() > 0) {
                    jSONObject.put(x.ar, b);
                    jSONObject3.put(x.ar, b);
                }
            }
            if (bg.a(j).a()) {
                d(jSONObject);
            }
            this.d.a(jSONObject, j);
            if (iArr != null && iArr.length == 2) {
                jSONObject2 = new JSONObject();
                b = new JSONObject();
                b.put(x.ap, iArr[0] / 1000);
                b.put(x.ao, iArr[1]);
                jSONObject2.put(x.an, b);
                jSONObject.put(x.am, jSONObject2);
            }
            if (jSONObject.length() > 0) {
                a.put(com.umeng.analytics.a.z, jSONObject);
            } else {
                try {
                    a.remove(com.umeng.analytics.a.z);
                } catch (Throwable th2) {
                }
            }
            jSONObject = new JSONObject();
            jSONObject.put("appkey", AnalyticsConfig.getAppkey(j));
            jSONObject.put("channel", AnalyticsConfig.getChannel(j));
            string = bw.a(AnalyticsConfig.getSecretKey(j));
            if (!TextUtils.isEmpty(string)) {
                jSONObject.put(x.c, string);
            }
            jSONObject.put(x.g, bv.w(j));
            jSONObject.put(x.e, bv.t(j));
            jSONObject.put(x.f, bv.u(j));
            if (a2 == null) {
                try {
                    a2 = ba.a(j);
                } catch (Throwable th3) {
                    jSONObject.put("app_version", bv.b(j));
                    jSONObject.put("version_code", Integer.parseInt(bv.a(j)));
                }
            }
            if (a2 != null) {
                string = a2.getString("vers_name", "");
                if (TextUtils.isEmpty(string)) {
                    jSONObject.put("app_version", bv.b(j));
                    jSONObject.put("version_code", Integer.parseInt(bv.a(j)));
                } else {
                    jSONObject.put("app_version", string);
                    jSONObject.put("version_code", a2.getInt("vers_code", 0));
                }
            }
            if (!(AnalyticsConfig.mWrapperType == null || AnalyticsConfig.mWrapperVersion == null)) {
                jSONObject.put(x.i, AnalyticsConfig.mWrapperType);
                jSONObject.put(x.j, AnalyticsConfig.mWrapperVersion);
            }
            jSONObject.put(x.k, "Android");
            jSONObject.put("sdk_version", AnalyticsConfig.getSDKVersion(j));
            jSONObject.put(x.m, AnalyticsConfig.getVerticalType(j));
            jSONObject.put("idmd5", bv.d(j));
            jSONObject.put(x.o, bv.a());
            jSONObject.put("os", "Android");
            jSONObject.put(x.q, VERSION.RELEASE);
            int[] r = bv.r(j);
            if (r != null) {
                jSONObject.put(x.r, r[1] + "*" + r[0]);
            }
            jSONObject.put("mc", bv.q(j));
            jSONObject.put(x.u, bv.c(j));
            jSONObject.put(x.v, Build.MODEL);
            jSONObject.put(x.w, Build.BOARD);
            jSONObject.put(x.x, Build.BRAND);
            jSONObject.put(x.y, Build.TIME);
            jSONObject.put(x.z, Build.MANUFACTURER);
            jSONObject.put(x.A, Build.ID);
            jSONObject.put(x.B, Build.DEVICE);
            string = bv.x(j);
            if (!TextUtils.isEmpty(string)) {
                jSONObject.put(x.C, string);
            }
            string = bv.y(j);
            if (!TextUtils.isEmpty(string)) {
                jSONObject.put(x.D, string);
            }
            a3 = bv.j(j);
            if (bv.d.equals(a3[0])) {
                jSONObject.put(x.I, IXAdSystemUtils.NT_WIFI);
            } else if (bv.c.equals(a3[0])) {
                jSONObject.put(x.I, bv.c);
            } else {
                jSONObject.put(x.I, "unknow");
            }
            if (!"".equals(a3[1])) {
                jSONObject.put(x.J, a3[1]);
            }
            string = bv.e(j);
            if (TextUtils.isEmpty(string)) {
                jSONObject.put(x.t, "");
            } else {
                jSONObject.put(x.t, string);
            }
            a3 = bv.o(j);
            jSONObject.put(x.G, a3[0]);
            jSONObject.put(x.F, a3[1]);
            jSONObject.put(x.E, bv.m(j));
            jSONObject.put(x.H, bv.h(j));
            try {
                jSONObject.put(x.K, a2.getInt("successful_request", 0));
                jSONObject.put(x.L, a2.getInt(x.L, 0));
                jSONObject.put(x.M, a2.getInt("last_request_spent_ms", 0));
            } catch (Exception e) {
            }
            try {
                cg a4 = af.a(j).a();
                if (a4 != null) {
                    jSONObject.put(x.N, Base64.encodeToString(new cp().a(a4), 0));
                }
            } catch (Exception e2) {
            }
            a.put(com.umeng.analytics.a.A, jSONObject);
            jSONObject3.put("sdk_version", jSONObject.getString("sdk_version")).put(x.u, jSONObject.getString(x.u)).put(x.v, jSONObject.getString(x.v)).put(GameAppOperation.QQFAV_DATALINE_VERSION, jSONObject.getString("version_code")).put("appkey", jSONObject.getString("appkey")).put("channel", jSONObject.getString("channel"));
            if (!a(jSONObject)) {
                a = null;
            }
            if (by.a && jSONObject3.length() > 0) {
                by.b(String.valueOf(jSONObject3));
            }
            if (a2 == null) {
                return a;
            }
            Editor edit = a2.edit();
            edit.remove("vers_name");
            edit.remove("vers_code");
            edit.remove("vers_date");
            edit.remove("vers_pre_version");
            edit.commit();
            return a;
        } catch (Throwable th4) {
            cc.a(j).g();
            return null;
        }
    }

    private void d(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(bg.a(j).f(), bg.a(j).e());
        jSONObject.put(x.aq, jSONObject2);
    }

    private String[] a(Editor editor, SharedPreferences sharedPreferences, String str, String str2) {
        String string = sharedPreferences.getString("pre_version", "");
        String string2 = sharedPreferences.getString("pre_date", "");
        String string3 = sharedPreferences.getString("cur_version", "");
        String b = bv.b(j);
        if (string3.equals(b)) {
            string3 = string;
        } else {
            string2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
            editor.putString("pre_version", string3);
            editor.putString("pre_date", string2);
            editor.putString("cur_version", b);
            editor.commit();
        }
        return new String[]{string3, string2};
    }

    private void a(JSONObject jSONObject, String str, String str2) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            str = "0";
        }
        jSONObject.put(x.R, str);
        if (TextUtils.isEmpty(str2)) {
            str2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
        }
        jSONObject.put(x.S, str2);
    }

    public boolean a(JSONObject jSONObject) {
        if (TextUtils.isEmpty(x.u) || TextUtils.isEmpty("mc") || TextUtils.isEmpty(x.r) || TextUtils.isEmpty("appkey") || TextUtils.isEmpty("channel") || TextUtils.isEmpty(x.f) || TextUtils.isEmpty(x.e) || TextUtils.isEmpty("app_version")) {
            return false;
        }
        return true;
    }

    private boolean a(boolean z) {
        if (!bv.l(j)) {
            by.e("network is unavailable");
            return false;
        } else if (this.c.f()) {
            return true;
        } else {
            return this.g.b(z).a(z);
        }
    }

    private void d() {
        try {
            if (this.b.h()) {
                bc bcVar = new bc(j, this.c);
                bcVar.a((az) this);
                if (this.d.d()) {
                    bcVar.b(true);
                }
                bcVar.a();
                return;
            }
            JSONObject a = a(new int[0]);
            if (a.length() > 0) {
                bc bcVar2 = new bc(j, this.c);
                bcVar2.a((az) this);
                if (this.d.d()) {
                    bcVar2.b(true);
                }
                bcVar2.a(a);
                bcVar2.a(e());
                bcVar2.a();
            }
        } catch (Throwable th) {
            if (th != null) {
                th.printStackTrace();
            }
        }
    }

    private boolean e() {
        switch (this.h.c(-1)) {
            case -1:
                return AnalyticsConfig.sEncrypt;
            case 1:
                return true;
            default:
                return false;
        }
    }

    private void b(int i) {
        a(i);
    }

    public void a(com.umeng.analytics.pro.af.a aVar) {
        this.e.a(aVar);
        this.d.a(aVar);
        this.f.a(aVar);
        this.g.a(aVar);
        this.a = af.a(j).b().b(null);
    }

    private boolean c(int i) {
        if (this.p == 0) {
            return true;
        }
        if (System.currentTimeMillis() - this.p > 28800000) {
            f();
            return true;
        } else if (i > 5000) {
            return false;
        } else {
            return true;
        }
    }

    public void a(Context context) {
        try {
            if (j == null) {
                j = context;
            }
            if (this.l.length() > 0) {
                w.a(j).a(this.l);
                this.l = new JSONArray();
            }
            ba.a(j).edit().putLong(q, this.p).putInt(r, this.n).putInt(s, this.o).commit();
        } catch (Throwable th) {
        }
    }

    private void f() {
        this.n = 0;
        this.o = 0;
        this.p = System.currentTimeMillis();
    }
}
