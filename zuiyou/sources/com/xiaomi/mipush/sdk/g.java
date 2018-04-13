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
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.misc.c;
import com.xiaomi.channel.commonutils.misc.f;
import com.xiaomi.channel.commonutils.misc.h.a;
import com.xiaomi.channel.commonutils.network.d;
import com.xiaomi.push.service.am;
import com.xiaomi.push.service.x;
import com.xiaomi.xmpush.thrift.ai;
import com.xiaomi.xmpush.thrift.au;
import com.xiaomi.xmpush.thrift.l;
import com.xiaomi.xmpush.thrift.o;
import com.xiaomi.xmpush.thrift.p;
import com.xiaomi.xmpush.thrift.r;
import com.xiaomi.xmpush.thrift.y;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class g extends a {
    private final int a = -1;
    private final int b = 3600;
    private Context c;
    private int d;

    public g(Context context, int i) {
        this.c = context;
        this.d = i;
    }

    private static Location a(Location location, Location location2) {
        return location == null ? location2 : (location2 == null || location.getTime() > location2.getTime()) ? location : location2;
    }

    public static void a(Context context, boolean z) {
        p b = b(context);
        byte[] a = au.a(b);
        org.apache.thrift.a aiVar = new ai(WeiboAuthException.DEFAULT_AUTH_ERROR_CODE, false);
        aiVar.c(r.GeoUpdateLoc.W);
        aiVar.a(a);
        aiVar.a(new HashMap());
        aiVar.i().put("initial_wifi_upload", String.valueOf(z));
        boolean b2 = x.b(context);
        if (b2) {
            aiVar.i().put("xmsf_geo_is_work", String.valueOf(b2));
        }
        b.c("reportLocInfo locInfo timestamp:" + System.currentTimeMillis() + "," + String.valueOf(b.c() != null ? b.c() : "null") + "," + String.valueOf(b.b != null ? b.b.toString() : null) + "," + String.valueOf(b.a != null ? b.a.toString() : null));
        ac.a(context).a(aiVar, com.xiaomi.xmpush.thrift.a.Notification, true, null);
        g(context);
    }

    private boolean a(long j) {
        return ((float) Math.abs((System.currentTimeMillis() / 1000) - this.c.getSharedPreferences("mipush_extra", 0).getLong("last_upload_lbs_data_timestamp", -1))) > ((float) j) * 0.9f;
    }

    protected static boolean a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        return (packageManager.checkPermission("android.permission.ACCESS_COARSE_LOCATION", packageName) == 0) || (packageManager.checkPermission("android.permission.ACCESS_FINE_LOCATION", packageName) == 0);
    }

    private static p b(Context context) {
        p pVar = new p();
        pVar.a(c(context));
        pVar.b(d(context));
        pVar.a(e(context));
        return pVar;
    }

    private boolean b() {
        if (d.e(this.c)) {
            return true;
        }
        return d.f(this.c) && a((long) Math.max(60, am.a(this.c).a(com.xiaomi.xmpush.thrift.g.UploadNOWIFIGeoLocFrequency.a(), 3600)));
    }

    private static List<y> c(Context context) {
        Comparator alVar = new al();
        try {
            List scanResults = ((WifiManager) context.getSystemService("wifi")).getScanResults();
            if (c.a(scanResults)) {
                return null;
            }
            Collections.sort(scanResults, alVar);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < Math.min(30, scanResults.size()); i++) {
                ScanResult scanResult = (ScanResult) scanResults.get(i);
                if (scanResult != null) {
                    y yVar = new y();
                    yVar.a(TextUtils.isEmpty(scanResult.BSSID) ? "" : scanResult.BSSID);
                    yVar.a(scanResult.level);
                    yVar.b(scanResult.SSID);
                    arrayList.add(yVar);
                }
            }
            return arrayList;
        } catch (Throwable th) {
            return null;
        }
    }

    private static List<com.xiaomi.xmpush.thrift.c> d(Context context) {
        try {
            List neighboringCellInfo = ((TelephonyManager) context.getSystemService("phone")).getNeighboringCellInfo();
            int i = 0;
            List<com.xiaomi.xmpush.thrift.c> list = null;
            while (i < neighboringCellInfo.size()) {
                NeighboringCellInfo neighboringCellInfo2 = (NeighboringCellInfo) neighboringCellInfo.get(i);
                ArrayList arrayList = new ArrayList();
                if (neighboringCellInfo2.getLac() > 0 || neighboringCellInfo2.getCid() > 0) {
                    com.xiaomi.xmpush.thrift.c cVar = new com.xiaomi.xmpush.thrift.c();
                    cVar.a(neighboringCellInfo2.getCid());
                    cVar.b((neighboringCellInfo2.getRssi() * 2) - 113);
                    arrayList.add(cVar);
                }
                i++;
                Object obj = arrayList;
            }
            return list;
        } catch (Throwable th) {
            return null;
        }
    }

    private static l e(Context context) {
        if (!a(context)) {
            return null;
        }
        Location f = f(context);
        if (f == null) {
            return null;
        }
        o oVar = new o();
        oVar.b(f.getLatitude());
        oVar.a(f.getLongitude());
        l lVar = new l();
        lVar.a((double) f.getAccuracy());
        lVar.a(oVar);
        lVar.a(f.getProvider());
        lVar.a(new Date().getTime() - f.getTime());
        return lVar;
    }

    private static Location f(Context context) {
        Location lastKnownLocation;
        Location lastKnownLocation2;
        Location lastKnownLocation3;
        LocationManager locationManager = (LocationManager) context.getSystemService(RequestParameters.SUBRESOURCE_LOCATION);
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

    private static void g(Context context) {
        Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
        edit.putLong("last_upload_lbs_data_timestamp", System.currentTimeMillis() / 1000);
        edit.commit();
    }

    public int a() {
        return 11;
    }

    public void run() {
        if (x.e(this.c) && am.a(this.c).a(com.xiaomi.xmpush.thrift.g.UploadGeoAppLocSwitch.a(), true) && d.d(this.c) && b() && f.a(this.c, String.valueOf(11), (long) this.d)) {
            a(this.c, false);
        }
    }
}
