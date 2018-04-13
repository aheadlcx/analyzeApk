package qsbk.app.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class re extends BroadcastReceiver {
    final /* synthetic */ MainActivity a;

    re(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void onReceive(Context context, Intent intent) {
        if (MainActivity.ACTION_RECEIVE_IM_MSG.equalsIgnoreCase(intent.getAction())) {
            this.a.unread(1, 0);
        }
    }
}
