package com.alibaba.sdk.android.httpdns;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class t {
    private static t a = null;
    /* renamed from: a */
    private String f15a;
    private boolean d;
    private long e;
    private ExecutorService pool;

    private t() {
        this.e = 0;
        this.d = true;
        this.f15a = null;
        this.pool = null;
        this.pool = Executors.newSingleThreadExecutor();
    }

    public static t a() {
        if (a == null) {
            synchronized (t.class) {
                if (a == null) {
                    a = new t();
                }
            }
        }
        return a;
    }

    private boolean b() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.e != 0 && currentTimeMillis - this.e < 30000) {
            return false;
        }
        this.e = currentTimeMillis;
        return true;
    }

    public synchronized void a(boolean z) {
        this.d = z;
    }

    public synchronized void c() {
        this.e = 0;
    }

    public synchronized void f(String str) {
        if (str != null) {
            this.f15a = str;
        }
        if (this.d && b() && this.f15a != null) {
            f.d("launch a sniff task");
            Callable lVar = new l(this.f15a, p.SNIFF_HOST);
            lVar.a(0);
            this.pool.submit(lVar);
            this.f15a = null;
        } else {
            f.d("hostname is null or sniff too often or sniffer is turned off");
        }
    }
}
