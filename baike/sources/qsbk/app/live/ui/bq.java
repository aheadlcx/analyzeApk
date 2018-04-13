package qsbk.app.live.ui;

import qsbk.app.core.utils.ConfigInfoUtil;

class bq implements Runnable {
    final /* synthetic */ LiveBaseActivity a;

    bq(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void run() {
        ConfigInfoUtil.instance().deleteConfigAndUpdate();
    }
}
