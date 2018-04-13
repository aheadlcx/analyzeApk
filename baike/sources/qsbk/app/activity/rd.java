package qsbk.app.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class rd extends BroadcastReceiver {
    final /* synthetic */ MainActivity a;

    rd(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void onReceive(Context context, Intent intent) {
        if (MainActivity.ACTION_RECEIVE_MEDAL_MSG.equalsIgnoreCase(intent.getAction())) {
            this.a.unread(1, 0);
            this.a.setSmallTips(MainActivity.TAB_MIME_ID);
        }
    }
}
