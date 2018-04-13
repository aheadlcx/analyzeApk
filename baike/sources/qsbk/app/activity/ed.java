package qsbk.app.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class ed extends BroadcastReceiver {
    final /* synthetic */ CheckInActivity a;

    ed(CheckInActivity checkInActivity) {
        this.a = checkInActivity;
    }

    public void onReceive(Context context, Intent intent) {
        this.a.k();
    }
}
