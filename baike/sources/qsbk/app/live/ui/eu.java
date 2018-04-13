package qsbk.app.live.ui;

import android.net.Uri;

class eu implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ LivePushActivity b;

    eu(LivePushActivity livePushActivity, String str) {
        this.b = livePushActivity;
        this.a = str;
    }

    public void run() {
        this.b.bL.setImageURI(Uri.parse("file://" + this.a));
    }
}
