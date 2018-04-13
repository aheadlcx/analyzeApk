package qsbk.app.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class hr extends BroadcastReceiver {
    final /* synthetic */ QiuYouFragment a;

    hr(QiuYouFragment qiuYouFragment) {
        this.a = qiuYouFragment;
    }

    public void onReceive(Context context, Intent intent) {
        this.a.a = true;
        this.a.onRefresh();
    }
}
