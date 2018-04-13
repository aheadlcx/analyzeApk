package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.iflytek.cloud.Setting;

public class br {
    public static br a = null;
    private SharedPreferences b = null;
    private Context c = null;
    private boolean d = true;
    private long e = 0;

    private br(Context context) {
        this.c = context;
        this.b = context.getSharedPreferences("com.iflytek.msc", 0);
        this.d = b(context);
    }

    public static br a(Context context) {
        if (a == null && context != null) {
            c(context);
        }
        return a;
    }

    public static boolean b(Context context) {
        try {
            if (!Setting.getLocationEnable() || context == null) {
                return false;
            }
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            int i = 0;
            while (i < strArr.length) {
                if ("android.permission.ACCESS_FINE_LOCATION".equalsIgnoreCase(strArr[i]) || "android.permission.ACCESS_COARSE_LOCATION".equalsIgnoreCase(strArr[i])) {
                    return true;
                }
                i++;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private static synchronized br c(Context context) {
        br brVar;
        synchronized (br.class) {
            if (a == null) {
                a = new br(context);
            }
            bv.a(context);
            bp.a(context);
            brVar = a;
        }
        return brVar;
    }

    public synchronized float a(String str) {
        try {
            if (this.d && System.currentTimeMillis() - this.e > 216000) {
                LocationManager locationManager = (LocationManager) this.c.getApplicationContext().getSystemService(RequestParameters.SUBRESOURCE_LOCATION);
                this.e = System.currentTimeMillis();
                a("loction_last_update", this.e);
                cb.d("getLocation begin:" + System.currentTimeMillis());
                Criteria criteria = new Criteria();
                criteria.setAccuracy(1);
                cb.d("bestProvider:" + locationManager.getBestProvider(criteria, true));
                Location lastKnownLocation = locationManager.getLastKnownLocation("gps");
                if (lastKnownLocation != null) {
                    cb.a(lastKnownLocation.toString());
                    a(lastKnownLocation);
                } else {
                    Location lastKnownLocation2 = locationManager.getLastKnownLocation("network");
                    if (lastKnownLocation2 != null) {
                        cb.a(lastKnownLocation2.toString());
                        a(lastKnownLocation2);
                    }
                }
                cb.d("getLocation end:" + System.currentTimeMillis());
            }
        } catch (Exception e) {
        }
        return this.b.getFloat(str, -0.1f);
    }

    public void a(Location location) {
        if (location != null) {
            Editor edit = this.b.edit();
            edit.putFloat("msc.lat", (float) location.getLatitude());
            edit.putFloat("msc.lng", (float) location.getLongitude());
            edit.commit();
        }
    }

    public void a(String str, long j) {
        Editor edit = this.b.edit();
        edit.putLong(str, j);
        edit.commit();
    }
}
