package qsbk.app.live.share;

import android.os.Bundle;
import com.tencent.tauth.Tencent;
import qsbk.app.thirdparty.ThirdPartyConstants;

class p implements Runnable {
    final /* synthetic */ Bundle a;
    final /* synthetic */ o b;

    p(o oVar, Bundle bundle) {
        this.b = oVar;
        this.a = bundle;
    }

    public void run() {
        this.b.a.f = Tencent.createInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, this.b.a);
        this.b.a.f.shareToQQ(this.b.a, this.a, this.b.a.r);
    }
}
