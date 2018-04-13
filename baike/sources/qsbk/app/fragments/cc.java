package qsbk.app.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class cc extends BroadcastReceiver {
    final /* synthetic */ ContactQiuYouFragment a;

    cc(ContactQiuYouFragment contactQiuYouFragment) {
        this.a = contactQiuYouFragment;
    }

    public void onReceive(Context context, Intent intent) {
        this.a.a = true;
        this.a.onRefresh();
    }
}
