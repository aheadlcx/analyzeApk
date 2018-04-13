package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.proguard.l;
import com.umeng.commonsdk.proguard.o;
import com.umeng.commonsdk.proguard.u;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.d;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.statistics.proto.c;
import com.xiaomi.mipush.sdk.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class e {
    public static final long a = 86400000;
    public static e b;
    private static Object j = new Object();
    private final String c = "umeng_it.cache";
    private File d;
    private c e = null;
    private long f;
    private long g;
    private Set<a> h = new HashSet();
    private a i = null;

    public static class a {
        private Context a;
        private Set<String> b = new HashSet();

        public a(Context context) {
            this.a = context;
        }

        public synchronized boolean a(String str) {
            return !this.b.contains(str);
        }

        public synchronized void b(String str) {
            this.b.add(str);
        }

        public void c(String str) {
            this.b.remove(str);
        }

        public synchronized void a() {
            if (!this.b.isEmpty()) {
                StringBuilder stringBuilder = new StringBuilder();
                for (String append : this.b) {
                    stringBuilder.append(append);
                    stringBuilder.append(',');
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                PreferenceWrapper.getDefault(this.a).edit().putString("invld_id", stringBuilder.toString()).commit();
            }
        }

        public synchronized void b() {
            Object string = PreferenceWrapper.getDefault(this.a).getString("invld_id", null);
            if (!TextUtils.isEmpty(string)) {
                String[] split = string.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
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

    e(Context context) {
        this.d = new File(context.getFilesDir(), "umeng_it.cache");
        this.g = 86400000;
        this.i = new a(context);
        this.i.b();
    }

    public static synchronized e a(Context context) {
        e eVar;
        synchronized (e.class) {
            if (b == null) {
                b = new e(context);
                b.a(new f(context));
                b.a(new b(context));
                b.a(new r(context));
                b.a(new d(context));
                b.a(new c(context));
                b.a(new g(context));
                b.a(new j());
                b.a(new s(context));
                a qVar = new q(context);
                if (!TextUtils.isEmpty(qVar.f())) {
                    b.a(qVar);
                }
                qVar = new i(context);
                if (qVar.g()) {
                    b.a(qVar);
                    b.a(new h(context));
                    qVar.i();
                }
                if (d.b != 1) {
                    b.a(new p(context));
                    b.a(new m(context));
                    b.a(new o(context));
                    b.a(new n(context));
                    b.a(new l(context));
                    b.a(new k(context));
                }
                b.e();
            }
            eVar = b;
        }
        return eVar;
    }

    private boolean a(a aVar) {
        if (this.i.a(aVar.b())) {
            return this.h.add(aVar);
        }
        if (AnalyticsConstants.UM_DEBUG) {
            MLog.w("invalid domain: " + aVar.b());
        }
        return false;
    }

    public void a(long j) {
        this.g = j;
    }

    public synchronized void a() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f >= this.g) {
            Object obj = null;
            for (a aVar : this.h) {
                if (aVar.c()) {
                    if (aVar.a()) {
                        obj = 1;
                        if (!aVar.c()) {
                            this.i.b(aVar.b());
                        }
                    }
                    obj = obj;
                }
            }
            if (obj != null) {
                g();
                this.i.a();
                f();
            }
            this.f = currentTimeMillis;
        }
    }

    public synchronized c b() {
        return this.e;
    }

    private synchronized void g() {
        c cVar = new c();
        Map hashMap = new HashMap();
        List arrayList = new ArrayList();
        for (a aVar : this.h) {
            if (aVar.c()) {
                if (aVar.d() != null) {
                    hashMap.put(aVar.b(), aVar.d());
                }
                if (!(aVar.e() == null || aVar.e().isEmpty())) {
                    arrayList.addAll(aVar.e());
                }
            }
        }
        cVar.a(arrayList);
        cVar.a(hashMap);
        synchronized (this) {
            this.e = cVar;
        }
    }

    public String c() {
        return null;
    }

    public synchronized void d() {
        Object obj = null;
        synchronized (this) {
            for (a aVar : this.h) {
                if (aVar.c()) {
                    Object obj2;
                    if (aVar.e() == null || aVar.e().isEmpty()) {
                        obj2 = obj;
                    } else {
                        aVar.a(null);
                        obj2 = 1;
                    }
                    obj = obj2;
                }
            }
            if (obj != null) {
                this.e.b(false);
                f();
            }
        }
    }

    public synchronized void e() {
        c h = h();
        if (h != null) {
            List<a> arrayList = new ArrayList(this.h.size());
            synchronized (this) {
                this.e = h;
                for (a aVar : this.h) {
                    aVar.a(this.e);
                    if (!aVar.c()) {
                        arrayList.add(aVar);
                    }
                }
                for (a aVar2 : arrayList) {
                    this.h.remove(aVar2);
                }
            }
            g();
        }
    }

    public synchronized void f() {
        if (this.e != null) {
            a(this.e);
        }
    }

    private c h() {
        InputStream fileInputStream;
        Exception e;
        Throwable th;
        synchronized (j) {
            if (this.d.exists()) {
                try {
                    fileInputStream = new FileInputStream(this.d);
                    try {
                        byte[] readStreamToByteArray = HelperUtils.readStreamToByteArray(fileInputStream);
                        l cVar = new c();
                        new o().a(cVar, readStreamToByteArray);
                        HelperUtils.safeClose(fileInputStream);
                        return cVar;
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            e.printStackTrace();
                            HelperUtils.safeClose(fileInputStream);
                            return null;
                        } catch (Throwable th2) {
                            th = th2;
                            HelperUtils.safeClose(fileInputStream);
                            throw th;
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    fileInputStream = null;
                    e.printStackTrace();
                    HelperUtils.safeClose(fileInputStream);
                    return null;
                } catch (Throwable th3) {
                    fileInputStream = null;
                    th = th3;
                    HelperUtils.safeClose(fileInputStream);
                    throw th;
                }
            }
            return null;
        }
    }

    private void a(c cVar) {
        synchronized (j) {
            if (cVar != null) {
                try {
                    byte[] a;
                    synchronized (this) {
                        a = new u().a(cVar);
                    }
                    if (a != null) {
                        HelperUtils.writeFile(this.d, a);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
