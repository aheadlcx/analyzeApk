package qsbk.app.service;

import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.LogUtil;

class g implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ VerifyUserInfoService b;

    g(VerifyUserInfoService verifyUserInfoService, int i) {
        this.b = verifyUserInfoService;
        this.a = i;
    }

    public void run() {
        LogUtil.d("delay verify:" + this.a);
        if (HttpUtils.netIsAvailable()) {
            this.b.a(this.a);
        } else {
            this.b.b(this.a);
        }
    }
}
