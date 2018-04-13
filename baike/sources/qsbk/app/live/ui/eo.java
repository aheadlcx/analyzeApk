package qsbk.app.live.ui;

import android.text.TextUtils;

class eo implements Runnable {
    final /* synthetic */ LivePushActivity a;

    eo(LivePushActivity livePushActivity) {
        this.a = livePushActivity;
    }

    public void run() {
        if (TextUtils.isEmpty(this.a.ch) || this.a.ch.startsWith("rtmp://pili-publish.app-remix.com")) {
            this.a.ch = null;
            this.a.aA = null;
            this.a.J();
            return;
        }
        this.a.aJ();
    }
}
