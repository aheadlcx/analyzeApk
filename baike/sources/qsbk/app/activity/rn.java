package qsbk.app.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class rn extends BroadcastReceiver {
    final /* synthetic */ MainActivity a;

    rn(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void onReceive(Context context, Intent intent) {
        if (MainActivity.ACTION_VERIFY.equalsIgnoreCase(intent.getAction())) {
            MainActivity.a(this.a, intent);
        }
    }
}
