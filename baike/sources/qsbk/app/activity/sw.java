package qsbk.app.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class sw extends BroadcastReceiver {
    final /* synthetic */ MainActivity a;

    sw(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void onReceive(Context context, Intent intent) {
        this.a.checkLiveBeginUnread();
    }
}
