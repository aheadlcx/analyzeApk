package qsbk.app.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class ci extends BroadcastReceiver {
    final /* synthetic */ FollowCircleFragment a;

    ci(FollowCircleFragment followCircleFragment) {
        this.a = followCircleFragment;
    }

    public void onReceive(Context context, Intent intent) {
        if (this.a.e != null && this.a.j != null) {
            this.a.notifyAdapterDataChanged();
        }
    }
}
