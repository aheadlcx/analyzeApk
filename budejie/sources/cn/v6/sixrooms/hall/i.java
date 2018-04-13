package cn.v6.sixrooms.hall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class i extends BroadcastReceiver {
    final /* synthetic */ HallActivity a;

    i(HallActivity hallActivity) {
        this.a = hallActivity;
    }

    public final void onReceive(Context context, Intent intent) {
        this.a.f.setChecked(1);
    }
}
