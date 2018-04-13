package com.loc;

import android.content.Context;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.DPoint;

public final class g {
    Handler a;
    LocationManager b;
    AMapLocationClientOption c;
    long d = 0;
    boolean e = false;
    by f = null;
    db g = null;
    int h = 240;
    int i = 80;
    long j = 0;
    LocationListener k = new ed(this);
    int l = 0;
    GpsStatus m = null;
    public AMapLocation n = null;
    private Context o;
    private long p = 0;
    private int q = 0;
    private Listener r = new ee(this);

    public g(Context context, Handler handler) {
        this.o = context;
        this.a = handler;
        this.b = (LocationManager) this.o.getSystemService("location");
        this.f = new by();
        this.g = new db();
    }

    private void a(int i, int i2, String str, long j) {
        if (this.a != null && this.c.getLocationMode() == AMapLocationMode.Device_Sensors) {
            Message obtain = Message.obtain();
            AMapLocation aMapLocation = new AMapLocation("");
            aMapLocation.setProvider("gps");
            aMapLocation.setErrorCode(i2);
            aMapLocation.setLocationDetail(str);
            aMapLocation.setLocationType(1);
            obtain.obj = aMapLocation;
            obtain.what = i;
            this.a.sendMessageDelayed(obtain, j);
        }
    }

    static /* synthetic */ void a(g gVar, AMapLocation aMapLocation) {
        try {
            if (cw.a(aMapLocation.getLatitude(), aMapLocation.getLongitude()) && gVar.c.isOffset()) {
                DPoint a = cx.a(gVar.o, new DPoint(aMapLocation.getLatitude(), aMapLocation.getLongitude()));
                aMapLocation.setLatitude(a.getLatitude());
                aMapLocation.setLongitude(a.getLongitude());
            }
        } catch (Throwable th) {
        }
    }

    static /* synthetic */ AMapLocation b(g gVar, AMapLocation aMapLocation) {
        if (!de.a(aMapLocation) || gVar.q < 3) {
            return aMapLocation;
        }
        if (aMapLocation.getAccuracy() < 0.0f || aMapLocation.getAccuracy() == Float.MAX_VALUE) {
            aMapLocation.setAccuracy(0.0f);
        }
        if (aMapLocation.getSpeed() < 0.0f || aMapLocation.getSpeed() == Float.MAX_VALUE) {
            aMapLocation.setSpeed(0.0f);
        }
        AMapLocation a = gVar.f.a(aMapLocation);
        gVar.g.a(aMapLocation, a);
        return a;
    }

    static /* synthetic */ void c(g gVar, AMapLocation aMapLocation) {
        if (de.a(aMapLocation)) {
            gVar.d = de.b();
            gVar.q++;
        }
    }

    static /* synthetic */ void d(g gVar, AMapLocation aMapLocation) {
        try {
            if (aMapLocation.getErrorCode() == 0 && !cw.k && !dd.b(gVar.o, "pref", "colde", false)) {
                cw.k = true;
                dd.a(gVar.o, "pref", "colde", true);
            }
        } catch (Throwable th) {
        }
    }

    public final void a() {
        if (this.b != null) {
            if (this.k != null) {
                this.b.removeUpdates(this.k);
            }
            if (this.r != null) {
                this.b.removeGpsStatusListener(this.r);
            }
            if (this.a != null) {
                this.a.removeMessages(8);
            }
            this.l = 0;
            this.p = 0;
            this.j = 0;
            this.d = 0;
            this.q = 0;
            this.f.a();
            this.g.a();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final void b() {
        /*
        r10 = this;
        r8 = 0;
        r6 = android.os.Looper.myLooper();	 Catch:{ SecurityException -> 0x0067, Throwable -> 0x0075 }
        if (r6 != 0) goto L_0x000e;
    L_0x0008:
        r0 = r10.o;	 Catch:{ SecurityException -> 0x0067, Throwable -> 0x0075 }
        r6 = r0.getMainLooper();	 Catch:{ SecurityException -> 0x0067, Throwable -> 0x0075 }
    L_0x000e:
        r0 = com.loc.de.b();	 Catch:{ SecurityException -> 0x0067, Throwable -> 0x0075 }
        r10.p = r0;	 Catch:{ SecurityException -> 0x0067, Throwable -> 0x0075 }
        r0 = r10.b;	 Catch:{ Throwable -> 0x007e, SecurityException -> 0x0067 }
        r1 = "gps";
        r2 = "force_xtra_injection";
        r3 = 0;
        r0.sendExtraCommand(r1, r2, r3);	 Catch:{ Throwable -> 0x007e, SecurityException -> 0x0067 }
    L_0x001e:
        r0 = r10.b;	 Catch:{ SecurityException -> 0x0067, Throwable -> 0x0075 }
        r0 = r0.getAllProviders();	 Catch:{ SecurityException -> 0x0067, Throwable -> 0x0075 }
        if (r0 == 0) goto L_0x002c;
    L_0x0026:
        r1 = r0.size();	 Catch:{ SecurityException -> 0x0067, Throwable -> 0x0075 }
        if (r1 != 0) goto L_0x0053;
    L_0x002c:
        r0 = 0;
    L_0x002d:
        if (r0 == 0) goto L_0x005a;
    L_0x002f:
        r0 = r10.b;	 Catch:{ SecurityException -> 0x0067, Throwable -> 0x0075 }
        r1 = "gps";
        r2 = 900; // 0x384 float:1.261E-42 double:4.447E-321;
        r4 = 0;
        r5 = r10.k;	 Catch:{ SecurityException -> 0x0067, Throwable -> 0x0075 }
        r0.requestLocationUpdates(r1, r2, r4, r5, r6);	 Catch:{ SecurityException -> 0x0067, Throwable -> 0x0075 }
        r0 = r10.b;	 Catch:{ SecurityException -> 0x0067, Throwable -> 0x0075 }
        r1 = r10.r;	 Catch:{ SecurityException -> 0x0067, Throwable -> 0x0075 }
        r0.addGpsStatusListener(r1);	 Catch:{ SecurityException -> 0x0067, Throwable -> 0x0075 }
        r1 = 8;
        r2 = 14;
        r3 = "no enough satellites";
        r0 = r10.c;	 Catch:{ SecurityException -> 0x0067, Throwable -> 0x0075 }
        r4 = r0.getHttpTimeOut();	 Catch:{ SecurityException -> 0x0067, Throwable -> 0x0075 }
        r0 = r10;
        r0.a(r1, r2, r3, r4);	 Catch:{ SecurityException -> 0x0067, Throwable -> 0x0075 }
    L_0x0052:
        return;
    L_0x0053:
        r1 = "gps";
        r0 = r0.contains(r1);	 Catch:{ SecurityException -> 0x0067, Throwable -> 0x0075 }
        goto L_0x002d;
    L_0x005a:
        r1 = 8;
        r2 = 14;
        r3 = "no gps provider";
        r4 = 0;
        r0 = r10;
        r0.a(r1, r2, r3, r4);	 Catch:{ SecurityException -> 0x0067, Throwable -> 0x0075 }
        goto L_0x0052;
    L_0x0067:
        r0 = move-exception;
        r1 = 2;
        r2 = 12;
        r3 = r0.getMessage();
        r0 = r10;
        r4 = r8;
        r0.a(r1, r2, r3, r4);
        goto L_0x0052;
    L_0x0075:
        r0 = move-exception;
        r1 = "GPSLocation";
        r2 = "requestLocationUpdates part2";
        com.loc.cw.a(r0, r1, r2);
        goto L_0x0052;
    L_0x007e:
        r0 = move-exception;
        goto L_0x001e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.g.b():void");
    }

    public final boolean c() {
        return de.b() - this.d <= 10000;
    }
}
