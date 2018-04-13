package com.alibaba.sdk.android.httpdns;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.umeng.analytics.a;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class q {
    private static q a = null;
    private static long c = 0;
    /* renamed from: c */
    private static boolean f16c = false;
    private static String g = "https://";
    private static String h = null;
    /* renamed from: a */
    private SharedPreferences f17a;
    private long d;
    private int e;
    private ExecutorService pool;

    private q() {
        this.e = 0;
        this.f17a = null;
        this.pool = null;
        this.d = 0;
        this.pool = Executors.newSingleThreadExecutor();
    }

    public static q a() {
        if (a == null) {
            synchronized (q.class) {
                if (a == null) {
                    a = new q();
                }
            }
        }
        return a;
    }

    private void b() {
        if (this.e < c.f7c.length - 1) {
            this.e++;
        } else {
            this.e = 0;
        }
    }

    /* renamed from: a */
    synchronized void m10a() {
        if (System.currentTimeMillis() - this.d >= 300000) {
            f.d("update server ips from schedule center.");
            this.e = 0;
            this.pool.submit(new n(c.f7c.length - 1));
        } else {
            f.d("update server ips from schedule center too often, give up. ");
            u.f();
        }
    }

    synchronized void a(Context context) {
        if (!f16c) {
            synchronized (q.class) {
                if (!f16c) {
                    if (context != null) {
                        this.f17a = context.getSharedPreferences("httpdns_config_cache", 0);
                    }
                    h = this.f17a.getString("httpdns_server_ips", null);
                    if (h != null) {
                        c.a(h.split(VoiceWakeuperAidl.PARAMS_SEPARATE));
                    }
                    c = this.f17a.getLong("schedule_center_last_request_time", 0);
                    if (c == 0 || System.currentTimeMillis() - c >= a.i) {
                        t.a().a(false);
                        a();
                    }
                    f16c = true;
                }
            }
        }
    }

    synchronized void a(s sVar) {
        this.e = 0;
        HttpDns.switchDnsService(sVar.isEnabled());
        if (a(sVar.c())) {
            f.d("Scheduler center update success");
            this.d = System.currentTimeMillis();
            u.e();
        }
    }

    synchronized boolean a(String[] strArr) {
        boolean z = false;
        synchronized (this) {
            if (c.a(strArr)) {
                StringBuilder stringBuilder = new StringBuilder();
                for (String append : strArr) {
                    stringBuilder.append(append);
                    stringBuilder.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                if (this.f17a != null) {
                    Editor edit = this.f17a.edit();
                    edit.putString("httpdns_server_ips", stringBuilder.toString());
                    edit.putLong("schedule_center_last_request_time", System.currentTimeMillis());
                    edit.commit();
                    z = true;
                }
            }
        }
        return z;
    }

    synchronized void b(Throwable th) {
        if (th instanceof SocketTimeoutException) {
            b();
            if (this.e == 0) {
                this.d = System.currentTimeMillis();
                f.e("Scheduler center update failed");
                u.f();
            }
        }
    }

    synchronized String f() {
        return g + c.f7c[this.e] + "/sc/httpdns_config?account_id=" + c.b + "&platform=android&sdk_version=" + "1.1.1";
    }
}
