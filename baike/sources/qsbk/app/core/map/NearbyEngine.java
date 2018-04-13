package qsbk.app.core.map;

import android.content.Context;
import android.location.LocationManager;
import android.text.TextUtils;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.LogUtils;
import qsbk.app.core.utils.PreferenceUtils;
import qsbk.app.core.utils.PrefrenceKeys;

public class NearbyEngine {
    public static final int RESP_NEED_INFO = -110;
    public static final int RESP_NEED_INFO_DIALOG = -120;
    public static final int RESP_SUCCESS = 0;
    private static NearbyEngine a = null;
    private boolean b = false;

    public static synchronized NearbyEngine instance() {
        NearbyEngine nearbyEngine;
        synchronized (NearbyEngine.class) {
            if (a == null) {
                a = new NearbyEngine();
            }
            nearbyEngine = a;
        }
        return nearbyEngine;
    }

    private NearbyEngine() {
    }

    public String getUserLocalPrefByKey(String str) {
        LogUtils.e(String.format("get key:%s value:%s", new Object[]{String.format(str, new Object[]{Long.valueOf(AppUtils.getInstance().getUserInfoProvider().getUserId())}), PreferenceUtils.instance().getString(String.format(str, new Object[]{Long.valueOf(AppUtils.getInstance().getUserInfoProvider().getUserId())}), "")}));
        return PreferenceUtils.instance().getString(String.format(str, new Object[]{Long.valueOf(AppUtils.getInstance().getUserInfoProvider().getUserId())}), "");
    }

    public boolean getLocalUserFlagByKey(String str) {
        return !TextUtils.isEmpty(getUserLocalPrefByKey(str));
    }

    public void setLocalUserFlagByKey(String str, String str2) {
        LogUtils.d(String.format("set key:%s value:%s", new Object[]{String.format(str, new Object[]{Long.valueOf(AppUtils.getInstance().getUserInfoProvider().getUserId())}), str2}));
        PreferenceUtils.instance().putString(r0, str2);
    }

    public void setLocalUserFlagByKey(String str, boolean z) {
        String format = String.format(str, new Object[]{Long.valueOf(AppUtils.getInstance().getUserInfoProvider().getUserId())});
        if (z) {
            PreferenceUtils.instance().putString(format, "true");
        } else {
            PreferenceUtils.instance().removeKey(format);
        }
    }

    public boolean isLocationServiceEnabled(Context context) {
        boolean isProviderEnabled;
        boolean isProviderEnabled2;
        Exception exception;
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            isProviderEnabled = locationManager.isProviderEnabled("gps");
            try {
                isProviderEnabled2 = locationManager.isProviderEnabled("network");
            } catch (Exception e) {
                Exception exception2 = e;
                isProviderEnabled2 = isProviderEnabled;
                exception = exception2;
                exception.printStackTrace();
                isProviderEnabled = isProviderEnabled2;
                isProviderEnabled2 = false;
                LogUtils.d("gps enabled:" + isProviderEnabled);
                LogUtils.d("networkd enabled:" + isProviderEnabled2);
                if (!isProviderEnabled) {
                }
                return true;
            }
        } catch (Exception e2) {
            exception = e2;
            isProviderEnabled2 = false;
            exception.printStackTrace();
            isProviderEnabled = isProviderEnabled2;
            isProviderEnabled2 = false;
            LogUtils.d("gps enabled:" + isProviderEnabled);
            LogUtils.d("networkd enabled:" + isProviderEnabled2);
            if (isProviderEnabled) {
            }
            return true;
        }
        LogUtils.d("gps enabled:" + isProviderEnabled);
        LogUtils.d("networkd enabled:" + isProviderEnabled2);
        if (isProviderEnabled || isProviderEnabled2) {
            return true;
        }
        return false;
    }

    public boolean isHasGps() {
        return AppUtils.getInstance().getAppContext().getPackageManager().hasSystemFeature("android.hardware.location");
    }

    public static void saveLastLocationToLocal(Location location) {
        PreferenceUtils.instance().putString("local_location", AppUtils.toJson(location));
    }

    public static Location getLastSavedLocation(boolean z) {
        Object string = PreferenceUtils.instance().getString("local_location", "");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        if (z) {
            PreferenceUtils.instance().putString("local_location", "");
        }
        return (Location) AppUtils.fromJson(string, Location.class);
    }

    public ILocationManager changeLocationMgr(ILocationManager iLocationManager) {
        if (iLocationManager instanceof BDLocationManager) {
            PreferenceUtils.instance().putString(PrefrenceKeys.PRE_KEY_LOCATION_MANAGER, "gaode");
        } else {
            PreferenceUtils.instance().putString(PrefrenceKeys.PRE_KEY_LOCATION_MANAGER, "baidu");
        }
        if (iLocationManager != null) {
            iLocationManager.stop();
        }
        return getLastLocationManager();
    }

    public ILocationManager getLastLocationManager() {
        PreferenceUtils.instance().getString(PrefrenceKeys.PRE_KEY_LOCATION_MANAGER, "baidu");
        LogUtils.d("use baidu location");
        return BDLocationManager.instance();
    }
}
