package qsbk.app.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class bt extends BroadcastReceiver {
    final /* synthetic */ CircleVideoFragment a;

    bt(CircleVideoFragment circleVideoFragment) {
        this.a = circleVideoFragment;
    }

    public void onReceive(Context context, Intent intent) {
        if (this.a.d != null && this.a.j != null) {
            this.a.notifyAdapterDataChanged();
        }
    }
}
