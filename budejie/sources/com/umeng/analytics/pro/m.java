package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

public class m {
    private static final int i = 48;
    private static final int j = 49;
    private static Context k;
    private h a;
    private o b;
    private p c;
    private boolean d;
    private boolean e;
    private long f;
    private final String g;
    private final String h;
    private List<String> l;
    private a m;
    private final Thread n;

    private static class a extends Handler {
        private final WeakReference<m> a;

        public a(m mVar) {
            this.a = new WeakReference(mVar);
        }

        public void handleMessage(Message message) {
            if (this.a != null) {
                switch (message.what) {
                    case 48:
                        sendEmptyMessageDelayed(48, q.c(System.currentTimeMillis()));
                        m.a(m.k).p();
                        return;
                    case 49:
                        sendEmptyMessageDelayed(49, q.d(System.currentTimeMillis()));
                        m.a(m.k).o();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private static class b {
        private static final m a = new m();

        private b() {
        }
    }

    public boolean a() {
        return this.d;
    }

    private m() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = false;
        this.e = false;
        this.f = 0;
        this.g = "main_fest_mode";
        this.h = "main_fest_timestamp";
        this.l = new ArrayList();
        this.m = null;
        this.n = new Thread(new Runnable(this) {
            final /* synthetic */ m a;

            {
                this.a = r1;
            }

            public void run() {
                Looper.prepare();
                if (this.a.m == null) {
                    this.a.m = new a(this.a);
                }
                this.a.h();
            }
        });
        if (k != null) {
            if (this.a == null) {
                this.a = new h();
            }
            if (this.b == null) {
                this.b = o.a(k);
            }
            if (this.c == null) {
                this.c = new p();
            }
        }
        this.n.start();
    }

    private void h() {
        long currentTimeMillis = System.currentTimeMillis();
        this.m.sendEmptyMessageDelayed(48, q.c(currentTimeMillis));
        this.m.sendEmptyMessageDelayed(49, q.d(currentTimeMillis));
    }

    public static final m a(Context context) {
        k = context;
        return b.a;
    }

    public void a(final f fVar) {
        if (!this.d) {
            bz.b(new cb(this) {
                final /* synthetic */ m b;

                public void a() {
                    try {
                        this.b.b.a(new f(this) {
                            final /* synthetic */ AnonymousClass6 a;

                            {
                                this.a = r1;
                            }

                            public void a(Object obj, boolean z) {
                                if (obj instanceof Map) {
                                    this.a.b.a.a((Map) obj);
                                } else if (!(obj instanceof String) && (obj instanceof Boolean)) {
                                }
                                this.a.b.d = true;
                            }
                        });
                        this.b.l();
                        this.b.q();
                        fVar.a("success", false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void a(final f fVar, Map<List<String>, l> map) {
        l lVar = (l) map.values().toArray()[0];
        List a = lVar.a();
        if (this.l.size() > 0 && this.l.contains(d.a(a))) {
            this.a.a(new f(this) {
                final /* synthetic */ m b;

                public void a(Object obj, boolean z) {
                    if (obj instanceof h) {
                        this.b.a = (h) obj;
                    }
                    fVar.a("success", false);
                }
            }, lVar);
        } else if (this.e) {
            a(lVar, a);
        } else if (i()) {
            String a2 = d.a(a);
            if (!this.l.contains(a2)) {
                this.l.add(a2);
            }
            this.a.a(new f(this) {
                final /* synthetic */ m a;

                {
                    this.a = r1;
                }

                public void a(Object obj, boolean z) {
                    this.a.a = (h) obj;
                }
            }, a, lVar);
        } else {
            a(lVar, a);
            j();
        }
    }

    private void a(l lVar, List<String> list) {
        this.a.a(new f(this) {
            final /* synthetic */ m a;

            {
                this.a = r1;
            }

            public void a(Object obj, boolean z) {
                if (obj instanceof h) {
                    this.a.a = (h) obj;
                } else if (obj instanceof Boolean) {
                    this.a.n();
                }
            }
        }, lVar, list, this.l);
    }

    private boolean i() {
        if (this.l.size() < n.a().d()) {
            return true;
        }
        return false;
    }

    private void j() {
        SharedPreferences a = ba.a(k);
        if (!a.getBoolean("main_fest_mode", false)) {
            this.e = true;
            Editor edit = a.edit();
            edit.putBoolean("main_fest_mode", true);
            edit.putLong("main_fest_timestamp", System.currentTimeMillis());
            edit.commit();
        }
    }

    private void k() {
        Editor edit = ba.a(k).edit();
        edit.putBoolean("main_fest_mode", false);
        edit.putLong("main_fest_timestamp", 0);
        edit.commit();
        this.e = false;
    }

    private void l() {
        SharedPreferences a = ba.a(k);
        this.e = a.getBoolean("main_fest_mode", false);
        this.f = a.getLong("main_fest_timestamp", 0);
    }

    public JSONObject b() {
        JSONObject a = this.b.a();
        JSONObject jSONObject = new JSONObject();
        if (a == null || a.length() <= 0) {
            return null;
        }
        for (String str : this.l) {
            if (a.has(str)) {
                try {
                    jSONObject.put(str, a.opt(str));
                } catch (Exception e) {
                }
            }
        }
        return jSONObject;
    }

    public JSONObject c() {
        if (this.c.a().size() > 0) {
            this.b.b(new f(this) {
                final /* synthetic */ m a;

                {
                    this.a = r1;
                }

                public void a(Object obj, boolean z) {
                    if (obj instanceof String) {
                        this.a.c.b();
                    }
                }
            }, this.c.a());
        }
        return this.b.b(new f());
    }

    public void b(f fVar) {
        boolean z = false;
        if (this.e) {
            if (this.f == 0) {
                l();
            }
            z = q.a(System.currentTimeMillis(), this.f);
        }
        if (!z) {
            k();
            this.l.clear();
        }
        this.c.b();
        this.b.a(new f(this) {
            final /* synthetic */ m a;

            {
                this.a = r1;
            }

            public void a(Object obj, boolean z) {
                if (obj.equals("success")) {
                    this.a.m();
                }
            }
        }, z);
    }

    private void m() {
        for (Entry key : this.a.a().entrySet()) {
            List list = (List) key.getKey();
            if (!this.l.contains(list)) {
                this.l.add(d.a(list));
            }
        }
        if (this.l.size() > 0) {
            this.b.a(new f(), this.l);
        }
    }

    private void n() {
        this.c.a(new f(this) {
            final /* synthetic */ m a;

            {
                this.a = r1;
            }

            public void a(Object obj, boolean z) {
                this.a.c = (p) obj;
            }
        }, com.umeng.analytics.a.u);
    }

    public void a(long j, long j2, String str) {
        this.b.a(new f(this) {
            final /* synthetic */ m a;

            {
                this.a = r1;
            }

            public void a(Object obj, boolean z) {
                if (!obj.equals("success")) {
                }
            }
        }, str, j, j2);
    }

    private void o() {
        try {
            if (this.a.a().size() > 0) {
                this.b.c(new f(this) {
                    final /* synthetic */ m a;

                    {
                        this.a = r1;
                    }

                    public void a(Object obj, boolean z) {
                        if (obj instanceof String) {
                            this.a.a.d();
                        }
                    }
                }, this.a.a());
            }
            if (this.c.a().size() > 0) {
                this.b.b(new f(this) {
                    final /* synthetic */ m a;

                    {
                        this.a = r1;
                    }

                    public void a(Object obj, boolean z) {
                        if (obj instanceof String) {
                            this.a.c.b();
                        }
                    }
                }, this.c.a());
            }
            if (this.l.size() > 0) {
                this.b.a(new f(), this.l);
            }
        } catch (Throwable th) {
            by.b("converyMemoryToDataTable happen error: " + th.toString());
        }
    }

    private void p() {
        try {
            if (this.a.a().size() > 0) {
                this.b.a(new f(this) {
                    final /* synthetic */ m a;

                    {
                        this.a = r1;
                    }

                    public void a(Object obj, boolean z) {
                    }
                }, this.a.a());
            }
            if (this.c.a().size() > 0) {
                this.b.b(new f(this) {
                    final /* synthetic */ m a;

                    {
                        this.a = r1;
                    }

                    public void a(Object obj, boolean z) {
                        if (obj instanceof String) {
                            this.a.c.b();
                        }
                    }
                }, this.c.a());
            }
            if (this.l.size() > 0) {
                this.b.a(new f(), this.l);
            }
        } catch (Throwable th) {
            by.b("convertMemoryToCacheTable happen error: " + th.toString());
        }
    }

    private void q() {
        List b = this.b.b();
        if (b != null) {
            this.l = b;
        }
    }

    public void d() {
        p();
    }

    public void e() {
        p();
    }

    public void f() {
        p();
    }
}
