package com.loc;

import android.location.GpsSatellite;
import android.location.GpsStatus.Listener;
import java.util.Iterator;

final class ee implements Listener {
    final /* synthetic */ g a;

    ee(g gVar) {
        this.a = gVar;
    }

    public final void onGpsStatusChanged(int i) {
        try {
            this.a.m = this.a.b.getGpsStatus(this.a.m);
            switch (i) {
                case 4:
                    Iterator it = this.a.m.getSatellites().iterator();
                    int i2 = 0;
                    int maxSatellites = this.a.m.getMaxSatellites();
                    while (it.hasNext() && i2 < maxSatellites) {
                        i2 = ((GpsSatellite) it.next()).usedInFix() ? i2 + 1 : i2;
                    }
                    this.a.l = i2;
                    return;
                default:
                    return;
            }
        } catch (Throwable th) {
            cw.a(th, "GPSLocation", "onGpsStatusChanged");
        }
    }
}
