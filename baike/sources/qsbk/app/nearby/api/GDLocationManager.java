package qsbk.app.nearby.api;

import android.os.Handler;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.QsbkApp;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.TimeDelta;
import qsbk.app.utils.image.issue.Logger;

public class GDLocationManager implements AMapLocationListener, ILocationManager {
    private static GDLocationManager a = null;
    private Handler b = new Handler();
    private AMapLocationClient c;
    private List<ILocationCallback> d = new ArrayList();

    private GDLocationManager() {
    }

    public static synchronized GDLocationManager instance() {
        GDLocationManager gDLocationManager;
        synchronized (GDLocationManager.class) {
            if (a == null) {
                a = new GDLocationManager();
            }
            gDLocationManager = a;
        }
        return gDLocationManager;
    }

    private void a() {
        TimeDelta timeDelta = new TimeDelta();
        this.c = new AMapLocationClient(QsbkApp.mContext);
        LogUtil.d("init manager use time:" + timeDelta.getDelta());
    }

    private synchronized void a(AMapLocation aMapLocation) {
        for (int size = this.d.size() - 1; size >= 0; size--) {
            ILocationCallback iLocationCallback = (ILocationCallback) this.d.remove(size);
            if (aMapLocation == null) {
                Logger.getInstance().debug(GDLocationManager.class.getSimpleName(), "onLocation", " failed.");
                iLocationCallback.onLocation(-1, 0.0d, 0.0d, null, null);
                LogUtil.d("gd location:" + null);
            } else if (aMapLocation.getErrorCode() == 0) {
                Logger.getInstance().debug(GDLocationManager.class.getSimpleName(), "onLocation", "0, " + aMapLocation.getLatitude() + ", " + aMapLocation.getLongitude() + ", " + aMapLocation.getDistrict() + ", " + aMapLocation.getCity());
                iLocationCallback.onLocation(0, aMapLocation.getLatitude(), aMapLocation.getLongitude(), aMapLocation.getDistrict(), aMapLocation.getCity());
                LogUtil.d("gd location:" + aMapLocation.getLatitude() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + aMapLocation.getLongitude() + " location.getCity()" + aMapLocation.getCity());
            } else {
                iLocationCallback.onLocation(-1, 0.0d, 0.0d, null, null);
                LogUtil.e("gd location failed: " + aMapLocation.getErrorCode() + " : " + aMapLocation.getErrorInfo());
            }
        }
        if (this.c != null) {
            this.c.stopLocation();
        }
    }

    public void reinit() {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void stop() {
        /*
        r2 = this;
        r1 = 0;
        r0 = r2.c;	 Catch:{ Exception -> 0x000d }
        if (r0 == 0) goto L_0x000a;
    L_0x0005:
        r0 = r2.c;	 Catch:{ Exception -> 0x000d }
        r0.onDestroy();	 Catch:{ Exception -> 0x000d }
    L_0x000a:
        r2.c = r1;
    L_0x000c:
        return;
    L_0x000d:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0014 }
        r2.c = r1;
        goto L_0x000c;
    L_0x0014:
        r0 = move-exception;
        r2.c = r1;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.nearby.api.GDLocationManager.stop():void");
    }

    public synchronized int getLocation(ILocationCallback iLocationCallback) {
        if (this.c == null) {
            a();
        }
        if (!(iLocationCallback == null || this.d.contains(iLocationCallback))) {
            this.d.add(iLocationCallback);
        }
        return b();
    }

    private synchronized int b() {
        LogUtil.d("use gd location");
        TimeDelta timeDelta = new TimeDelta();
        AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
        aMapLocationClientOption.setLocationCacheEnable(true);
        aMapLocationClientOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
        aMapLocationClientOption.setOnceLocationLatest(true);
        aMapLocationClientOption.setNeedAddress(true);
        aMapLocationClientOption.setMockEnable(false);
        aMapLocationClientOption.setHttpTimeOut(15000);
        this.c.setLocationOption(aMapLocationClientOption);
        this.c.setLocationListener(this);
        this.c.startLocation();
        LogUtil.d("time use:" + timeDelta.getDelta());
        return 0;
    }

    public void onLocationChanged(AMapLocation aMapLocation) {
        a(aMapLocation);
    }

    public boolean remove(ILocationCallback iLocationCallback) {
        boolean remove;
        synchronized (this.d) {
            remove = this.d.remove(iLocationCallback);
        }
        return remove;
    }
}
