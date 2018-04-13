package cn.v6.sixrooms.hall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import cn.v6.sixrooms.engine.AppUpdateEngine;
import cn.v6.sixrooms.utils.LogUtils;

final class j extends BroadcastReceiver {
    final /* synthetic */ HallActivity a;

    j(HallActivity hallActivity) {
        this.a = hallActivity;
    }

    public final void onReceive(Context context, Intent intent) {
        LogUtils.e("LoginReceive", MineFragment.class.getName());
        String stringExtra = intent.getStringExtra("op");
        AppUpdateEngine appUpdateEngine = new AppUpdateEngine(null);
        if (!TextUtils.isEmpty(stringExtra)) {
            appUpdateEngine.update(stringExtra, "3");
            this.a.getUserInfo(stringExtra);
        }
    }
}
