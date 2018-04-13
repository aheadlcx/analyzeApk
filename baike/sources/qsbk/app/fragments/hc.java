package qsbk.app.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class hc extends BroadcastReceiver {
    final /* synthetic */ NearbyCircleFragment a;

    hc(NearbyCircleFragment nearbyCircleFragment) {
        this.a = nearbyCircleFragment;
    }

    public void onReceive(Context context, Intent intent) {
        if (this.a.e != null && this.a.r != null) {
            this.a.notifyAdapterDataChanged();
        }
    }
}
