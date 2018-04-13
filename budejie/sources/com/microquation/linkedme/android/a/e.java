package com.microquation.linkedme.android.a;

import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;

public class e {
    public static boolean a(Context context) {
        return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo() != null;
    }

    public static boolean a(Location location) {
        return (location == null || (location.getLatitude() == 0.0d && location.getLongitude() == 0.0d)) ? false : true;
    }

    public static boolean a(Location location, Location location2) {
        if (location2 == null) {
            return true;
        }
        long time = location.getTime() - location2.getTime();
        boolean z = time > 120000;
        boolean z2 = time < -120000;
        boolean z3 = time > 0;
        if (z) {
            return true;
        }
        if (z2) {
            return false;
        }
        int accuracy = (int) (location.getAccuracy() - location2.getAccuracy());
        return !(accuracy < 0) ? (!z3 || (accuracy > 0)) ? z3 && !(accuracy > 200) && a(location.getProvider(), location2.getProvider()) : true : true;
    }

    public static boolean a(String str, String str2) {
        return str == null ? str2 == null : str.equals(str2);
    }
}
