package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import java.util.List;
import org.json.JSONObject;

public final class h {
    String a = null;
    cn b = null;
    cn c = null;
    cn d = null;
    af e = null;
    long f = 0;
    boolean g = false;
    private Context h;

    public h(Context context) {
        this.h = context.getApplicationContext();
    }

    public final void a() {
        if (!this.g) {
            try {
                if (this.a == null) {
                    this.a = cj.a("MD5", n.q(this.h));
                }
                if (this.e == null) {
                    this.e = new af(this.h, af.a(co.class), de.i());
                }
            } catch (Throwable th) {
                cw.a(th, "LastLocationManager", "<init>:DBOperation");
            }
            this.g = true;
        }
    }

    public final boolean a(AMapLocation aMapLocation, String str) {
        if (this.h == null || aMapLocation == null || !de.a(aMapLocation) || aMapLocation.getLocationType() == 2 || aMapLocation.isMock() || aMapLocation.isFixLastLocation()) {
            return false;
        }
        cn cnVar = new cn();
        cnVar.a(aMapLocation);
        if (aMapLocation.getLocationType() == 1) {
            cnVar.a(null);
        } else {
            cnVar.a(str);
        }
        try {
            this.b = cnVar;
            this.c = cnVar;
            return (this.d == null || de.a(this.d.a(), cnVar.a()) > 500.0f) && de.b() - this.f > i.MIN_UPLOAD_INTERVAL;
        } catch (Throwable th) {
            cw.a(th, "LastLocationManager", "setLastFix");
            return false;
        }
    }

    public final AMapLocation b(AMapLocation aMapLocation, String str) {
        if (aMapLocation == null || aMapLocation.getErrorCode() == 0 || aMapLocation.getLocationType() == 1 || aMapLocation.getErrorCode() == 7) {
            return aMapLocation;
        }
        if (this.b == null) {
            this.b = d();
        }
        if (this.b == null || this.b.a() == null) {
            return aMapLocation;
        }
        if (TextUtils.isEmpty(str)) {
            long b = de.b() - this.b.d();
            if (b < 0 || b > i.MIN_UPLOAD_INTERVAL) {
                return aMapLocation;
            }
            aMapLocation = this.b.a();
            aMapLocation.setLocationType(4);
            aMapLocation.setFixLastLocation(true);
            return aMapLocation;
        } else if (!de.a(this.b.b(), str)) {
            return aMapLocation;
        } else {
            aMapLocation = this.b.a();
            aMapLocation.setLocationType(4);
            aMapLocation.setFixLastLocation(true);
            return aMapLocation;
        }
    }

    public final void b() {
        try {
            c();
            this.f = 0;
            this.g = false;
            this.b = null;
            this.c = null;
            this.d = null;
        } catch (Throwable th) {
            cw.a(th, "LastLocationManager", "destroy");
        }
    }

    public final void c() {
        String str = null;
        try {
            a();
            if (this.c != null && de.a(this.c.a()) && this.e != null && this.c != this.d && this.c.d() == 0) {
                Object toStr = this.c.a().toStr();
                Object b = this.c.b();
                this.d = this.c;
                if (TextUtils.isEmpty(toStr)) {
                    toStr = null;
                } else {
                    toStr = o.a(cj.c(toStr.getBytes("UTF-8"), this.a));
                    if (!TextUtils.isEmpty(b)) {
                        str = o.a(cj.c(b.getBytes("UTF-8"), this.a));
                    }
                }
                if (!TextUtils.isEmpty(toStr)) {
                    b = new cn();
                    b.b(toStr);
                    b.a(de.b());
                    b.a(str);
                    this.e.a(b, "_id=1");
                    this.f = de.b();
                }
            }
        } catch (Throwable th) {
            cw.a(th, "LastLocationManager", "saveLastFix");
        }
    }

    final cn d() {
        Throwable th;
        Object obj = null;
        if (this.h == null) {
            return null;
        }
        a();
        cn cnVar;
        try {
            if (this.e == null) {
                return null;
            }
            List b = this.e.b("_id=1", cn.class);
            if (b == null || b.size() <= 0) {
                cnVar = null;
            } else {
                cnVar = (cn) b.get(0);
                try {
                    byte[] d;
                    String str;
                    String str2;
                    byte[] b2 = o.b(cnVar.c());
                    if (b2 != null && b2.length > 0) {
                        d = cj.d(b2, this.a);
                        if (d != null && d.length > 0) {
                            str = new String(d, "UTF-8");
                            d = o.b(cnVar.b());
                            if (d != null && d.length > 0) {
                                d = cj.d(d, this.a);
                                if (d != null && d.length > 0) {
                                    str2 = new String(d, "UTF-8");
                                }
                            }
                            cnVar.a(str2);
                            obj = str;
                        }
                    }
                    str = null;
                    d = o.b(cnVar.b());
                    d = cj.d(d, this.a);
                    str2 = new String(d, "UTF-8");
                    cnVar.a(str2);
                    obj = str;
                } catch (Throwable th2) {
                    th = th2;
                    cw.a(th, "LastLocationManager", "readLastFix");
                    return cnVar;
                }
            }
            if (TextUtils.isEmpty(obj)) {
                return cnVar;
            }
            AMapLocation aMapLocation = new AMapLocation("");
            cw.a(aMapLocation, new JSONObject(obj));
            if (!de.b(aMapLocation)) {
                return cnVar;
            }
            cnVar.a(aMapLocation);
            return cnVar;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            cnVar = null;
            th = th4;
            cw.a(th, "LastLocationManager", "readLastFix");
            return cnVar;
        }
    }
}
