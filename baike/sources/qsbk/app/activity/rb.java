package qsbk.app.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class rb extends BroadcastReceiver {
    final /* synthetic */ MainActivity a;

    rb(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void onReceive(Context context, Intent intent) {
        if (MainActivity.ACTION_UPDATE.equalsIgnoreCase(intent.getAction())) {
            MainActivity.a(this.a);
        }
    }
}
