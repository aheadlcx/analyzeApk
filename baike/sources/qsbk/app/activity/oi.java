package qsbk.app.activity;

import qsbk.app.activity.HighLightQiushiActivity.QiushiListFragment;

class oi implements Runnable {
    final /* synthetic */ QiushiListFragment a;

    oi(QiushiListFragment qiushiListFragment) {
        this.a = qiushiListFragment;
    }

    public void run() {
        if (this.a.m != null) {
            this.a.l.setLoadMoreEnable(false);
        }
    }
}
