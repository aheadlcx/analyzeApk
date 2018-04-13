package qsbk.app.live.share;

import android.os.Bundle;
import com.tencent.tauth.Tencent;
import qsbk.app.thirdparty.ThirdPartyConstants;

class e implements Runnable {
    final /* synthetic */ Bundle a;
    final /* synthetic */ d b;

    e(d dVar, Bundle bundle) {
        this.b = dVar;
        this.a = bundle;
    }

    public void run() {
        this.b.a.f = Tencent.createInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, this.b.a);
        this.b.a.f.publishToQzone(this.b.a, this.a, this.b.a.r);
    }
}
