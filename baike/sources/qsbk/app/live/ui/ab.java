package qsbk.app.live.ui;

import qsbk.app.core.utils.LogUtils;

class ab implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ LiveBaseActivity b;

    ab(LiveBaseActivity liveBaseActivity, int i) {
        this.b = liveBaseActivity;
        this.a = i;
    }

    public void run() {
        if (!this.b.isFinishing()) {
            LiveBaseActivity.h(this.b).remove(Integer.valueOf(this.a));
            LogUtils.d("MicConnect", "doRemoveRemoteUi. uid:" + this.a);
            LiveBaseActivity.r(this.b);
        }
    }
}
