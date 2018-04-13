package qsbk.app.nearby.api;

import android.os.Handler;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import qsbk.app.QsbkApp;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.image.issue.Logger;

public class BDLocationManger implements ILocationManager {
    private static BDLocationManger c = null;
    TimerTask a = null;
    Timer b = new Timer();
    private LocationClient d = null;
    private List<ILocationCallback> e = new ArrayList();
    private Handler f = new Handler();

    private BDLocationManger() {
    }

    public static synchronized BDLocationManger instance() {
        BDLocationManger bDLocationManger;
        synchronized (BDLocationManger.class) {
            if (c == null) {
                c = new BDLocationManger();
            }
            bDLocationManger = c;
        }
        return bDLocationManger;
    }

    private void a() {
        if (this.d != null) {
            this.d.stop();
        }
        this.d = new LocationClient(QsbkApp.mContext);
        LocationClientOption locationClientOption = new LocationClientOption();
        locationClientOption.setAddrType("all");
        locationClientOption.setIsNeedAddress(true);
        locationClientOption.setProdName("qsbk.app");
        locationClientOption.setCoorType("gcj02");
        locationClientOption.setTimeOut(10);
        this.d.setLocOption(locationClientOption);
        this.d.registerLocationListener(new a(this));
        this.d.start();
    }

    private synchronized void a(BDLocation bDLocation) {
        for (int size = this.e.size() - 1; size >= 0; size--) {
            ILocationCallback iLocationCallback = (ILocationCallback) this.e.remove(size);
            if (bDLocation != null) {
                Logger.getInstance().debug(BDLocationManger.class.getSimpleName(), "onLocation", bDLocation.getLocType() + ", " + bDLocation.getLatitude() + ", " + bDLocation.getLongitude() + ", " + bDLocation.getDistrict() + ", " + bDLocation.getCity());
                iLocationCallback.onLocation(bDLocation.getLocType(), bDLocation.getLatitude(), bDLocation.getLongitude(), bDLocation.getDistrict(), bDLocation.getCity());
            } else {
                Logger.getInstance().debug(BDLocationManger.class.getSimpleName(), "onLocation", " failed.");
                iLocationCallback.onLocation(-1, 0.0d, 0.0d, null, null);
            }
        }
    }

    public synchronized int getLocation(ILocationCallback iLocationCallback) {
        if (this.d == null) {
            a();
        }
        if (this.a != null) {
            this.a.cancel();
        }
        if (!(iLocationCallback == null || this.e.contains(iLocationCallback))) {
            this.e.add(iLocationCallback);
        }
        return b();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void stop() {
        /*
        r2 = this;
        r1 = 0;
        r0 = r2.d;	 Catch:{ Exception -> 0x000d }
        if (r0 == 0) goto L_0x000a;
    L_0x0005:
        r0 = r2.d;	 Catch:{ Exception -> 0x000d }
        r0.stop();	 Catch:{ Exception -> 0x000d }
    L_0x000a:
        r2.d = r1;
    L_0x000c:
        return;
    L_0x000d:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0014 }
        r2.d = r1;
        goto L_0x000c;
    L_0x0014:
        r0 = move-exception;
        r2.d = r1;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.nearby.api.BDLocationManger.stop():void");
    }

    public void reinit() {
        a();
    }

    private synchronized int b() {
        int requestLocation;
        this.a = new b(this);
        LogUtil.d("location started:" + this.d.isStarted());
        requestLocation = this.d.requestLocation();
        this.b.schedule(this.a, 15000);
        return requestLocation;
    }

    public boolean remove(ILocationCallback iLocationCallback) {
        boolean remove;
        synchronized (this.e) {
            remove = this.e.remove(iLocationCallback);
        }
        return remove;
    }
}
