package com.umeng.analytics.d;

import a.a.a.g;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.a.d;
import com.umeng.a.k;
import com.umeng.a.k.b;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.a;
import com.umeng.analytics.a.d.e;
import com.umeng.analytics.c.c;
import com.umeng.analytics.c.f;
import com.umeng.analytics.c.h;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.json.JSONObject;

public class o {
    private static final int a = 1;
    private static final int b = 2;
    private static final int c = 3;
    private static Context g;
    private f d;
    private h e;
    private final int f = 1;
    private q h;
    private i i;
    private JSONObject j;
    private boolean k = false;
    private boolean l;

    public o(Context context, q qVar) {
        this.d = f.a(context);
        this.e = h.a(context);
        g = context;
        this.h = qVar;
        this.i = new i(context);
        this.i.a(this.h);
    }

    public void a(JSONObject jSONObject) {
        this.j = jSONObject;
    }

    public void a(boolean z) {
        this.k = z;
    }

    public void b(boolean z) {
        this.l = z;
    }

    public void a(l lVar) {
        this.e.a(lVar);
    }

    public void a() {
        try {
            if (this.j != null) {
                c();
            } else {
                b();
            }
        } catch (Throwable th) {
        }
        try {
            if (d.i(g)) {
                SharedPreferences a = m.a(g);
                if (a != null) {
                    CharSequence string = a.getString(a.r, "");
                    long b = e.b(System.currentTimeMillis());
                    if (TextUtils.isEmpty(string)) {
                        long j = a.getLong(a.s, -1);
                        int i = a.getInt(a.t, 0);
                        if (j == -1) {
                            a.edit().putInt(a.t, i + 1).commit();
                            this.i.a();
                        } else if (b != j) {
                            a.edit().putInt(a.t, 1).commit();
                            this.i.a();
                        } else if (i < 2) {
                            a.edit().putInt(a.t, i + 1).commit();
                            this.i.a();
                        }
                        a.edit().putLong(a.s, b).commit();
                        return;
                    }
                    return;
                }
                this.i.a();
            }
        } catch (Throwable th2) {
        }
    }

    private void b() {
        k.a(g).h().a(new b(this) {
            final /* synthetic */ o a;

            {
                this.a = r1;
            }

            public void a(File file) {
            }

            public boolean b(File file) {
                Throwable th;
                InputStream fileInputStream;
                try {
                    fileInputStream = new FileInputStream(file);
                    try {
                        byte[] b = com.umeng.a.e.b(fileInputStream);
                        try {
                            com.umeng.a.e.c(fileInputStream);
                            byte[] a = this.a.i.a(b);
                            int i;
                            if (a == null) {
                                i = 1;
                            } else {
                                i = this.a.a(a);
                            }
                            if (!this.a.l && r2 == 1) {
                                return false;
                            }
                            return true;
                        } catch (Exception e) {
                            return false;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        com.umeng.a.e.c(fileInputStream);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileInputStream = null;
                    com.umeng.a.e.c(fileInputStream);
                    throw th;
                }
            }

            public void c(File file) {
                this.a.h.k();
            }
        });
    }

    private void c() {
        try {
            this.d.a();
            try {
                CharSequence encodeToString = Base64.encodeToString(new g().a(this.d.b()), 0);
                if (!TextUtils.isEmpty(encodeToString)) {
                    JSONObject jSONObject = this.j.getJSONObject(a.A);
                    jSONObject.put(com.umeng.analytics.b.g.O, encodeToString);
                    this.j.put(a.A, jSONObject);
                }
            } catch (Exception e) {
            }
            byte[] bytes = String.valueOf(this.j).getBytes();
            if (bytes != null && !com.umeng.a.b.a(g, bytes)) {
                c b;
                int i;
                if (this.k) {
                    b = c.b(g, AnalyticsConfig.getAppkey(g), bytes);
                } else {
                    b = c.a(g, AnalyticsConfig.getAppkey(g), bytes);
                }
                byte[] c = b.c();
                k.a(g).f();
                bytes = this.i.a(c);
                if (bytes == null) {
                    i = 1;
                } else {
                    i = a(bytes);
                }
                switch (i) {
                    case 1:
                        if (!this.l) {
                            k.a(g).a(c);
                            return;
                        }
                        return;
                    case 2:
                        this.d.d();
                        this.h.k();
                        return;
                    case 3:
                        this.h.k();
                        return;
                    default:
                        return;
                }
            }
        } catch (Throwable th) {
        }
    }

    private int a(byte[] bArr) {
        com.umeng.analytics.f.g gVar = new com.umeng.analytics.f.g();
        try {
            new a.a.a.e(new a.a.a.b.a.a()).a(gVar, bArr);
            if (gVar.a == 1) {
                this.e.b(gVar.i());
                this.e.d();
            }
            com.umeng.a.g.b("send log:" + gVar.f());
        } catch (Throwable th) {
        }
        if (gVar.a == 1) {
            return 2;
        }
        return 3;
    }
}
