package qsbk.app.live.ui;

import qsbk.app.core.utils.LogUtils;

class bp implements Runnable {
    final /* synthetic */ LiveBaseActivity a;

    bp(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void run() {
        LogUtils.d(LiveBaseActivity.a, "live close message");
        this.a.I();
    }
}
