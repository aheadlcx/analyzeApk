package com.iflytek.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.iflytek.cloud.thirdparty.dj;
import com.iflytek.cloud.thirdparty.dr;

final class b extends BroadcastReceiver {
    final /* synthetic */ LaunchService a;

    b(LaunchService launchService) {
        this.a = launchService;
    }

    public final void onReceive(Context context, Intent intent) {
        dr.a(context, "alarm onReceive");
        dj.a(context);
        this.a.b();
    }
}
