package qsbk.app.nearby.api;

import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import qsbk.app.utils.AppContext;
import qsbk.app.utils.LogUtil;

public class LocationTester {
    public static void TestILocationManager(ILocationManager iLocationManager, String str) {
        iLocationManager.getLocation(new h(str, iLocationManager));
    }

    public static void testAll() {
        TestILocationManager(BDLocationManger.instance(), "baidu");
        TestILocationManager(GDLocationManager.instance(), "gaode");
    }

    public void testLocationEnable() {
        if (VERSION.SDK_INT < 19) {
            String string = Secure.getString(AppContext.getContext().getContentResolver(), "location_providers_allowed");
            if (string != null) {
                LogUtil.d("Location providers: " + string);
                return;
            } else {
                LogUtil.d("Location providers: " + null);
                return;
            }
        }
        LogUtil.d("locationMode:" + Secure.getInt(AppContext.getContext().getContentResolver(), "location_mode", 0));
    }
}
