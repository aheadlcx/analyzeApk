package qsbk.app.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class am extends BroadcastReceiver {
    final /* synthetic */ CircleTopicFragment a;

    am(CircleTopicFragment circleTopicFragment) {
        this.a = circleTopicFragment;
    }

    public void onReceive(Context context, Intent intent) {
        if (this.a.g != null && this.a.h != null) {
            this.a.h.notifyDataSetChanged();
        }
    }
}
