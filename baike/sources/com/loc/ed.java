package com.loc;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Message;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;

final class ed implements LocationListener {
    final /* synthetic */ g a;

    ed(g gVar) {
        this.a = gVar;
    }

    public final void onLocationChanged(Location location) {
        int i = 0;
        if (this.a.a != null) {
            this.a.a.removeMessages(8);
        }
        if (location != null) {
            try {
                AMapLocation aMapLocation = new AMapLocation(location);
                aMapLocation.setLocationType(1);
                Bundle extras = location.getExtras();
                if (extras != null) {
                    i = extras.getInt("satellites");
                }
                if (!this.a.e) {
                    db.a(this.a.o, de.b() - this.a.p);
                    this.a.e = true;
                }
                if (de.a(location, this.a.l)) {
                    aMapLocation.setMock(true);
                    if (!this.a.c.isMockEnable()) {
                        db.a(null, 2152);
                        aMapLocation.setErrorCode(15);
                        aMapLocation.setLocationDetail("GPSLocation has been mocked!");
                        aMapLocation.setLatitude(0.0d);
                        aMapLocation.setLongitude(0.0d);
                    }
                }
                aMapLocation.setSatellites(i);
                g.a(this.a, aMapLocation);
                g gVar = this.a;
                try {
                    if (gVar.l >= 4) {
                        aMapLocation.setGpsAccuracyStatus(1);
                    } else if (gVar.l == 0) {
                        aMapLocation.setGpsAccuracyStatus(-1);
                    } else {
                        aMapLocation.setGpsAccuracyStatus(0);
                    }
                } catch (Throwable th) {
                }
                AMapLocation b = g.b(this.a, aMapLocation);
                g.c(this.a, b);
                g gVar2 = this.a;
                if (de.a(b) && gVar2.a != null && gVar2.c.isNeedAddress()) {
                    long b2 = de.b();
                    if (gVar2.c.getInterval() <= 8000 || b2 - gVar2.j > gVar2.c.getInterval() - 8000) {
                        extras = new Bundle();
                        extras.putDouble("lat", b.getLatitude());
                        extras.putDouble("lon", b.getLongitude());
                        Message obtain = Message.obtain();
                        obtain.setData(extras);
                        obtain.what = 5;
                        if (gVar2.n == null) {
                            gVar2.a.sendMessage(obtain);
                        } else if (de.a(b, gVar2.n) > ((float) gVar2.i)) {
                            gVar2.a.sendMessage(obtain);
                        }
                    }
                }
                gVar2 = this.a;
                AMapLocation aMapLocation2 = this.a.n;
                if (aMapLocation2 != null && gVar2.c.isNeedAddress() && de.a(b, aMapLocation2) < ((float) gVar2.h)) {
                    cw.a(b, aMapLocation2);
                }
                gVar2 = this.a;
                if ((b.getErrorCode() != 15 || AMapLocationMode.Device_Sensors.equals(gVar2.c.getLocationMode())) && de.b() - gVar2.j >= gVar2.c.getInterval() - 200) {
                    gVar2.j = de.b();
                    if (gVar2.a != null) {
                        Message obtain2 = Message.obtain();
                        obtain2.obj = b;
                        obtain2.what = 2;
                        gVar2.a.sendMessage(obtain2);
                    }
                }
                g.d(this.a, b);
            } catch (Throwable th2) {
                cw.a(th2, "GPSLocation", "onLocationChanged");
            }
        }
    }

    public final void onProviderDisabled(String str) {
        try {
            if ("gps".equals(str)) {
                this.a.d = 0;
            }
        } catch (Throwable th) {
            cw.a(th, "GPSLocation", "onProviderDisabled");
        }
    }

    public final void onProviderEnabled(String str) {
    }

    public final void onStatusChanged(String str, int i, Bundle bundle) {
        if (i == 0) {
            try {
                this.a.d = 0;
            } catch (Throwable th) {
                cw.a(th, "GPSLocation", "onStatusChanged");
            }
        }
    }
}
