package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.a;
import com.umeng.analytics.pro.cc.b;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.json.JSONObject;

public class bc {
    private static final int a = 1;
    private static final int b = 2;
    private static final int c = 3;
    private static Context g;
    private ad d;
    private af e;
    private final int f = 1;
    private be h;
    private aw i;
    private JSONObject j;
    private boolean k = false;
    private boolean l;

    public bc(Context context, be beVar) {
        this.d = ad.a(context);
        this.e = af.a(context);
        g = context;
        this.h = beVar;
        this.i = new aw(context);
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

    public void a(az azVar) {
        this.e.a(azVar);
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
            if (bv.k(g)) {
                SharedPreferences a = ba.a(g);
                if (a != null) {
                    CharSequence string = a.getString(a.r, "");
                    long b = q.b(System.currentTimeMillis());
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
        cc.a(g).i().a(new b(this) {
            final /* synthetic */ bc a;

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
                        byte[] b = bw.b(fileInputStream);
                        try {
                            bw.c(fileInputStream);
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
                        bw.c(fileInputStream);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileInputStream = null;
                    bw.c(fileInputStream);
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
                CharSequence encodeToString = Base64.encodeToString(new cp().a(this.d.b()), 0);
                if (!TextUtils.isEmpty(encodeToString)) {
                    JSONObject jSONObject = this.j.getJSONObject(a.A);
                    jSONObject.put(x.O, encodeToString);
                    this.j.put(a.A, jSONObject);
                }
            } catch (Exception e) {
            }
            byte[] bytes = String.valueOf(this.j).getBytes();
            if (bytes != null && !bt.a(g, bytes)) {
                aa b;
                int i;
                if (this.k) {
                    b = aa.b(g, AnalyticsConfig.getAppkey(g), bytes);
                } else {
                    b = aa.a(g, AnalyticsConfig.getAppkey(g), bytes);
                }
                byte[] c = b.c();
                cc.a(g).g();
                bytes = this.i.a(c);
                if (bytes == null) {
                    i = 1;
                } else {
                    i = a(bytes);
                }
                switch (i) {
                    case 1:
                        if (!this.l) {
                            cc.a(g).a(c);
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
        cg bpVar = new bp();
        try {
            new cj(new cy.a()).a(bpVar, bArr);
            if (bpVar.a == 1) {
                this.e.b(bpVar.i());
                this.e.d();
            }
            by.c("send log:" + bpVar.f());
        } catch (Throwable th) {
        }
        if (bpVar.a == 1) {
            return 2;
        }
        return 3;
    }
}
