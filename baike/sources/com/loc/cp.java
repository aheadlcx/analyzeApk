package com.loc;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.net.Proxy;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.alipay.android.phone.mrpc.core.gwprotocol.JsonSerializer;
import com.alipay.sdk.cons.c;
import com.baidu.mobstat.Config;
import java.util.concurrent.ExecutorService;

public final class cp {
    private static cp c = null;
    cs a = null;
    volatile int b = 0;
    private Object d = null;
    private Context e = null;
    private ExecutorService f = null;
    private boolean g = false;
    private boolean h = true;

    class a implements Runnable {
        cs a = null;
        final /* synthetic */ cp b;

        a(cp cpVar, cs csVar) {
            this.b = cpVar;
            this.a = csVar;
        }

        public final void run() {
            cp cpVar = this.b;
            cpVar.b++;
            this.b.b(this.a);
            cpVar = this.b;
            cpVar.b--;
        }
    }

    private cp() {
    }

    private cp(Context context) {
        this.e = context;
        Context context2 = this.e;
        try {
            s a = cw.a("HttpDNS", JsonSerializer.VERSION);
            if (db.a(context2, a)) {
                try {
                    this.d = au.a(context2, a, "com.autonavi.httpdns.HttpDnsManager", null, new Class[]{Context.class}, new Object[]{context2});
                } catch (Throwable th) {
                }
                db.a(context2, "HttpDns", this.d == null ? 0 : 1);
            }
        } catch (Throwable th2) {
            cw.a(th2, "DNSManager", "initHttpDns");
        }
    }

    public static cp a(Context context) {
        if (c == null) {
            c = new cp(context);
        }
        return c;
    }

    private boolean c() {
        return (this.d == null || e() || dd.b(this.e, "pref", "dns_faile_count_total", 0) >= 2) ? false : true;
    }

    private String d() {
        if (c()) {
            try {
                return (String) cz.a(this.d, "getIpByHostAsync", "apilocatesrc.amap.com");
            } catch (Throwable th) {
                db.a(this.e, "HttpDns");
            }
        }
        return null;
    }

    private boolean e() {
        int parseInt;
        String str = null;
        try {
            if (VERSION.SDK_INT >= 14) {
                str = System.getProperty("http.proxyHost");
                String property = System.getProperty("http.proxyPort");
                if (property == null) {
                    property = "-1";
                }
                parseInt = Integer.parseInt(property);
            } else {
                str = Proxy.getHost(this.e);
                parseInt = Proxy.getPort(this.e);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            parseInt = -1;
        }
        return (str == null || parseInt == -1) ? false : true;
    }

    public final void a() {
        if (this.g) {
            dd.a(this.e, "pref", "dns_faile_count_total", 0);
        }
    }

    public final void a(cs csVar) {
        try {
            this.g = false;
            if (csVar != null) {
                this.a = csVar;
                String c = csVar.c();
                if (!c.substring(0, c.indexOf(Config.TRACE_TODAY_VISIT_SPLIT)).equalsIgnoreCase("https") && !"http://abroad.apilocate.amap.com/mobile/binary".equals(c) && c()) {
                    CharSequence a;
                    CharSequence d = d();
                    if (this.h && TextUtils.isEmpty(d)) {
                        this.h = false;
                        a = dd.a(this.e, "ip", "last_ip", "");
                    } else {
                        a = d;
                    }
                    if (!TextUtils.isEmpty(a)) {
                        String str = "last_ip";
                        Editor edit = this.e.getSharedPreferences("ip", 0).edit();
                        edit.putString(str, a);
                        dd.a(edit);
                        csVar.g = "http://apilocatesrc.amap.com/mobile/binary".replace("apilocatesrc.amap.com", a);
                        csVar.b().put(c.f, "apilocatesrc.amap.com");
                        this.g = true;
                    }
                }
            }
        } catch (Throwable th) {
        }
    }

    public final void b() {
        try {
            if (this.b <= 5 && this.g) {
                if (this.f == null) {
                    this.f = z.b();
                }
                if (!this.f.isShutdown()) {
                    this.f.submit(new a(this, this.a));
                }
            }
        } catch (Throwable th) {
        }
    }

    final synchronized void b(cs csVar) {
        try {
            csVar.g = "http://apilocatesrc.amap.com/mobile/binary";
            long b = dd.b(this.e, "pref", "dns_faile_count_total", 0);
            if (b < 2) {
                bi.a();
                bi.a(csVar, false);
                b++;
                if (b >= 2) {
                    dc.a(this.e, "HttpDNS", "dns failed too much");
                }
                dd.a(this.e, "pref", "dns_faile_count_total", b);
            }
        } catch (Throwable th) {
            dd.a(this.e, "pref", "dns_faile_count_total", 0);
        }
    }
}
