package cn.v6.sixrooms.room.utils;

import android.location.Location;
import android.location.LocationManager;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.constants.CommonStrs;

public class LocationUtil {
    public static Location getLocation() {
        Location lastKnownLocation;
        Exception exception;
        Exception exception2;
        Location location = null;
        LocationManager locationManager = (LocationManager) V6Coop.getInstance().getContext().getSystemService(CommonStrs.TYPE_LOCATION);
        try {
            if (locationManager.isProviderEnabled("gps")) {
                location = locationManager.getLastKnownLocation("gps");
                if (location != null) {
                    return location;
                }
            }
            try {
                if (!locationManager.isProviderEnabled("network")) {
                    return location;
                }
                lastKnownLocation = locationManager.getLastKnownLocation("network");
                return lastKnownLocation != null ? lastKnownLocation : lastKnownLocation;
            } catch (Exception e) {
                exception = e;
                lastKnownLocation = location;
                exception2 = exception;
                exception2.printStackTrace();
                return lastKnownLocation;
            }
        } catch (Exception e2) {
            exception = e2;
            lastKnownLocation = null;
            exception2 = exception;
            exception2.printStackTrace();
            return lastKnownLocation;
        }
    }

    public static boolean isOpenLocation() {
        LocationManager locationManager = (LocationManager) V6Coop.getInstance().getContext().getSystemService(CommonStrs.TYPE_LOCATION);
        try {
            if (locationManager.isProviderEnabled("gps") || locationManager.isProviderEnabled("network")) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
