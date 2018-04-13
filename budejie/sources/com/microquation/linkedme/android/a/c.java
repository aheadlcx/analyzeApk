package com.microquation.linkedme.android.a;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.text.TextUtils;
import com.microquation.linkedme.android.f.b;
import java.util.ArrayList;

public class c implements LocationListener {
    private d a;
    private ArrayList<Location> b = new ArrayList();
    private Location c;

    public c(d dVar) {
        this.a = dVar;
    }

    public Location a() {
        return this.c;
    }

    public ArrayList<Location> b() {
        ArrayList<Location> arrayList = new ArrayList();
        arrayList.addAll(this.b);
        this.b.clear();
        return arrayList;
    }

    public void onLocationChanged(Location location) {
        if (e.a(location)) {
            b.a("更新地LC数据onChanged()，provider = " + location.getProvider());
            b.a("onChanged = " + location.getLongitude() + "," + location.getLatitude());
            this.b.add(location);
            if (this.c == null) {
                this.c = location;
            } else {
                this.c.set(location);
            }
            if (!this.a.h()) {
                this.a.a(this.c);
                b.a("onChanged()，通知LC信息变更！");
            }
        }
    }

    public void onProviderDisabled(String str) {
        if (TextUtils.equals(str, "gps")) {
            this.a.e();
            this.a.a(false);
            this.a.f();
        }
        if (TextUtils.equals(str, "network")) {
            this.a.d();
        }
    }

    public void onProviderEnabled(String str) {
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }
}
