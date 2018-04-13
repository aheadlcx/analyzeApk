package cn.xiaochuankeji.tieba.background.g;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import com.alibaba.sdk.android.oss.common.RequestParameters;

class b {
    static void a(Context context) {
        com.izuiyou.a.a.b.c("GetLocationData");
        if (b(context) && a("gps", context) != null) {
            a(a("gps", context));
        } else if (!c(context) || a("network", context) == null) {
            a(a("passive", context));
        } else {
            a(a("network", context));
        }
    }

    private static boolean b(Context context) {
        return d(context).isProviderEnabled("gps");
    }

    private static boolean c(Context context) {
        return d(context).isProviderEnabled("network");
    }

    private static void a(Location location) {
        d.a().a(b(location));
    }

    private static a b(Location location) {
        a aVar = new a();
        if (location != null) {
            aVar.b(location.getLongitude());
            aVar.a(location.getAltitude());
            aVar.c(location.getLatitude());
            aVar.a(location.getTime());
        }
        return aVar;
    }

    private static Location a(String str, Context context) {
        return d(context).getLastKnownLocation(str);
    }

    private static LocationManager d(Context context) {
        return (LocationManager) context.getSystemService(RequestParameters.SUBRESOURCE_LOCATION);
    }
}
