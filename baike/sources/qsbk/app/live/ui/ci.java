package qsbk.app.live.ui;

import qsbk.app.core.utils.LogUtils;

class ci implements Runnable {
    final /* synthetic */ LiveBaseActivity a;

    ci(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void run() {
        LogUtils.d(LiveBaseActivity.a, "live author continue");
        this.a.J();
    }
}
