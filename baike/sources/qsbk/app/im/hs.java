package qsbk.app.im;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

class hs extends BroadcastReceiver {
    final /* synthetic */ IMMessageListFragment a;

    hs(IMMessageListFragment iMMessageListFragment) {
        this.a = iMMessageListFragment;
    }

    public void onReceive(Context context, Intent intent) {
        if (TextUtils.equals(intent.getAction(), "android.net.conn.CONNECTIVITY_CHANGE")) {
            this.a.c();
        }
    }
}
