package qsbk.app.core.map;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;

class a implements BDLocationListener {
    final /* synthetic */ BDLocationManager a;

    a(BDLocationManager bDLocationManager) {
        this.a = bDLocationManager;
    }

    public void onReceiveLocation(BDLocation bDLocation) {
        this.a.a(bDLocation);
    }
}
