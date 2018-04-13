package com.umeng.commonsdk.proguard;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.common.e;
import com.umeng.commonsdk.utils.UMUtils;

public class d {
    private LocationManager a;
    private Context b;
    private f c;

    private d() {
    }

    public d(Context context) {
        if (context == null) {
            MLog.e("Context参数不能为null");
            return;
        }
        this.b = context.getApplicationContext();
        this.a = (LocationManager) context.getApplicationContext().getSystemService("location");
    }

    public synchronized void a(f fVar) {
        Location location = null;
        synchronized (this) {
            e.a("UMSysLocation", "getSystemLocation");
            if (!(fVar == null || this.b == null)) {
                this.c = fVar;
                if (UMUtils.checkPermission(this.b, "android.permission.ACCESS_COARSE_LOCATION") || UMUtils.checkPermission(this.b, "android.permission.ACCESS_FINE_LOCATION")) {
                    try {
                        if (this.a != null) {
                            boolean isProviderEnabled = this.a.isProviderEnabled("gps");
                            boolean isProviderEnabled2 = this.a.isProviderEnabled("network");
                            if (isProviderEnabled || isProviderEnabled2) {
                                e.a("UMSysLocation", "getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)");
                                if (UMUtils.checkPermission(this.b, "android.permission.ACCESS_FINE_LOCATION")) {
                                    location = this.a.getLastKnownLocation("passive");
                                } else if (UMUtils.checkPermission(this.b, "android.permission.ACCESS_COARSE_LOCATION")) {
                                    location = this.a.getLastKnownLocation("network");
                                }
                            }
                            this.c.a(location);
                        }
                    } catch (Throwable th) {
                        b.a(this.b, th);
                    }
                } else if (this.c != null) {
                    this.c.a(null);
                }
            }
        }
        b.a(this.b, th);
    }

    public synchronized void a() {
        e.a("UMSysLocation", "destroy");
        try {
            if (this.a != null) {
                this.a = null;
            }
        } catch (Throwable th) {
            b.a(this.b, th);
        }
    }
}
