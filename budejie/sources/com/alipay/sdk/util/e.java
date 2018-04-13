package com.alipay.sdk.util;

import android.app.Activity;
import android.content.Intent;
import android.content.ServiceConnection;
import com.alipay.android.app.IAlixPay;
import com.alipay.android.app.IRemoteServiceCallback;
import com.alipay.sdk.app.statistic.c;

public class e {
    public static final String b = "failed";
    public Activity a;
    private IAlixPay c;
    private final Object d = IAlixPay.class;
    private boolean e;
    private a f;
    private ServiceConnection g = new f(this);
    private IRemoteServiceCallback h = new g(this);

    public interface a {
        void a();
    }

    public e(Activity activity, a aVar) {
        this.a = activity;
        this.f = aVar;
    }

    public final String a(String str) {
        try {
            l$a a = l.a(this.a, l.b);
            if (a.a()) {
                return b;
            }
            if (a != null && a.b > 78) {
                Intent intent = new Intent();
                intent.setClassName(l.b, "com.alipay.android.app.TransProcessPayActivity");
                this.a.startActivity(intent);
                Thread.sleep(200);
            }
            return b(str);
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a("biz", c.C, th);
        }
    }

    private void a(l$a l_a) throws InterruptedException {
        if (l_a != null && l_a.b > 78) {
            Intent intent = new Intent();
            intent.setClassName(l.b, "com.alipay.android.app.TransProcessPayActivity");
            this.a.startActivity(intent);
            Thread.sleep(200);
        }
    }

    private String b(String str) {
        Intent intent = new Intent();
        intent.setPackage(l.b);
        intent.setAction("com.eg.android.AlipayGphone.IAlixPay");
        String g = l.g(this.a);
        try {
            String g2;
            this.a.getApplicationContext().bindService(intent, this.g, 1);
            synchronized (this.d) {
                if (this.c == null) {
                    try {
                        this.d.wait((long) com.alipay.sdk.data.a.b().a());
                    } catch (Throwable e) {
                        com.alipay.sdk.app.statistic.a.a("biz", c.A, e);
                    }
                }
            }
            try {
                if (this.c == null) {
                    g2 = l.g(this.a);
                    com.alipay.sdk.app.statistic.a.a("biz", c.u, g + "|" + g2 + "|" + l.h(this.a));
                    g2 = b;
                    try {
                        this.c.unregisterCallback(this.h);
                    } catch (Throwable th) {
                    }
                    try {
                        this.a.unbindService(this.g);
                    } catch (Throwable th2) {
                    }
                    this.h = null;
                    this.g = null;
                    this.c = null;
                    if (!this.e) {
                        return g2;
                    }
                    this.a.setRequestedOrientation(0);
                    this.e = false;
                    return g2;
                }
                if (this.f != null) {
                    this.f.a();
                }
                if (this.a.getRequestedOrientation() == 0) {
                    this.a.setRequestedOrientation(1);
                    this.e = true;
                }
                this.c.registerCallback(this.h);
                g2 = this.c.Pay(str);
                try {
                    this.c.unregisterCallback(this.h);
                } catch (Throwable th3) {
                }
                try {
                    this.a.unbindService(this.g);
                } catch (Throwable th4) {
                }
                this.h = null;
                this.g = null;
                this.c = null;
                if (!this.e) {
                    return g2;
                }
                this.a.setRequestedOrientation(0);
                this.e = false;
                return g2;
            } catch (Throwable th5) {
            }
            this.a.unbindService(this.g);
            this.h = null;
            this.g = null;
            this.c = null;
            if (this.e) {
                return g2;
            }
            this.a.setRequestedOrientation(0);
            this.e = false;
            return g2;
            this.h = null;
            this.g = null;
            this.c = null;
            if (this.e) {
                return g2;
            }
            this.a.setRequestedOrientation(0);
            this.e = false;
            return g2;
        } catch (Throwable e2) {
            com.alipay.sdk.app.statistic.a.a("biz", c.z, e2);
            return b;
        }
    }

    private void a() {
        this.a = null;
    }
}
