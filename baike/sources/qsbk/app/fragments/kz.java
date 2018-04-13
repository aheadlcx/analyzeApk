package qsbk.app.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;

class kz extends BroadcastReceiver {
    final /* synthetic */ SubscribeFragment a;

    kz(SubscribeFragment subscribeFragment) {
        this.a = subscribeFragment;
    }

    public void onReceive(Context context, Intent intent) {
        if (this.a.i != null) {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                this.a.T();
                this.a.U();
                this.a.i.notifyDataSetChanged();
                if (this.a.m != null) {
                    this.a.m.post(this.a.R);
                    return;
                }
                return;
            }
            SubscribeFragment.a.post(new la(this));
        }
    }
}
