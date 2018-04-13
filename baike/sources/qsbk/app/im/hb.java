package qsbk.app.im;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class hb extends BroadcastReceiver {
    final /* synthetic */ IMMessageListFragment a;

    hb(IMMessageListFragment iMMessageListFragment) {
        this.a = iMMessageListFragment;
    }

    public void onReceive(Context context, Intent intent) {
        if (this.a.q != null) {
            this.a.q.notifyDataSetChanged();
        }
    }
}
