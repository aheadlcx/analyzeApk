package com.crashlytics.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class l extends BroadcastReceiver {
    private /* synthetic */ ba a;

    l(ba baVar) {
        this.a = baVar;
    }

    public final void onReceive(Context context, Intent intent) {
        this.a.v = true;
    }
}
