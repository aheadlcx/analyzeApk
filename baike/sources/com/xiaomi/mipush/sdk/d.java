package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.misc.b;
import com.xiaomi.channel.commonutils.misc.f.a;
import com.xiaomi.push.service.ah;
import com.xiaomi.xmpush.thrift.ae;
import com.xiaomi.xmpush.thrift.aq;
import com.xiaomi.xmpush.thrift.e;
import com.xiaomi.xmpush.thrift.i;
import com.xiaomi.xmpush.thrift.l;
import com.xiaomi.xmpush.thrift.m;
import com.xiaomi.xmpush.thrift.o;
import com.xiaomi.xmpush.thrift.v;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import qsbk.app.core.utils.ACache;

public class d extends a {
    private final int a = 30;
    private final int b = -1;
    private final int c = ACache.TIME_HOUR;
    private final String d = MiPushClient.PREF_EXTRA;
    private final String e = "GeoFenceNetInfoUpdateJob";
    private final String f = "last_upload_lbs_data_timestamp";
    private Context g;
    private Comparator<ScanResult> h;

    public d(Context context) {
        this.g = context;
        this.h = new e(this);
    }

    private Location a(Location location, Location location2) {
        return location == null ? location2 : (location2 == null || location.getTime() > location2.getTime()) ? location : location2;
    }

    private boolean a(long j) {
        return Math.abs((System.currentTimeMillis() / 1000) - this.g.getSharedPreferences(MiPushClient.PREF_EXTRA, 0).getLong("last_upload_lbs_data_timestamp", -1)) / 1000 > j;
    }

    private boolean c() {
        if (com.xiaomi.channel.commonutils.network.d.f(this.g)) {
            return true;
        }
        int a = ah.a(this.g).a(e.UploadNOWIFIGeoLocFrequency.a(), (int) ACache.TIME_HOUR);
        return (com.xiaomi.channel.commonutils.network.d.g(this.g) && a((long) a)) ? true : (com.xiaomi.channel.commonutils.network.d.h(this.g) && a((long) a)) ? true : com.xiaomi.channel.commonutils.network.d.i(this.g) && a((long) a);
    }

    private m d() {
        m mVar = new m();
        mVar.a(e());
        mVar.b(f());
        mVar.a(g());
        return mVar;
    }

    private List<v> e() {
        try {
            List scanResults = ((WifiManager) this.g.getSystemService("wifi")).getScanResults();
            if (b.a(scanResults)) {
                return null;
            }
            Collections.sort(scanResults, this.h);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < Math.min(30, scanResults.size()); i++) {
                ScanResult scanResult = (ScanResult) scanResults.get(i);
                if (scanResult != null) {
                    v vVar = new v();
                    vVar.a(TextUtils.isEmpty(scanResult.BSSID) ? "" : scanResult.BSSID);
                    vVar.a(scanResult.level);
                    vVar.b(scanResult.SSID);
                    arrayList.add(vVar);
                }
            }
            return arrayList;
        } catch (Throwable th) {
            return null;
        }
    }

    private List<com.xiaomi.xmpush.thrift.b> f() {
        try {
            List neighboringCellInfo = ((TelephonyManager) this.g.getSystemService("phone")).getNeighboringCellInfo();
            int i = 0;
            List<com.xiaomi.xmpush.thrift.b> list = null;
            while (i < neighboringCellInfo.size()) {
                NeighboringCellInfo neighboringCellInfo2 = (NeighboringCellInfo) neighboringCellInfo.get(i);
                ArrayList arrayList = new ArrayList();
                if (neighboringCellInfo2.getLac() > 0 || neighboringCellInfo2.getCid() > 0) {
                    com.xiaomi.xmpush.thrift.b bVar = new com.xiaomi.xmpush.thrift.b();
                    bVar.a(neighboringCellInfo2.getCid());
                    bVar.b((neighboringCellInfo2.getRssi() * 2) - 113);
                    arrayList.add(bVar);
                }
                i++;
                Object obj = arrayList;
            }
            return list;
        } catch (Throwable th) {
            return null;
        }
    }

    private i g() {
        if (!b()) {
            return null;
        }
        Location h = h();
        if (h == null) {
            return null;
        }
        l lVar = new l();
        lVar.b(h.getLatitude());
        lVar.a(h.getLongitude());
        i iVar = new i();
        iVar.a((double) h.getAccuracy());
        iVar.a(lVar);
        iVar.a(h.getProvider());
        iVar.a(new Date().getTime() - h.getTime());
        return iVar;
    }

    private Location h() {
        Location lastKnownLocation;
        Location lastKnownLocation2;
        Location lastKnownLocation3;
        LocationManager locationManager = (LocationManager) this.g.getSystemService("location");
        try {
            lastKnownLocation = locationManager.getLastKnownLocation("network");
        } catch (Exception e) {
            lastKnownLocation = null;
        }
        try {
            lastKnownLocation2 = locationManager.getLastKnownLocation("gps");
        } catch (Exception e2) {
            lastKnownLocation2 = null;
        }
        try {
            lastKnownLocation3 = locationManager.getLastKnownLocation("passive");
        } catch (Exception e3) {
            lastKnownLocation3 = null;
        }
        return a(lastKnownLocation3, a(lastKnownLocation, lastKnownLocation2));
    }

    private void i() {
        Editor edit = this.g.getSharedPreferences(MiPushClient.PREF_EXTRA, 0).edit();
        edit.putLong("last_upload_lbs_data_timestamp", System.currentTimeMillis() / 1000);
        edit.commit();
    }

    public int a() {
        return 14;
    }

    protected boolean b() {
        PackageManager packageManager = this.g.getPackageManager();
        String packageName = this.g.getPackageName();
        return (packageManager.checkPermission("android.permission.ACCESS_COARSE_LOCATION", packageName) == 0) || (packageManager.checkPermission("android.permission.ACCESS_FINE_LOCATION", packageName) == 0);
    }

    public void run() {
        if (!com.xiaomi.channel.commonutils.network.d.e(this.g)) {
            com.xiaomi.channel.commonutils.logger.b.d("GeoFenceNetInfoUpdateJobNetwork.is not Connected");
        } else if (c()) {
            byte[] a = aq.a(d());
            org.apache.thrift.a aeVar = new ae("-1", false);
            aeVar.c(o.GeoUpdateLoc.N);
            aeVar.a(a);
            u.a(this.g).a(aeVar, com.xiaomi.xmpush.thrift.a.Notification, true, null);
            i();
            com.xiaomi.channel.commonutils.logger.b.a("GeoFenceNetInfoUpdateJob: update_loc_data");
        } else {
            com.xiaomi.channel.commonutils.logger.b.d("GeoFenceNetInfoUpdateJobverifyUploadData");
        }
    }
}
