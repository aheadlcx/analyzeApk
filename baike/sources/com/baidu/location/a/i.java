package com.baidu.location.a;

import android.location.Location;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import com.baidu.location.Address;
import com.baidu.location.BDLocation;
import com.baidu.location.Poi;
import com.baidu.location.b.d;
import com.baidu.location.b.g;
import com.baidu.location.b.h;
import com.baidu.location.d.j;
import com.baidu.location.f;
import com.baidu.mobstat.Config;
import java.util.List;

public class i extends g {
    public static boolean h = false;
    private static i i = null;
    private double A;
    private boolean B;
    private long C;
    private long D;
    private a E;
    private boolean F;
    private boolean G;
    private boolean H;
    private boolean I;
    private b J;
    private boolean K;
    final int e;
    public b f;
    public final Handler g;
    private boolean j;
    private String k;
    private BDLocation l;
    private BDLocation m;
    private g n;
    private com.baidu.location.b.a o;
    private g p;
    private com.baidu.location.b.a q;
    private boolean r;
    private volatile boolean s;
    private boolean t;
    private long u;
    private long v;
    private Address w;
    private String x;
    private List<Poi> y;
    private double z;

    private class a implements Runnable {
        final /* synthetic */ i a;

        public void run() {
            if (this.a.F) {
                this.a.F = false;
                if (!this.a.G) {
                }
            }
        }
    }

    private class b implements Runnable {
        final /* synthetic */ i a;

        private b(i iVar) {
            this.a = iVar;
        }

        public void run() {
            if (this.a.K) {
                this.a.K = false;
            }
            if (this.a.t) {
                this.a.t = false;
                this.a.g(null);
            }
        }
    }

    private i() {
        this.e = 1000;
        this.j = true;
        this.f = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = true;
        this.s = false;
        this.t = false;
        this.u = 0;
        this.v = 0;
        this.w = null;
        this.x = null;
        this.y = null;
        this.B = false;
        this.C = 0;
        this.D = 0;
        this.E = null;
        this.F = false;
        this.G = false;
        this.H = true;
        this.g = new com.baidu.location.a.g.a(this);
        this.I = false;
        this.J = null;
        this.K = false;
        this.f = new b(this);
    }

    private boolean a(com.baidu.location.b.a aVar) {
        this.b = com.baidu.location.b.b.a().f();
        return this.b == aVar ? false : this.b == null || aVar == null || !aVar.a(this.b);
    }

    private boolean a(g gVar) {
        this.a = h.a().m();
        return gVar == this.a ? false : this.a == null || gVar == null || !gVar.c(this.a);
    }

    public static synchronized i c() {
        i iVar;
        synchronized (i.class) {
            if (i == null) {
                i = new i();
            }
            iVar = i;
        }
        return iVar;
    }

    private void c(Message message) {
        int d;
        boolean z = message.getData().getBoolean("isWaitingLocTag", false);
        if (z) {
            h = true;
        }
        if (z) {
            d = a.a().d(message);
            j.a().d();
        } else {
            d = a.a().d(message);
            j.a().d();
        }
        switch (d) {
            case 1:
                d(message);
                return;
            case 2:
                f(message);
                return;
            case 3:
                if (d.a().i()) {
                    e(message);
                    return;
                }
                return;
            default:
                throw new IllegalArgumentException(String.format("this type %d is illegal", new Object[]{Integer.valueOf(d)}));
        }
    }

    private void d(Message message) {
        if (d.a().i()) {
            e(message);
            j.a().c();
            return;
        }
        f(message);
        j.a().b();
    }

    private void e(Message message) {
        BDLocation bDLocation = new BDLocation(d.a().f());
        if (j.f.equals("all") || j.g || j.h) {
            float[] fArr = new float[2];
            Location.distanceBetween(this.A, this.z, bDLocation.getLatitude(), bDLocation.getLongitude(), fArr);
            if (fArr[0] < 100.0f) {
                if (this.w != null) {
                    bDLocation.setAddr(this.w);
                }
                if (this.x != null) {
                    bDLocation.setLocationDescribe(this.x);
                }
                if (this.y != null) {
                    bDLocation.setPoiList(this.y);
                }
            } else {
                this.B = true;
                f(null);
            }
        }
        this.l = bDLocation;
        this.m = null;
        a.a().a(bDLocation);
    }

    private void f(Message message) {
        if (this.r) {
            this.D = SystemClock.uptimeMillis();
            g(message);
        } else if (!this.s) {
            this.D = SystemClock.uptimeMillis();
            if (h.a().e()) {
                this.t = true;
                if (this.J == null) {
                    this.J = new b();
                }
                if (this.K && this.J != null) {
                    this.g.removeCallbacks(this.J);
                }
                this.g.postDelayed(this.J, 3500);
                this.K = true;
                return;
            }
            g(message);
        }
    }

    private void g(Message message) {
        if (!this.s) {
            if (System.currentTimeMillis() - this.u <= 0 || System.currentTimeMillis() - this.u >= 1000 || this.l == null) {
                this.s = true;
                this.j = a(this.o);
                if (a(this.n) || this.j || this.l == null || this.B) {
                    this.u = System.currentTimeMillis();
                    String a = a(null);
                    if (a == null) {
                        j();
                        long currentTimeMillis = System.currentTimeMillis();
                        if (currentTimeMillis - this.C > 60000) {
                            this.C = currentTimeMillis;
                        }
                        a = h.a().j();
                        a = a != null ? a + b() : "" + b();
                        String a2 = com.baidu.location.d.b.a().a(true);
                        if (a2 != null) {
                            a = a + a2;
                        }
                    }
                    if (this.k != null) {
                        a = a + this.k;
                        this.k = null;
                    }
                    this.f.a(a);
                    this.o = this.b;
                    this.n = this.a;
                    if (this.r) {
                        this.r = false;
                        if (h.h() && message != null && a.a().e(message) < 1000) {
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (this.m != null && System.currentTimeMillis() - this.v > com.sina.weibo.sdk.statistic.i.MIN_UPLOAD_INTERVAL) {
                    this.l = this.m;
                    this.m = null;
                }
                if (j.a().f()) {
                    this.l.setDirection(j.a().h());
                }
                a.a().a(this.l);
                k();
                return;
            }
            a.a().a(this.l);
            k();
        }
    }

    private String[] j() {
        String[] strArr = new String[]{"", "Location failed beacuse we can not get any loc information!"};
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("&apl=");
        int a = j.a(f.getServiceContext());
        if (a == 1) {
            strArr[1] = "Location failed beacuse we can not get any loc information in airplane mode, you can turn it off and try again!!";
        }
        stringBuffer.append(a);
        String c = j.c(f.getServiceContext());
        if (c.contains("0|0|")) {
            strArr[1] = "Location failed beacuse we can not get any loc information without any location permission!";
        }
        stringBuffer.append(c);
        if (VERSION.SDK_INT >= 23) {
            stringBuffer.append("&loc=");
            if (j.b(f.getServiceContext()) == 0) {
                strArr[1] = "Location failed beacuse we can not get any loc information with the phone loc mode is off, you can turn it on and try again!";
            }
            stringBuffer.append(j.b(f.getServiceContext()));
        }
        stringBuffer.append(h.a().f());
        stringBuffer.append(com.baidu.location.b.b.a().g());
        stringBuffer.append(j.d(f.getServiceContext()));
        strArr[0] = stringBuffer.toString();
        return strArr;
    }

    private void k() {
        this.s = false;
        this.G = false;
        this.H = false;
        this.B = false;
        l();
    }

    private void l() {
        if (this.l != null) {
            s.a().c();
        }
    }

    public Address a(BDLocation bDLocation) {
        if (j.f.equals("all") || j.g || j.h) {
            float[] fArr = new float[2];
            Location.distanceBetween(this.A, this.z, bDLocation.getLatitude(), bDLocation.getLongitude(), fArr);
            if (fArr[0] >= 100.0f) {
                this.x = null;
                this.y = null;
                this.B = true;
                f(null);
            } else if (this.w != null) {
                return this.w;
            }
        }
        return null;
    }

    public void a() {
        if (this.E != null && this.F) {
            this.F = false;
            this.g.removeCallbacks(this.E);
        }
        if (d.a().i()) {
            BDLocation bDLocation = new BDLocation(d.a().f());
            if (j.f.equals("all") || j.g || j.h) {
                float[] fArr = new float[2];
                Location.distanceBetween(this.A, this.z, bDLocation.getLatitude(), bDLocation.getLongitude(), fArr);
                if (fArr[0] < 100.0f) {
                    if (this.w != null) {
                        bDLocation.setAddr(this.w);
                    }
                    if (this.x != null) {
                        bDLocation.setLocationDescribe(this.x);
                    }
                    if (this.y != null) {
                        bDLocation.setPoiList(this.y);
                    }
                }
            }
            a.a().a(bDLocation);
            k();
        } else if (this.G) {
            k();
        } else {
            if (this.j || this.l == null) {
                BDLocation bDLocation2 = new BDLocation();
                bDLocation2.setLocType(63);
                this.l = null;
                a.a().a(bDLocation2);
            } else {
                a.a().a(this.l);
            }
            this.m = null;
            k();
        }
    }

    public void a(Message message) {
        if (this.E != null && this.F) {
            this.F = false;
            this.g.removeCallbacks(this.E);
        }
        BDLocation bDLocation = (BDLocation) message.obj;
        BDLocation bDLocation2 = new BDLocation(bDLocation);
        if (bDLocation.hasAddr()) {
            this.w = bDLocation.getAddress();
            this.z = bDLocation.getLongitude();
            this.A = bDLocation.getLatitude();
        }
        if (bDLocation.getLocationDescribe() != null) {
            this.x = bDLocation.getLocationDescribe();
            this.z = bDLocation.getLongitude();
            this.A = bDLocation.getLatitude();
        }
        if (bDLocation.getPoiList() != null) {
            this.y = bDLocation.getPoiList();
            this.z = bDLocation.getLongitude();
            this.A = bDLocation.getLatitude();
        }
        float[] fArr;
        if (d.a().i()) {
            bDLocation = new BDLocation(d.a().f());
            if (j.f.equals("all") || j.g || j.h) {
                fArr = new float[2];
                Location.distanceBetween(this.A, this.z, bDLocation.getLatitude(), bDLocation.getLongitude(), fArr);
                if (fArr[0] < 100.0f) {
                    if (this.w != null) {
                        bDLocation.setAddr(this.w);
                    }
                    if (this.x != null) {
                        bDLocation.setLocationDescribe(this.x);
                    }
                    if (this.y != null) {
                        bDLocation.setPoiList(this.y);
                    }
                }
            }
            a.a().a(bDLocation);
            k();
        } else if (this.G) {
            fArr = new float[2];
            if (this.l != null) {
                Location.distanceBetween(this.l.getLatitude(), this.l.getLongitude(), bDLocation.getLatitude(), bDLocation.getLongitude(), fArr);
            }
            if (fArr[0] > 10.0f) {
                this.l = bDLocation;
                if (!this.H) {
                    this.H = false;
                    a.a().a(bDLocation);
                }
            } else if (bDLocation.getUserIndoorState() > -1) {
                this.l = bDLocation;
                a.a().a(bDLocation);
            }
            k();
        } else {
            boolean z;
            this.m = null;
            if (bDLocation.getLocType() == 161 && Config.CELL_LOCATION.equals(bDLocation.getNetworkLocationType()) && this.l != null && this.l.getLocType() == 161 && "wf".equals(this.l.getNetworkLocationType()) && System.currentTimeMillis() - this.v < com.sina.weibo.sdk.statistic.i.MIN_UPLOAD_INTERVAL) {
                z = true;
                this.m = bDLocation;
            } else {
                z = false;
            }
            if (z) {
                a.a().a(this.l);
            } else {
                a.a().a(bDLocation);
                this.v = System.currentTimeMillis();
            }
            if (!j.a(bDLocation)) {
                this.l = null;
            } else if (!z) {
                this.l = bDLocation;
            }
            int a = j.a(c, "ssid\":\"", "\"");
            if (a == Integer.MIN_VALUE || this.n == null) {
                this.k = null;
            } else {
                this.k = this.n.b(a);
            }
            if (h.h()) {
                k();
            } else {
                k();
            }
        }
    }

    public void b(Message message) {
        if (this.I) {
            c(message);
        }
    }

    public void b(BDLocation bDLocation) {
        this.l = new BDLocation(bDLocation);
    }

    public void d() {
        this.r = true;
        this.s = false;
        this.I = true;
    }

    public void e() {
        this.s = false;
        this.t = false;
        this.G = false;
        this.H = true;
        i();
        this.I = false;
    }

    public String f() {
        return this.x;
    }

    public List<Poi> g() {
        return this.y;
    }

    public void h() {
        if (this.t) {
            g(null);
            this.t = false;
        }
    }

    public void i() {
        this.l = null;
    }
}
