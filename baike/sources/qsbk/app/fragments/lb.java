package qsbk.app.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class lb extends BroadcastReceiver {
    final /* synthetic */ SubscribeFragment a;

    lb(SubscribeFragment subscribeFragment) {
        this.a = subscribeFragment;
    }

    public void onReceive(Context context, Intent intent) {
        if (this.a.j != null && this.a.j.size() > 1) {
            this.a.j.remove(0);
            this.a.b().notifyDataSetChanged();
        }
        this.a.X.unregisterReceiver(this.a.aa);
    }
}
