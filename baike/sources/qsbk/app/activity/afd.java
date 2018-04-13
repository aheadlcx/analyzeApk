package qsbk.app.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class afd extends BroadcastReceiver {
    final /* synthetic */ VideoImmersionActivity a;

    afd(VideoImmersionActivity videoImmersionActivity) {
        this.a = videoImmersionActivity;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            this.a.i();
        }
    }
}
