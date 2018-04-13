package qsbk.app.im;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import qsbk.app.activity.MainActivity;

class gm extends BroadcastReceiver {
    final /* synthetic */ IMMessageListFragment a;

    gm(IMMessageListFragment iMMessageListFragment) {
        this.a = iMMessageListFragment;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equalsIgnoreCase(MainActivity.ACTION_NEW_FANS)) {
            this.a.l();
        }
    }
}
