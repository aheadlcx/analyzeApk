package cn.v6.sixrooms.hall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class c extends BroadcastReceiver {
    final /* synthetic */ AttentionFragment a;

    c(AttentionFragment attentionFragment) {
        this.a = attentionFragment;
    }

    public final void onReceive(Context context, Intent intent) {
        AttentionFragment.a(this.a);
    }
}
