package qsbk.app.nearby.api;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;

class a implements BDLocationListener {
    final /* synthetic */ BDLocationManger a;

    a(BDLocationManger bDLocationManger) {
        this.a = bDLocationManger;
    }

    public void onReceiveLocation(BDLocation bDLocation) {
        this.a.a(bDLocation);
    }
}
