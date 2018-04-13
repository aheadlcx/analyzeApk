package com.xiaomi.metoknlp.devicediscover;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.HandlerThread;
import android.os.Message;
import com.xiaomi.metoknlp.a;
import com.xiaomi.metoknlp.b;

public class g {
    private static final long a = (b.b() ? 30000 : 1800000);
    private static final Object e = new Object();
    private Context b;
    private ConnectivityManager c;
    private p d;
    private c f;
    private HandlerThread g;
    private o h;
    private BroadcastReceiver i = new l(this);

    static {
        b.a();
    }

    public g(Context context) {
        this.b = context;
    }

    private void a(boolean z) {
        NetworkInfo networkInfo = null;
        try {
            if (!(this.b == null || this.b.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", this.b.getPackageName()) != 0 || this.c == null)) {
                networkInfo = this.c.getActiveNetworkInfo();
            }
        } catch (Exception e) {
        }
        if (this.f != null) {
            if (networkInfo != null && networkInfo.getType() == 1 && networkInfo.isConnected()) {
                String a = j.a(this.b, 1);
                if (this.f.b() == null || !this.f.b().equals(a)) {
                    this.f.a(a);
                }
                if (this.h.hasMessages(2)) {
                    this.h.removeMessages(2);
                }
                Message obtainMessage = this.h.obtainMessage(2);
                long j = a;
                obtainMessage.obj = Boolean.valueOf(z);
                if (z) {
                    this.h.sendMessage(obtainMessage);
                    return;
                } else {
                    this.h.sendMessageDelayed(obtainMessage, j);
                    return;
                }
            }
            this.f.h();
        }
    }

    private void b(boolean z) {
        if (!b.a().f()) {
            return;
        }
        if (z || (e() && g() && f())) {
            h();
            this.f.g();
            this.f.i();
        }
    }

    private boolean e() {
        long currentTimeMillis = System.currentTimeMillis();
        long c = this.f.c();
        long j = b.a().j();
        if (j == Long.MAX_VALUE) {
            j = a;
        }
        String b = this.f.b();
        return b != null && b.equals(j.a(this.b, 1)) && currentTimeMillis - c >= j;
    }

    private boolean f() {
        if (!b.a().h()) {
            return true;
        }
        long i = b.a().i();
        if (i == Long.MAX_VALUE) {
            i = 172800000;
        }
        this.f.f();
        return this.f.d() > i;
    }

    private boolean g() {
        long e = this.f.e();
        long g = b.a().g();
        if (g == Long.MAX_VALUE) {
            g = 172800000;
        }
        return System.currentTimeMillis() - e > g;
    }

    private void h() {
        this.d.a(this.f.b(), this.f.c(), this.f.d());
    }

    private int i() {
        try {
            return ((a) this.b).b();
        } catch (Exception e) {
            return 0;
        }
    }

    private void j() {
        this.b.registerReceiver(this.i, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    private void k() {
        if (this.h.hasMessages(1)) {
            this.h.removeMessages(1);
        }
        if (this.h.hasMessages(2)) {
            this.h.removeMessages(2);
        }
        this.b.unregisterReceiver(this.i);
    }

    public void a() {
        a(true);
    }

    public void a(p pVar) {
        synchronized (e) {
            this.d = pVar;
        }
    }

    public void b() {
        this.f = new c(this.b);
        this.c = (ConnectivityManager) this.b.getSystemService("connectivity");
        this.g = new HandlerThread("WifiCampStatics");
        this.g.start();
        this.h = new o(this, this.g.getLooper());
        if (i() == 0) {
            j();
        }
    }

    public void c() {
        if (i() == 0) {
            k();
        }
        this.c = null;
        this.f.a();
        if (this.g != null) {
            this.g.quitSafely();
            this.g = null;
        }
    }

    public void d() {
        synchronized (e) {
            this.d = null;
        }
    }
}
