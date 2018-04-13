package android.support.v7.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class q extends BroadcastReceiver {
    final /* synthetic */ b a;

    q(b bVar) {
        this.a = bVar;
    }

    public void onReceive(Context context, Intent intent) {
        this.a.b();
    }
}
