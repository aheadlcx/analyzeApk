package com.umeng.analytics.c;

import a.a.a.g;
import android.content.Context;
import android.text.TextUtils;
import com.umeng.a.e;
import com.umeng.analytics.d.m;
import com.umeng.analytics.f.d;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class f {
    public static f a;
    private final String b = "umeng_it.cache";
    private File c;
    private d d = null;
    private long e;
    private long f;
    private Set<a> g = new HashSet();
    private a h = null;

    public static class a {
        private Context a;
        private Set<String> b = new HashSet();

        public a(Context context) {
            this.a = context;
        }

        public boolean a(String str) {
            return !this.b.contains(str);
        }

        public void b(String str) {
            this.b.add(str);
        }

        public void c(String str) {
            this.b.remove(str);
        }

        public void a() {
            if (!this.b.isEmpty()) {
                StringBuilder stringBuilder = new StringBuilder();
                for (String append : this.b) {
                    stringBuilder.append(append);
                    stringBuilder.append(',');
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                m.a(this.a).edit().putString("invld_id", stringBuilder.toString()).commit();
            }
        }

        public void b() {
            Object string = m.a(this.a).getString("invld_id", null);
            if (!TextUtils.isEmpty(string)) {
                String[] split = string.split(",");
                if (split != null) {
                    for (CharSequence charSequence : split) {
                        if (!TextUtils.isEmpty(charSequence)) {
                            this.b.add(charSequence);
                        }
                    }
                }
            }
        }
    }

    f(Context context) {
        this.c = new File(context.getFilesDir(), "umeng_it.cache");
        this.f = com.umeng.analytics.a.i;
        this.h = new a(context);
        this.h.b();
    }

    public static synchronized f a(Context context) {
        f fVar;
        synchronized (f.class) {
            if (a == null) {
                a = new f(context);
                a.a(new g(context));
                a.a(new b(context));
                a.a(new o(context));
                a.a(new e(context));
                a.a(new d(context));
                a.a(new i(context));
                a.a(new l());
                a.a(new p(context));
                a nVar = new n(context);
                if (!TextUtils.isEmpty(nVar.f())) {
                    a.a(nVar);
                }
                nVar = new k(context);
                if (nVar.g()) {
                    a.a(nVar);
                    a.a(new j(context));
                    nVar.i();
                }
                a.e();
            }
            fVar = a;
        }
        return fVar;
    }

    public boolean a(a aVar) {
        if (this.h.a(aVar.b())) {
            return this.g.add(aVar);
        }
        return false;
    }

    public void a(long j) {
        this.f = j;
    }

    public void a() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.e >= this.f) {
            Object obj = null;
            for (a aVar : this.g) {
                if (aVar.c()) {
                    if (aVar.a()) {
                        obj = 1;
                        if (!aVar.c()) {
                            this.h.b(aVar.b());
                        }
                    }
                    obj = obj;
                }
            }
            if (obj != null) {
                g();
                this.h.a();
                f();
            }
            this.e = currentTimeMillis;
        }
    }

    public d b() {
        return this.d;
    }

    private void g() {
        d dVar = new d();
        Map hashMap = new HashMap();
        List arrayList = new ArrayList();
        for (a aVar : this.g) {
            if (aVar.c()) {
                if (aVar.d() != null) {
                    hashMap.put(aVar.b(), aVar.d());
                }
                if (!(aVar.e() == null || aVar.e().isEmpty())) {
                    arrayList.addAll(aVar.e());
                }
            }
        }
        dVar.a(arrayList);
        dVar.a(hashMap);
        synchronized (this) {
            this.d = dVar;
        }
    }

    public String c() {
        return null;
    }

    public void d() {
        boolean z = false;
        for (a aVar : this.g) {
            if (aVar.c()) {
                boolean z2;
                if (aVar.e() == null || aVar.e().isEmpty()) {
                    z2 = z;
                } else {
                    aVar.a(null);
                    z2 = true;
                }
                z = z2;
            }
        }
        if (z) {
            this.d.b(false);
            f();
        }
    }

    public void e() {
        d h = h();
        if (h != null) {
            List<a> arrayList = new ArrayList(this.g.size());
            synchronized (this) {
                this.d = h;
                for (a aVar : this.g) {
                    aVar.a(this.d);
                    if (!aVar.c()) {
                        arrayList.add(aVar);
                    }
                }
                for (a aVar2 : arrayList) {
                    this.g.remove(aVar2);
                }
            }
            g();
        }
    }

    public void f() {
        if (this.d != null) {
            a(this.d);
        }
    }

    private d h() {
        Exception e;
        Throwable th;
        if (!this.c.exists()) {
            return null;
        }
        InputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(this.c);
            try {
                byte[] b = e.b(fileInputStream);
                a.a.a.d dVar = new d();
                new a.a.a.e().a(dVar, b);
                e.c(fileInputStream);
                return dVar;
            } catch (Exception e2) {
                e = e2;
                try {
                    e.printStackTrace();
                    e.c(fileInputStream);
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    e.c(fileInputStream);
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            fileInputStream = null;
            e.printStackTrace();
            e.c(fileInputStream);
            return null;
        } catch (Throwable th3) {
            fileInputStream = null;
            th = th3;
            e.c(fileInputStream);
            throw th;
        }
    }

    private void a(d dVar) {
        if (dVar != null) {
            try {
                byte[] a;
                synchronized (this) {
                    a = new g().a(dVar);
                }
                if (a != null) {
                    e.a(this.c, a);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
