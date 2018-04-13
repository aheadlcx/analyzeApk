package qsbk.app.live.ui;

import qsbk.app.core.utils.LogUtils;

class cu implements Runnable {
    final /* synthetic */ LiveBaseActivity a;

    cu(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void run() {
        LogUtils.d(LiveBaseActivity.a, "live retry get live room ip");
        this.a.mHandler.removeCallbacks(this);
        this.a.M();
    }
}
