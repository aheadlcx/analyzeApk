package qsbk.app.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class cj extends BroadcastReceiver {
    final /* synthetic */ FollowCircleFragment a;

    cj(FollowCircleFragment followCircleFragment) {
        this.a = followCircleFragment;
    }

    public void onReceive(Context context, Intent intent) {
        if (this.a.j != null) {
            this.a.j.notifyDataSetChanged();
        }
    }
}
