package qsbk.app.live.ui;

import qsbk.app.core.net.NetworkCallback;
import qsbk.app.core.utils.ToastUtil;

class cx extends NetworkCallback {
    final /* synthetic */ String a;
    final /* synthetic */ cw b;

    cx(cw cwVar, String str) {
        this.b = cwVar;
        this.a = str;
    }

    public void onFailed(int i, String str) {
        if (i > 0) {
            ToastUtil.Short(this.a);
            LiveBaseActivity.d(this.b.b, new cy(this));
        }
    }
}
