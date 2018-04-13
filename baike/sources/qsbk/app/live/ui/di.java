package qsbk.app.live.ui;

import qsbk.app.core.utils.LogUtils;

class di implements Runnable {
    final /* synthetic */ LiveBaseActivity a;

    di(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void run() {
        if (!this.a.isFinishing()) {
            this.a.x.setVisibility(8);
        }
        LogUtils.d(LiveBaseActivity.a, "live hide follow tips");
    }
}
