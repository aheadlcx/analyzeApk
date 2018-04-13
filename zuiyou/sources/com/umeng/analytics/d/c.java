package com.umeng.analytics.d;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.a.g;
import com.umeng.a.i;
import com.umeng.a.i.d;
import com.umeng.a.i.e;
import com.umeng.a.i.f;
import com.umeng.a.i.h;
import com.umeng.a.i.j;
import com.umeng.a.k;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.e.b;
import com.zhihu.matisse.internal.utils.Platform;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class c implements g, l {
    private static Context j = null;
    private static final String q = "thtstart";
    private static final String r = "gkvc";
    private static final String s = "ekvc";
    String a = null;
    private k b = null;
    private q c = null;
    private b d = null;
    private com.umeng.analytics.e.a e = null;
    private com.umeng.analytics.e.c f = null;
    private a g = null;
    private com.umeng.analytics.c.h.a h = null;
    private long i = 0;
    private int k = 10;
    private JSONArray l = new JSONArray();
    private final int m = 5000;
    private int n = 0;
    private int o = 0;
    private long p = 0;
    private final long t = 28800000;

    public class a {
        final /* synthetic */ c a;
        private h b;
        private int c = -1;
        private int d = -1;
        private int e = -1;
        private int f = -1;

        public a(c cVar) {
            this.a = cVar;
            int[] a = cVar.h.a(-1, -1);
            this.c = a[0];
            this.d = a[1];
        }

        protected void a(boolean z) {
            int i = 1;
            int i2 = 0;
            if (this.a.d.d()) {
                h hVar;
                if (!((this.b instanceof i.b) && this.b.a())) {
                    i = 0;
                }
                if (i != 0) {
                    hVar = this.b;
                } else {
                    hVar = new i.b(this.a.c, this.a.d);
                }
                this.b = hVar;
                return;
            }
            if (!((this.b instanceof com.umeng.a.i.c) && this.b.a())) {
                i = 0;
            }
            if (i != 0) {
                return;
            }
            if (z && this.a.f.a()) {
                this.b = new com.umeng.a.i.c((int) this.a.f.b());
                this.a.b((int) this.a.f.b());
            } else if (g.a && this.a.h.b()) {
                this.b = new com.umeng.a.i.a(this.a.c);
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
                    return this.b instanceof i.g ? this.b : new i.g();
                case 1:
                    return this.b instanceof d ? this.b : new d();
                case 4:
                    if (this.b instanceof f) {
                        return this.b;
                    }
                    return new f(this.a.c);
                case 5:
                    if (this.b instanceof i.i) {
                        return this.b;
                    }
                    return new i.i(c.j);
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

        public void a(com.umeng.analytics.c.h.a aVar) {
            int[] a = aVar.a(-1, -1);
            this.c = a[0];
            this.d = a[1];
        }
    }

    public c(Context context) {
        j = context;
        this.c = new q(context);
        this.b = k.a(context);
        this.h = com.umeng.analytics.c.h.a(context).b();
        this.g = new a(this);
        this.e = com.umeng.analytics.e.a.a(j);
        this.d = b.a(j);
        this.f = com.umeng.analytics.e.c.a(j, this.c);
        SharedPreferences a = m.a(j);
        this.p = a.getLong(q, 0);
        this.n = a.getInt(r, 0);
        this.o = a.getInt(s, 0);
        this.a = com.umeng.analytics.c.h.a(j).b().b(null);
    }

    public void a() {
        if (com.umeng.a.d.j(j)) {
            d();
        } else {
            g.a("network is unavailable");
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
            if (com.umeng.analytics.b.f.b == jSONObject.getInt("__t")) {
                if (c(this.n)) {
                    this.n++;
                } else {
                    return;
                }
            } else if (com.umeng.analytics.b.f.a == jSONObject.getInt("__t")) {
                if (c(this.o)) {
                    this.o++;
                } else {
                    return;
                }
            }
            if (this.l.length() > this.k) {
                com.umeng.analytics.b.f.a(j).a(this.l);
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
        com.umeng.a.h.a(new com.umeng.a.j(this) {
            final /* synthetic */ c a;

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
                com.umeng.analytics.c.f a = com.umeng.analytics.c.f.a(j);
                a.a();
                try {
                    CharSequence encodeToString = Base64.encodeToString(new a.a.a.g().a(a.b()), 0);
                    if (!TextUtils.isEmpty(encodeToString)) {
                        JSONObject jSONObject2 = jSONObject.getJSONObject(com.umeng.analytics.a.A);
                        jSONObject2.put(com.umeng.analytics.b.g.O, encodeToString);
                        jSONObject.put(com.umeng.analytics.a.A, jSONObject2);
                    }
                } catch (Exception e) {
                }
                byte[] bytes = String.valueOf(jSONObject).getBytes();
                if (bytes != null && !com.umeng.a.b.a(j, bytes)) {
                    com.umeng.analytics.c.c b;
                    if (e()) {
                        b = com.umeng.analytics.c.c.b(j, AnalyticsConfig.getAppkey(j), bytes);
                    } else {
                        b = com.umeng.analytics.c.c.a(j, AnalyticsConfig.getAppkey(j), bytes);
                    }
                    bytes = b.c();
                    k a2 = k.a(j);
                    a2.f();
                    a2.a(bytes);
                    a.d();
                }
            } catch (Exception e2) {
            }
        }
    }

    protected JSONObject a(int... iArr) {
        if (TextUtils.isEmpty(AnalyticsConfig.getAppkey(j))) {
            g.d("Appkey is missing ,Please check AndroidManifest.xml");
            return null;
        }
        JSONObject jSONObject;
        a(j);
        JSONObject a = com.umeng.analytics.b.f.a(j).a();
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
            SharedPreferences a2 = m.a(j);
            if (a2 != null) {
                string = a2.getString("userlevel", "");
                if (!TextUtils.isEmpty(string)) {
                    jSONObject.put("userlevel", string);
                }
            }
            if (this.c.f() && this.i != 0) {
                jSONObject2 = new JSONObject();
                jSONObject2.put("ts", this.i);
                jSONObject.put(com.umeng.analytics.b.g.ak, jSONObject2);
                jSONObject3.put(com.umeng.analytics.b.g.ak, jSONObject2);
            }
            jSONObject2 = new JSONObject();
            JSONObject b = com.umeng.analytics.a.d.a.a(j).b();
            if (b != null && b.length() > 0) {
                jSONObject2.put(com.umeng.analytics.b.g.av, b);
            }
            b = com.umeng.analytics.a.d.a.a(j).c();
            if (b != null && b.length() > 0) {
                jSONObject2.put(com.umeng.analytics.b.g.aB, b);
            }
            if (jSONObject2.length() > 0) {
                jSONObject.put(com.umeng.analytics.b.g.au, jSONObject2);
                jSONObject3.put(com.umeng.analytics.b.g.au, jSONObject2);
            }
            String[] a3 = com.umeng.analytics.c.a(j);
            if (!(a3 == null || TextUtils.isEmpty(a3[0]) || TextUtils.isEmpty(a3[1]))) {
                b = new JSONObject();
                b.put(com.umeng.analytics.b.g.as, a3[0]);
                b.put(com.umeng.analytics.b.g.at, a3[1]);
                if (b.length() > 0) {
                    jSONObject.put(com.umeng.analytics.b.g.ar, b);
                    jSONObject3.put(com.umeng.analytics.b.g.ar, b);
                }
            }
            if (com.umeng.analytics.e.a.a(j).a()) {
                d(jSONObject);
            }
            this.d.a(jSONObject, j);
            if (iArr != null && iArr.length == 2) {
                jSONObject2 = new JSONObject();
                b = new JSONObject();
                b.put(com.umeng.analytics.b.g.ap, iArr[0] / 1000);
                b.put(com.umeng.analytics.b.g.ao, iArr[1]);
                jSONObject2.put(com.umeng.analytics.b.g.an, b);
                jSONObject.put(com.umeng.analytics.b.g.am, jSONObject2);
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
            jSONObject.put(com.umeng.analytics.b.g.a, AnalyticsConfig.getAppkey(j));
            jSONObject.put(com.umeng.analytics.b.g.b, AnalyticsConfig.getChannel(j));
            string = com.umeng.a.e.a(AnalyticsConfig.getSecretKey(j));
            if (!TextUtils.isEmpty(string)) {
                jSONObject.put(com.umeng.analytics.b.g.c, string);
            }
            jSONObject.put(com.umeng.analytics.b.g.g, com.umeng.a.d.t(j));
            jSONObject.put(com.umeng.analytics.b.g.e, com.umeng.a.d.q(j));
            jSONObject.put(com.umeng.analytics.b.g.f, com.umeng.a.d.r(j));
            if (a2 == null) {
                try {
                    a2 = m.a(j);
                } catch (Throwable th3) {
                    jSONObject.put(com.umeng.analytics.b.g.d, com.umeng.a.d.b(j));
                    jSONObject.put(com.umeng.analytics.b.g.h, Integer.parseInt(com.umeng.a.d.a(j)));
                }
            }
            if (a2 != null) {
                string = a2.getString("vers_name", "");
                if (TextUtils.isEmpty(string)) {
                    jSONObject.put(com.umeng.analytics.b.g.d, com.umeng.a.d.b(j));
                    jSONObject.put(com.umeng.analytics.b.g.h, Integer.parseInt(com.umeng.a.d.a(j)));
                } else {
                    jSONObject.put(com.umeng.analytics.b.g.d, string);
                    jSONObject.put(com.umeng.analytics.b.g.h, a2.getInt("vers_code", 0));
                }
            }
            if (!(AnalyticsConfig.mWrapperType == null || AnalyticsConfig.mWrapperVersion == null)) {
                jSONObject.put(com.umeng.analytics.b.g.i, AnalyticsConfig.mWrapperType);
                jSONObject.put(com.umeng.analytics.b.g.j, AnalyticsConfig.mWrapperVersion);
            }
            jSONObject.put(com.umeng.analytics.b.g.k, "Android");
            jSONObject.put(com.umeng.analytics.b.g.l, AnalyticsConfig.getSDKVersion(j));
            jSONObject.put(com.umeng.analytics.b.g.m, AnalyticsConfig.getVerticalType(j));
            jSONObject.put(com.umeng.analytics.b.g.n, com.umeng.a.d.d(j));
            jSONObject.put(com.umeng.analytics.b.g.o, com.umeng.a.d.a());
            jSONObject.put(com.umeng.analytics.b.g.p, "Android");
            jSONObject.put(com.umeng.analytics.b.g.q, VERSION.RELEASE);
            int[] o = com.umeng.a.d.o(j);
            if (o != null) {
                jSONObject.put(com.umeng.analytics.b.g.r, o[1] + "*" + o[0]);
            }
            jSONObject.put(com.umeng.analytics.b.g.s, com.umeng.a.d.n(j));
            jSONObject.put("device_id", com.umeng.a.d.c(j));
            jSONObject.put(com.umeng.analytics.b.g.v, Build.MODEL);
            jSONObject.put(com.umeng.analytics.b.g.w, Build.BOARD);
            jSONObject.put(com.umeng.analytics.b.g.x, Build.BRAND);
            jSONObject.put(com.umeng.analytics.b.g.y, Build.TIME);
            jSONObject.put(com.umeng.analytics.b.g.z, Build.MANUFACTURER);
            jSONObject.put(com.umeng.analytics.b.g.A, Build.ID);
            jSONObject.put(com.umeng.analytics.b.g.B, Build.DEVICE);
            string = com.umeng.a.d.u(j);
            if (!TextUtils.isEmpty(string)) {
                jSONObject.put(com.umeng.analytics.b.g.C, string);
            }
            string = com.umeng.a.d.v(j);
            if (!TextUtils.isEmpty(string)) {
                jSONObject.put(com.umeng.analytics.b.g.D, string);
            }
            a3 = com.umeng.a.d.h(j);
            if ("Wi-Fi".equals(a3[0])) {
                jSONObject.put(com.umeng.analytics.b.g.I, "wifi");
            } else if ("2G/3G".equals(a3[0])) {
                jSONObject.put(com.umeng.analytics.b.g.I, "2G/3G");
            } else {
                jSONObject.put(com.umeng.analytics.b.g.I, Platform.UNKNOW);
            }
            if (!"".equals(a3[1])) {
                jSONObject.put(com.umeng.analytics.b.g.J, a3[1]);
            }
            string = com.umeng.a.d.e(j);
            if (TextUtils.isEmpty(string)) {
                jSONObject.put(com.umeng.analytics.b.g.t, "");
            } else {
                jSONObject.put(com.umeng.analytics.b.g.t, string);
            }
            a3 = com.umeng.a.d.l(j);
            jSONObject.put(com.umeng.analytics.b.g.G, a3[0]);
            jSONObject.put("language", a3[1]);
            jSONObject.put(com.umeng.analytics.b.g.E, com.umeng.a.d.k(j));
            jSONObject.put(com.umeng.analytics.b.g.H, com.umeng.a.d.g(j));
            try {
                jSONObject.put(com.umeng.analytics.b.g.K, a2.getInt("successful_request", 0));
                jSONObject.put(com.umeng.analytics.b.g.L, a2.getInt(com.umeng.analytics.b.g.L, 0));
                jSONObject.put(com.umeng.analytics.b.g.M, a2.getInt("last_request_spent_ms", 0));
            } catch (Exception e) {
            }
            try {
                a.a.a.d a4 = com.umeng.analytics.c.h.a(j).a();
                if (a4 != null) {
                    jSONObject.put(com.umeng.analytics.b.g.N, Base64.encodeToString(new a.a.a.g().a(a4), 0));
                }
            } catch (Exception e2) {
            }
            a.put(com.umeng.analytics.a.A, jSONObject);
            jSONObject3.put(com.umeng.analytics.b.g.l, jSONObject.getString(com.umeng.analytics.b.g.l)).put("device_id", jSONObject.getString("device_id")).put(com.umeng.analytics.b.g.v, jSONObject.getString(com.umeng.analytics.b.g.v)).put("version", jSONObject.getString(com.umeng.analytics.b.g.h)).put(com.umeng.analytics.b.g.a, jSONObject.getString(com.umeng.analytics.b.g.a)).put(com.umeng.analytics.b.g.b, jSONObject.getString(com.umeng.analytics.b.g.b));
            if (!a(jSONObject)) {
                a = null;
            }
            if (g.a && jSONObject3.length() > 0) {
                g.a(String.valueOf(jSONObject3));
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
            k.a(j).f();
            return null;
        }
    }

    private void d(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(com.umeng.analytics.e.a.a(j).f(), com.umeng.analytics.e.a.a(j).e());
        jSONObject.put(com.umeng.analytics.b.g.aq, jSONObject2);
    }

    private String[] a(Editor editor, SharedPreferences sharedPreferences, String str, String str2) {
        String string = sharedPreferences.getString("pre_version", "");
        String string2 = sharedPreferences.getString("pre_date", "");
        String string3 = sharedPreferences.getString("cur_version", "");
        String b = com.umeng.a.d.b(j);
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
        jSONObject.put(com.umeng.analytics.b.g.R, str);
        if (TextUtils.isEmpty(str2)) {
            str2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
        }
        jSONObject.put(com.umeng.analytics.b.g.S, str2);
    }

    public boolean a(JSONObject jSONObject) {
        if (TextUtils.isEmpty("device_id") || TextUtils.isEmpty(com.umeng.analytics.b.g.s) || TextUtils.isEmpty(com.umeng.analytics.b.g.r) || TextUtils.isEmpty(com.umeng.analytics.b.g.a) || TextUtils.isEmpty(com.umeng.analytics.b.g.b) || TextUtils.isEmpty(com.umeng.analytics.b.g.f) || TextUtils.isEmpty(com.umeng.analytics.b.g.e) || TextUtils.isEmpty(com.umeng.analytics.b.g.d)) {
            return false;
        }
        return true;
    }

    private boolean a(boolean z) {
        if (!com.umeng.a.d.j(j)) {
            g.d("network is unavailable");
            return false;
        } else if (this.c.f()) {
            return true;
        } else {
            return this.g.b(z).a(z);
        }
    }

    private void d() {
        try {
            if (this.b.g()) {
                o oVar = new o(j, this.c);
                oVar.a((l) this);
                if (this.d.d()) {
                    oVar.b(true);
                }
                oVar.a();
                return;
            }
            JSONObject a = a(new int[0]);
            if (a.length() > 0) {
                o oVar2 = new o(j, this.c);
                oVar2.a((l) this);
                if (this.d.d()) {
                    oVar2.b(true);
                }
                oVar2.a(a);
                oVar2.a(e());
                oVar2.a();
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

    public void a(com.umeng.analytics.c.h.a aVar) {
        this.e.a(aVar);
        this.d.a(aVar);
        this.f.a(aVar);
        this.g.a(aVar);
        this.a = com.umeng.analytics.c.h.a(j).b().b(null);
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
                com.umeng.analytics.b.f.a(j).a(this.l);
                this.l = new JSONArray();
            }
            m.a(j).edit().putLong(q, this.p).putInt(r, this.n).putInt(s, this.o).commit();
        } catch (Throwable th) {
        }
    }

    private void f() {
        this.n = 0;
        this.o = 0;
        this.p = System.currentTimeMillis();
    }
}
