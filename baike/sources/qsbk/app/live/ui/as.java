package qsbk.app.live.ui;

import java.util.List;

class as implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ LiveBaseActivity b;

    as(LiveBaseActivity liveBaseActivity, List list) {
        this.b = liveBaseActivity;
        this.a = list;
    }

    public void run() {
        this.b.Q.clear();
        this.b.Q.addAll(this.a);
        LiveBaseActivity.u(this.b);
    }
}
