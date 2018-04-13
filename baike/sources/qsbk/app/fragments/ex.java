package qsbk.app.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

class ex extends BroadcastReceiver {
    final /* synthetic */ LiveTabsFragment a;

    ex(LiveTabsFragment liveTabsFragment) {
        this.a = liveTabsFragment;
    }

    public void onReceive(Context context, Intent intent) {
        if (TextUtils.equals(intent.getAction(), "android.net.conn.CONNECTIVITY_CHANGE")) {
            this.a.a();
        }
    }
}
