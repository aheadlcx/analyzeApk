package cn.v6.sixrooms.hall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import cn.v6.sixrooms.base.VLScheduler;

final class d extends BroadcastReceiver {
    final /* synthetic */ AttentionFragment a;

    d(AttentionFragment attentionFragment) {
        this.a = attentionFragment;
    }

    public final void onReceive(Context context, Intent intent) {
        VLScheduler.instance.schedule(100, 0, new e(this));
    }
}
