package com.xiaomi.metoknlp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.xiaomi.metoknlp.a.e;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONObject;

public class b {
    private static boolean a = true;
    private static int b = 1;
    private static b c = null;
    private Context d;
    private SharedPreferences e = this.d.getSharedPreferences("config", 0);
    private List f = new ArrayList();

    private b(Context context) {
        this.d = context;
    }

    public static b a() {
        return c;
    }

    public static synchronized void a(Context context) {
        synchronized (b.class) {
            if (c == null) {
                c = new b(context);
            }
        }
    }

    public static boolean b() {
        return false;
    }

    public void a(g gVar) {
        if (gVar != null) {
            synchronized (this.f) {
                this.f.add(gVar);
            }
        }
    }

    public void a(String str) {
        Editor edit = this.e.edit();
        edit.putString("config_update_time", str);
        edit.commit();
    }

    public void c() {
        synchronized (this.f) {
            for (g a : this.f) {
                a.a();
            }
        }
    }

    public void d() {
        int i = 0;
        String k = k();
        String h = e.h();
        if (!k.equals(h)) {
            String a;
            String d = e.d();
            k = c.a("collect", "t_" + d);
            if (k == null || k.isEmpty()) {
                int i2 = 0;
                do {
                    a = c.a("collect", "t_" + d);
                    if (a != null && !a.isEmpty()) {
                        break;
                    }
                    i2++;
                } while (i2 != 5);
                if (i2 != 5) {
                    k = a;
                } else {
                    return;
                }
            }
            try {
                JSONObject jSONObject = new JSONObject(new JSONObject(k).getString("data"));
                a = this.e.getString("s_f", "");
                String optString = jSONObject.optString("s_f", "");
                boolean a2 = e.a(jSONObject.optInt("f_d_d", 0));
                Editor edit = this.e.edit();
                edit.putString("s_f", optString);
                edit.putBoolean("f_d_d", a2);
                edit.putString("m_s_u", jSONObject.optString("m_s_u", "https://metok.sys.miui.com"));
                edit.commit();
                Date date = new Date();
                date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
                if (!(a == null || a.isEmpty() || optString == null || optString.isEmpty())) {
                    Date parse = simpleDateFormat.parse(a);
                    date = simpleDateFormat.parse(optString);
                    if (date.before(parse) || date.equals(parse)) {
                        a(h);
                        c();
                        return;
                    }
                }
                k = c.a("collect", "f_" + d);
                if (k == null || k.isEmpty()) {
                    do {
                        k = c.a("collect", "f_" + d);
                        if (k != null && !k.isEmpty()) {
                            break;
                        }
                        i++;
                    } while (i != 5);
                    if (i == 5) {
                        return;
                    }
                }
                try {
                    jSONObject = new JSONObject(new JSONObject(k).getString("data"));
                    Editor edit2 = this.e.edit();
                    edit2.putLong("d_m_i", jSONObject.optLong("d_m_i", Long.MAX_VALUE));
                    edit2.putBoolean("d_n_s", e.a(jSONObject.optInt("d_n_s", b)));
                    edit2.putLong("d_s_t", jSONObject.optLong("d_s_t", Long.MAX_VALUE));
                    edit2.putLong("d_s_c_t", jSONObject.optLong("d_s_c_t", Long.MAX_VALUE));
                    edit2.commit();
                    a(h);
                    c();
                } catch (Exception e) {
                }
            } catch (Exception e2) {
            }
        }
    }

    public String e() {
        return this.e.getString("m_s_u", "https://metok.sys.miui.com");
    }

    public boolean f() {
        return this.e.getBoolean("f_d_d", true);
    }

    public long g() {
        return this.e.getLong("d_m_i", Long.MAX_VALUE);
    }

    public boolean h() {
        return this.e.getBoolean("d_n_s", a);
    }

    public long i() {
        return this.e.getLong("d_s_t", Long.MAX_VALUE);
    }

    public long j() {
        return this.e.getLong("d_s_c_t", Long.MAX_VALUE);
    }

    public String k() {
        return this.e.getString("config_update_time", "0");
    }
}
