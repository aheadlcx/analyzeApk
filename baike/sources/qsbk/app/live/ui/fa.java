package qsbk.app.live.ui;

import android.view.View;

class fa implements Runnable {
    final /* synthetic */ View a;
    final /* synthetic */ LivePushActivity b;

    fa(LivePushActivity livePushActivity, View view) {
        this.b = livePushActivity;
        this.a = view;
    }

    public void run() {
        this.a.setVisibility(8);
    }
}
